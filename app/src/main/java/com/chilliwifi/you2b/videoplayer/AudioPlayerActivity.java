package com.chilliwifi.you2b.videoplayer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.chilliwifi.you2b.App;
import com.chilliwifi.you2b.R;
import com.chilliwifi.you2b.SampleModule;
import com.chilliwifi.you2b.searchyou2b.DaggerSearchYouTubeComponent;
import com.chilliwifi.you2b.searchyou2b.SearchYouTubeComponent;
import com.chilliwifi.you2b.videoplayer.data.Samples;
import com.chilliwifi.you2b.videoplayer.manager.PlaylistManager;
import com.chilliwifi.you2b.videoplayer.playlist.VideoApi;
import com.chilliwifi.you2b.videourl.VideoUrlApi;
import com.chilliwifi.you2b.videourl.model.VideoUrl;
import com.devbrackets.android.exomedia.util.TimeFormatUtil;
import com.devbrackets.android.playlistcore.event.MediaProgress;
import com.devbrackets.android.playlistcore.event.PlaylistItemChange;
import com.devbrackets.android.playlistcore.listener.PlaylistListener;
import com.devbrackets.android.playlistcore.listener.ProgressListener;
import com.devbrackets.android.playlistcore.manager.IPlaylistItem;
import com.devbrackets.android.playlistcore.service.PlaylistServiceCore;
import com.squareup.picasso.Picasso;
import com.chilliwifi.you2b.videoplayer.data.MediaItem;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.chilliwifi.you2b.videoplayer.VideoPlayerActivity.EXTRA_VIDEO_ID;

/**
 * An example activity to show how to implement and audio UI
 * that interacts with the {@link com.devbrackets.android.playlistcore.service.BasePlaylistService}
 * and {@link com.devbrackets.android.playlistcore.manager.BasePlaylistManager} classes.
 */
public class AudioPlayerActivity extends AppCompatActivity implements PlaylistListener, ProgressListener {
    public static final String EXTRA_INDEX = "EXTRA_INDEX";
    public static final int PLAYLIST_ID = 4; //Arbitrary, for the example

    private ProgressBar loadingBar;
    private ImageView artworkView;

    private TextView currentPositionView;
    private TextView durationView;

    private SeekBar seekBar;
    private boolean shouldSetDuration;
    private boolean userInteracting;

    private ImageButton previousButton;
    private ImageButton playPauseButton;
    private ImageButton nextButton;

    private PlaylistManager playlistManager;
    private int selectedIndex = 0;

    private Picasso picasso;


    protected String videoId;

    @Inject
    SearchYouTubeComponent searchYouTubeComponent;
    VideoUrlApi videoUrlApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_player_activity);

        injectDependencies();
        retrieveExtras();

        System.out.println("Javier videoid " + videoId);

        init();

        getVideoURl(videoId);

    }


    protected void injectDependencies() {
        searchYouTubeComponent =
                DaggerSearchYouTubeComponent.builder().sampleModule(new SampleModule(getApplicationContext())).build();
        searchYouTubeComponent.inject(this);


        videoUrlApi = searchYouTubeComponent.videoUrlApi();

    }

    @Override
    protected void onPause() {
        super.onPause();
        playlistManager.unRegisterPlaylistListener(this);
        playlistManager.unRegisterProgressListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        playlistManager = App.getPlaylistManager();
        playlistManager.registerPlaylistListener(this);
        playlistManager.registerProgressListener(this);

        //Makes sure to retrieve the current playback information
        updateCurrentPlaybackInformation();
    }

    @Override
    public boolean onPlaylistItemChanged(@Nullable IPlaylistItem currentItem, boolean hasNext, boolean hasPrevious) {
        shouldSetDuration = true;

        //Updates the button states
        nextButton.setEnabled(hasNext);
        previousButton.setEnabled(hasPrevious);

        //Loads the new image
        picasso.load(currentItem.getArtworkUrl()).into(artworkView);

        return true;
    }

    @Override
    public boolean onPlaybackStateChanged(@NonNull PlaylistServiceCore.PlaybackState playbackState) {
        switch (playbackState) {
            case STOPPED:
                finish();
                break;

            case RETRIEVING:
            case PREPARING:
            case SEEKING:
                restartLoading();
                break;

            case PLAYING:
                doneLoading(true);
                break;

            case PAUSED:
                doneLoading(false);
                break;

            default:
                break;
        }

        return true;
    }

    @Override
    public boolean onProgressUpdated(@NonNull MediaProgress progress) {
        if (shouldSetDuration && progress.getDuration() > 0) {
            shouldSetDuration = false;
            setDuration(progress.getDuration());
        }

        if (!userInteracting) {
            seekBar.setSecondaryProgress((int) (progress.getDuration() * progress.getBufferPercentFloat()));
            seekBar.setProgress((int)progress.getPosition());
            currentPositionView.setText(TimeFormatUtil.formatMs(progress.getPosition()));
        }

        return true;
    }

    /**
     * Makes sure to update the UI to the current playback item.
     */
    private void updateCurrentPlaybackInformation() {
        PlaylistItemChange<MediaItem> itemChangedEvent = playlistManager.getCurrentItemChange();
        if (itemChangedEvent != null) {
            onPlaylistItemChanged(itemChangedEvent.getCurrentItem(), itemChangedEvent.hasNext(), itemChangedEvent.hasPrevious());
        }

        PlaylistServiceCore.PlaybackState currentPlaybackState = playlistManager.getCurrentPlaybackState();
        if (currentPlaybackState != PlaylistServiceCore.PlaybackState.STOPPED) {
            onPlaybackStateChanged(currentPlaybackState);
        }

        MediaProgress progressEvent = playlistManager.getCurrentProgress();
        if (progressEvent != null) {
            onProgressUpdated(progressEvent);
        }
    }

    /**
     * Retrieves the extra associated with the selected playlist index
     * so that we can start playing the correct item.
     */
    private void retrieveExtras() {
        Bundle extras = getIntent().getExtras();
        videoId = extras.getString(EXTRA_VIDEO_ID, "");
        selectedIndex = 0;
    }

    /**
     * Performs the initialization of the views and any other
     * general setup
     */
    private void init() {
        retrieveViews();
        setupListeners();

        picasso = Picasso.with(getApplicationContext());

        boolean generatedPlaylist = setupPlaylistManager();
//        startPlayback(generatedPlaylist);
    }


    /**
     * Called when we receive a notification that the current item is
     * done loading.  This will then update the view visibilities and
     * states accordingly.
     *
     * @param isPlaying True if the audio item is currently playing
     */
    private void doneLoading(boolean isPlaying) {
        loadCompleted();
        updatePlayPauseImage(isPlaying);
    }

    /**
     * Updates the Play/Pause image to represent the correct playback state
     *
     * @param isPlaying True if the audio item is currently playing
     */
    private void updatePlayPauseImage(boolean isPlaying) {
        int resId = isPlaying ? R.drawable.playlistcore_ic_pause_black : R.drawable.playlistcore_ic_play_arrow_black;
        playPauseButton.setImageResource(resId);
    }

    /**
     * Used to inform the controls to finalize their setup.  This
     * means replacing the loading animation with the PlayPause button
     */
    public void loadCompleted() {
        playPauseButton.setVisibility(View.VISIBLE);
        previousButton.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE );

        loadingBar.setVisibility(View.INVISIBLE);
    }

    /**
     * Used to inform the controls to return to the loading stage.
     * This is the opposite of {@link #loadCompleted()}
     */
    public void restartLoading() {
        playPauseButton.setVisibility(View.INVISIBLE);
        previousButton.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE );

        loadingBar.setVisibility(View.VISIBLE);
    }

    /**
     * Sets the {@link #seekBar}s max and updates the duration text
     *
     * @param duration The duration of the media item in milliseconds
     */
    private void setDuration(long duration) {
        seekBar.setMax((int)duration);
        durationView.setText(TimeFormatUtil.formatMs(duration));
    }

    /**
     * Retrieves the playlist instance and performs any generation
     * of content if it hasn't already been performed.
     *
     * @return True if the content was generated
     */
    private boolean setupPlaylistManager() {
        playlistManager = App.getPlaylistManager();

        //There is nothing to do if the currently playing values are the same
        if (playlistManager.getId() == PLAYLIST_ID) {
            return false;
        }

//        List<MediaItem> mediaItems = new LinkedList<>();
//        for (Samples.Sample sample : Samples.getAudioSamples()) {
//            MediaItem mediaItem = new MediaItem(sample, true);
//            mediaItems.add(mediaItem);
//        }

//        playlistManager.setParameters(mediaItems, selectedIndex);
        playlistManager.setId(PLAYLIST_ID);

        return true;
    }

    /**
     * Populates the class variables with the views created from the
     * xml layout file.
     */
    private void retrieveViews() {
        loadingBar = (ProgressBar)findViewById(R.id.audio_player_loading);
        artworkView = (ImageView)findViewById(R.id.audio_player_image);

        currentPositionView = (TextView)findViewById(R.id.audio_player_position);
        durationView = (TextView)findViewById(R.id.audio_player_duration);

        seekBar = (SeekBar)findViewById(R.id.audio_player_seek);

        previousButton = (ImageButton)findViewById(R.id.audio_player_previous);
        playPauseButton = (ImageButton)findViewById(R.id.audio_player_play_pause);
        nextButton = (ImageButton)findViewById(R.id.audio_player_next);
    }

    /**
     * Links the SeekBarChanged to the {@link #seekBar} and
     * onClickListeners to the media buttons that call the appropriate
     * invoke methods in the {@link #playlistManager}
     */
    private void setupListeners() {
        seekBar.setOnSeekBarChangeListener(new SeekBarChanged());

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playlistManager.invokePrevious();
            }
        });

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playlistManager.invokePausePlay();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playlistManager.invokeNext();
            }
        });
    }

    /**
     * Starts the audio playback if necessary.
     *
     * @param forceStart True if the audio should be started from the beginning even if it is currently playing
     */
    private void startPlayback(boolean forceStart) {
        //If we are changing audio files, or we haven't played before then start the playback
        if (forceStart || playlistManager.getCurrentPosition() != selectedIndex) {
            playlistManager.setCurrentPosition(selectedIndex);
            playlistManager.play(0, false);
        }
    }

    /**
     * Listens to the seek bar change events and correctly handles the changes
     */
    private class SeekBarChanged implements SeekBar.OnSeekBarChangeListener {
        private int seekPosition = -1;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (!fromUser) {
                return;
            }

            seekPosition = progress;
            currentPositionView.setText(TimeFormatUtil.formatMs(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            userInteracting = true;

            seekPosition = seekBar.getProgress();
            playlistManager.invokeSeekStarted();
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            userInteracting = false;

            //noinspection Range - seekPosition won't be less than 0
            playlistManager.invokeSeekEnded(seekPosition);
            seekPosition = -1;
        }
    }

    private void getVideoURl(final String videoId) {
        Observable<VideoUrl> videoUrl = videoUrlApi.getVideoUrl(videoId);
        Observable<VideoUrl> videoUrlObservable = videoUrl.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        videoUrlObservable.subscribe(new Subscriber<VideoUrl>() {
            @Override
            public void onCompleted() {
                System.out.println("Javier YoutubeVO completed");

            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Javier YoutubeVO error " + e.toString());
            }

            @Override
            public void onNext(VideoUrl videoUrl) {
                System.out.println("Javier YoutubeVO url " + videoUrl.getInfo().getUrl());
                Samples.Sample sample = new Samples.Sample(videoUrl.getInfo().getTitle(), videoUrl.getInfo().getUrl());
                List<MediaItem> mediaItems = new LinkedList<>();
                MediaItem mediaItem = new MediaItem(sample, true);
                mediaItems.add(mediaItem);
                playlistManager.setParameters(mediaItems, 0);
                play();
            }
        });
    }

    private void play() {


        playlistManager.setCurrentPosition(0);


        String mediaUrl = playlistManager.getCurrentItem().getMediaUrl();
        System.out.println("Javier audio mediaurl " + mediaUrl);

        playlistManager.play(0, false);



    }
}
