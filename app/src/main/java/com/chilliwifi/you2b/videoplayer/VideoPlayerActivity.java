package com.chilliwifi.you2b.videoplayer;

import android.app.Activity;
import android.os.Bundle;

import com.chilliwifi.you2b.App;
import com.chilliwifi.you2b.R;
import com.chilliwifi.you2b.SampleModule;
import com.chilliwifi.you2b.searchyou2b.DaggerSearchYouTubeComponent;
import com.chilliwifi.you2b.searchyou2b.SearchYouTubeComponent;
import com.chilliwifi.you2b.videoplayer.data.MediaItem;
import com.chilliwifi.you2b.videoplayer.data.Samples;
import com.chilliwifi.you2b.videoplayer.manager.PlaylistManager;
import com.chilliwifi.you2b.videoplayer.playlist.VideoApi;
import com.chilliwifi.you2b.videourl.VideoUrlApi;
import com.chilliwifi.you2b.videourl.model.VideoUrl;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;
import com.devbrackets.android.playlistcore.manager.BasePlaylistManager;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class VideoPlayerActivity extends Activity {
    public static final String EXTRA_INDEX = "EXTRA_INDEX";
    public static final String EXTRA_VIDEO_ID = "EXTRA_VIDEO_ID";
    public static final int PLAYLIST_ID = 6; //Arbitrary, for the example (different from audio)

    protected EMVideoView emVideoView;
    protected PlaylistManager playlistManager;

    protected int selectedIndex;
    protected boolean pausedInOnStop = false;

    protected String videoId;


    @Inject
    SearchYouTubeComponent searchYouTubeComponent;
    VideoUrlApi videoUrlApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player_activity);

        injectDependencies();

        retrieveExtras();

        System.out.println("Javier videoid " + videoId);
        getVideoURl(videoId);
        init();
    }


    protected void injectDependencies() {
        searchYouTubeComponent =
                DaggerSearchYouTubeComponent.builder().sampleModule(new SampleModule(getApplicationContext())).build();
        searchYouTubeComponent.inject(this);


        videoUrlApi = searchYouTubeComponent.videoUrlApi();

    }

    @Override
    protected void onStop() {
        super.onStop();
//        if (emVideoView.isPlaying()) {
//            pausedInOnStop = true;
//            emVideoView.pause();
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (pausedInOnStop) {
            emVideoView.start();
            pausedInOnStop = false;
            System.out.println("Javier start ");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        playlistManager.invokeStop();
    }

    /**
     * Retrieves the extra associated with the selected playlist index
     * so that we can start playing the correct item.
     */
    protected void retrieveExtras() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            videoId = extras.getString(EXTRA_VIDEO_ID, "");
        }

        selectedIndex = 0;
    }

    protected void init() {

        emVideoView = (EMVideoView) findViewById(R.id.video_play_activity_video_view);


        setupPlaylistManager();



}

    /**
     * Retrieves the playlist instance and performs any generation
     * of content if it hasn't already been performed.
     */
    private void setupPlaylistManager() {
        playlistManager = App.getPlaylistManager();


//        List<MediaItem> mediaItems = new LinkedList<>();
//        for (Samples.Sample sample : Samples.getVideoSamples()) {
//            MediaItem mediaItem = new MediaItem(sample, false);
//            mediaItems.add(mediaItem);
//        }
//        playlistManager.setParameters(mediaItems, selectedIndex);
//
        playlistManager.setAllowedMediaType(BasePlaylistManager.AUDIO | BasePlaylistManager.VIDEO);

        playlistManager.setId(PLAYLIST_ID);
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
                MediaItem mediaItem = new MediaItem(sample, false);
                mediaItems.add(mediaItem);
                playlistManager.setParameters(mediaItems, 0);
                play();
            }
        });
    }

    private void play() {
        playlistManager.setVideoPlayer(new VideoApi(emVideoView));
        playlistManager.play(0, false);

        String mediaUrl = playlistManager.getCurrentItem().getMediaUrl();
        System.out.println("Javier mediaurl " + mediaUrl);
    }
}
