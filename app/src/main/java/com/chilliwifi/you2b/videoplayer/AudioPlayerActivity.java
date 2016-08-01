package com.chilliwifi.you2b.videoplayer;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.chilliwifi.you2b.videoplayer.data.MediaItem;
import com.chilliwifi.you2b.videoplayer.data.Samples;
import com.chilliwifi.you2b.videoplayer.manager.PlaylistManager;
import com.chilliwifi.you2b.videourl.VideoUrlApi;
import com.chilliwifi.you2b.videourl.model.Format;
import com.chilliwifi.you2b.videourl.model.VideoUrl;
import com.devbrackets.android.exomedia.util.TimeFormatUtil;
import com.devbrackets.android.playlistcore.event.MediaProgress;
import com.devbrackets.android.playlistcore.event.PlaylistItemChange;
import com.devbrackets.android.playlistcore.listener.PlaylistListener;
import com.devbrackets.android.playlistcore.listener.ProgressListener;
import com.devbrackets.android.playlistcore.service.PlaylistServiceCore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * An example activity to show how to implement and audio UI
 * that interacts with the {@link com.devbrackets.android.playlistcore.service.BasePlaylistService}
 * and {@link com.devbrackets.android.playlistcore.manager.BasePlaylistManager} classes.
 */
public class AudioPlayerActivity extends AppCompatActivity implements PlaylistListener<MediaItem>, ProgressListener {
    public static final String EXTRA_INDEX = "EXTRA_INDEX";
    public static final int PLAYLIST_ID = 4; //Arbitrary, for the example
    public static final String MEDIA_ITEMS = "MEDIA_ITEMS";

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

    private ArrayList<MediaItem> mediaItemsArrayList;
    private TextView title;
    private TextView subtitle;

    private Picasso picasso;
    @Inject
    SearchYouTubeComponent searchYouTubeComponent;
    VideoUrlApi videoUrlApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_player_activity);

        System.out.println("Javier debug App.getPlaylistManager  onCreate " + App.getPlaylistManager().getItemCount());

        injectDependencies();
        retrieveExtras();
        init();
    }

    protected void injectDependencies() {
        searchYouTubeComponent = DaggerSearchYouTubeComponent.builder().sampleModule(new SampleModule(getApplicationContext())).build();
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
    public boolean onPlaylistItemChanged(MediaItem currentItem, boolean hasNext, boolean hasPrevious) {
        shouldSetDuration = true;

        //Updates the button states
        nextButton.setEnabled(hasNext);
        previousButton.setEnabled(hasPrevious);

        //Loads the new image
        picasso.load(currentItem.getArtworkUrl()).into(artworkView);

        updateTitles();

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
            seekBar.setProgress((int) progress.getPosition());
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

        System.out.println("Javier debug finding extras " + extras);
        if (extras != null) {
            selectedIndex = extras.getInt(EXTRA_INDEX, 0);
            mediaItemsArrayList = extras.getParcelableArrayList(MEDIA_ITEMS);
        }
    }

    /**
     * Performs the initialization of the views and any other
     * general setup
     */
    private void init() {
        retrieveViews();
        setupListeners();

        picasso = Picasso.with(getApplicationContext());

        System.out.println("Javier debug PLAYLIST_ID " + PLAYLIST_ID);
        System.out.println("Javier debug mediaItemsArrayList" + mediaItemsArrayList);
        System.out.println("Javier debug App.getPlaylistManager " + App.getPlaylistManager().getItemCount());
        System.out.println("Javier debug App.getItems " + App.getPlaylistManager().getItems());
        System.out.println("Javier debug getCurrentPlaybackState " + App.getPlaylistManager().getCurrentPlaybackState());
        System.out.println("Javier debug getCurrent position at init!  " + App.getPlaylistManager().getCurrentPosition());

        boolean generatedPlaylist = setupPlaylistManager();
        startPlayback(generatedPlaylist);
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
        nextButton.setVisibility(View.VISIBLE);

        loadingBar.setVisibility(View.INVISIBLE);
    }

    /**
     * Used to inform the controls to return to the loading stage.
     * This is the opposite of {@link #loadCompleted()}
     */
    public void restartLoading() {
        playPauseButton.setVisibility(View.INVISIBLE);
        previousButton.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);

        loadingBar.setVisibility(View.VISIBLE);
    }

    /**
     * Sets the {@link #seekBar}s max and updates the duration text
     *
     * @param duration The duration of the media item in milliseconds
     */
    private void setDuration(long duration) {
        seekBar.setMax((int) duration);
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
//        if (playlistManager.getId() == PLAYLIST_ID) {
//            return false;
//        }


        if (mediaItemsArrayList == null) {
            if(playlistManager.getItems()==null)
            {
                //nothing to play!!!
                finish();
                return false;
            }

        }



        System.out.println("Javier debug2 mediaItemsArrayList " + mediaItemsArrayList);
        System.out.println("Javier debug2 selectedIndex" + selectedIndex);


        return true;
    }

    /**
     * Populates the class variables with the views created from the
     * xml layout file.
     */
    private void retrieveViews() {
        loadingBar = (ProgressBar) findViewById(R.id.audio_player_loading);
        artworkView = (ImageView) findViewById(R.id.audio_player_image);

        currentPositionView = (TextView) findViewById(R.id.audio_player_position);
        durationView = (TextView) findViewById(R.id.audio_player_duration);

        seekBar = (SeekBar) findViewById(R.id.audio_player_seek);

        previousButton = (ImageButton) findViewById(R.id.audio_player_previous);
        playPauseButton = (ImageButton) findViewById(R.id.audio_player_play_pause);
        nextButton = (ImageButton) findViewById(R.id.audio_player_next);

        title = (TextView) findViewById(R.id.title);
        subtitle = (TextView) findViewById(R.id.subtitle);
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

//                System.out.println("Javier current position " + playlistManager.getCurrentPosition());
//                playlistManager.setCurrentPosition(playlistManager.getCurrentPosition() + 1);
//                System.out.println("Javier  new current position " + playlistManager.getCurrentPosition());
//                getVideoURl(playlistManager.getCurrentItem().getVideoId(), true);
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

        //bad hack, change Javier
        if (!(mediaItemsArrayList == null)) {
//            if (forceStart || playlistManager.getCurrentPosition() != selectedIndex) {
            playlistManager.setParameters(mediaItemsArrayList, selectedIndex);
            playlistManager.play(0, false);
//            getVideoURl(playlistManager.getCurrentItem().getVideoId(), false);
//            }
        } else {

            playlistManager = App.getPlaylistManager();
            mediaItemsArrayList = (ArrayList<MediaItem>) App.getPlaylistManager().getItems();
            selectedIndex = App.getPlaylistManager().getCurrentPosition();
            playlistManager.setParameters(mediaItemsArrayList, selectedIndex);
            playlistManager.setId(PLAYLIST_ID);

            System.out.println("Javier tocando " + playlistManager.getCurrentItem() );
            System.out.println("Javier tocando2  " + mediaItemsArrayList);
        }

        updateTitles();



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

    private void getVideoURl(final String videoId, final boolean next) {
        if (videoId == null) {
            throw new IllegalArgumentException("null videoid");
        }

        System.out.println("Javier getVideoURL " + videoId + " NEXT " + next);
        loadingBar.setVisibility(View.VISIBLE);
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
                List<Format> formats = videoUrl.getInfo().getFormats();
                Format format = formats.get(0);
                if (format.getFormat().contains("audio only")) {
                    String audioUrl = format.getUrl();
                    System.out.println("Javier YoutubeVO url " + audioUrl);
                    System.out.println("Javier  mediaItems " + mediaItemsArrayList);
                    Samples.Sample sample = new Samples.Sample(videoUrl.getInfo().getTitle(), audioUrl, videoUrl.getInfo().getThumbnails().get(0).getUrl());

                    int position = playlistManager.getCurrentPosition();


                    System.out.println("Javier playing videoId  " + videoId);
                    mediaItemsArrayList.set(position, new MediaItem(sample, true, videoId));


                    playlistManager.setParameters(mediaItemsArrayList, next ? position - 1 : position);

                    System.out.println("Javier playing position  " + playlistManager.getCurrentPosition());

                    for (MediaItem mediaItem : mediaItemsArrayList) {
                        System.out.println("Javier mediaItem " + mediaItem.getTitle() + " url " + mediaItem.getMediaUrl());
                    }


                    if (!next) {
                        playlistManager.play(0, false);
                    } else {
                        System.out.println("Javier invoke next");
                        playlistManager.invokeNext();
                    }
                }
            }
        });
    }

    private void updateTitles() {
        if (playlistManager.getCurrentItem() != null) {
            title.setText(playlistManager.getCurrentItem().getTitle());
            subtitle.setText(playlistManager.getCurrentItem().getArtist());
        }

    }





}