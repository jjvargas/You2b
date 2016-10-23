package com.chilliwifi.you2b.videoplayer.service;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chilliwifi.you2b.App;
import com.chilliwifi.you2b.MainActivity;
import com.chilliwifi.you2b.R;
import com.chilliwifi.you2b.SampleModule;
import com.chilliwifi.you2b.searchyou2b.DaggerSearchYouTubeComponent;
import com.chilliwifi.you2b.searchyou2b.SearchYouTubeComponent;
import com.chilliwifi.you2b.videoplayer.AudioPlayerActivity;
import com.chilliwifi.you2b.videoplayer.data.MediaItem;
import com.chilliwifi.you2b.videoplayer.manager.PlaylistManager;
import com.chilliwifi.you2b.videoplayer.playlist.AudioApi;
import com.chilliwifi.you2b.videoplayer.playlist.VideoApi;
import com.chilliwifi.you2b.videourl.VideoUrlApi;
import com.chilliwifi.you2b.videourl.model.Format;
import com.chilliwifi.you2b.videourl.model.VideoUrl;
import com.devbrackets.android.exomedia.EMAudioPlayer;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;
import com.devbrackets.android.exomedia.ui.widget.VideoControls;
import com.devbrackets.android.playlistcore.api.AudioPlayerApi;
import com.devbrackets.android.playlistcore.service.BasePlaylistService;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple service that extends {@link BasePlaylistService} in order to provide
 * the application specific information required.
 */
public class MediaService extends BasePlaylistService<MediaItem, PlaylistManager> {
    private static final int NOTIFICATION_ID = 1564; //Arbitrary
    private static final int FOREGROUND_REQUEST_CODE = 332; //Arbitrary
    private static final float AUDIO_DUCK_VOLUME = 0.1f;

    private Bitmap defaultLargeNotificationImage;
    private Bitmap largeNotificationImage;
    private Bitmap lockScreenArtwork;

    private NotificationTarget notificationImageTarget = new NotificationTarget();
    private LockScreenTarget lockScreenImageTarget = new LockScreenTarget();

    //Picasso is an image loading library (NOTE: google now recommends using glide for image loading)
    private Picasso picasso;

    @Inject
    SearchYouTubeComponent searchYouTubeComponent;
    VideoUrlApi videoUrlApi;


    @Override
    public void onCreate() {
        super.onCreate();
        picasso = Picasso.with(getApplicationContext());

        injectDependencies();
    }

    protected void injectDependencies() {
        searchYouTubeComponent = DaggerSearchYouTubeComponent.builder().sampleModule(new SampleModule(getApplicationContext())).build();
        searchYouTubeComponent.inject(this);
        videoUrlApi = searchYouTubeComponent.videoUrlApi();
    }

    @Override
    protected void performOnMediaCompletion() {
        performNext();
        immediatelyPause = false;
    }

    @NonNull
    @Override
    protected AudioPlayerApi getNewAudioPlayer() {
        return new AudioApi(new EMAudioPlayer(getApplicationContext()));
    }

    @Override
    protected int getNotificationId() {
        return NOTIFICATION_ID;
    }

    @Override
    protected float getAudioDuckVolume() {
        return AUDIO_DUCK_VOLUME;
    }

    @NonNull
    @Override
    protected PlaylistManager getPlaylistManager() {
        return App.getPlaylistManager();
    }

    @NonNull
    @Override
    protected PendingIntent getNotificationClickPendingIntent() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(getApplicationContext(), FOREGROUND_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    protected Bitmap getDefaultLargeNotificationImage() {
        if (defaultLargeNotificationImage == null) {
            defaultLargeNotificationImage = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }

        return defaultLargeNotificationImage;
    }

    @Nullable
    @Override
    protected Bitmap getDefaultLargeNotificationSecondaryImage() {
        return null;
    }

    @Override
    protected int getNotificationIconRes() {
        return R.mipmap.ic_launcher;
    }

    @Override
    protected int getRemoteViewIconRes() {
        return R.mipmap.ic_launcher;
    }

    @Override
    protected void updateLargeNotificationImage(int size, MediaItem playlistItem) {
        picasso.load(playlistItem.getThumbnailUrl()).into(notificationImageTarget);
    }

    @Override
    protected void updateRemoteViewArtwork(MediaItem playlistItem) {
        picasso.load(playlistItem.getArtworkUrl()).into(lockScreenImageTarget);
    }

    @Nullable
    @Override
    protected Bitmap getRemoteViewArtwork() {
        return lockScreenArtwork;
    }

    @Nullable
    @Override
    protected Bitmap getLargeNotificationImage() {
        return largeNotificationImage;
    }

    /**
     * Overridden to allow updating the Title, SubTitle, and description in
     * the EMVideoView (VideoControls)
     */
    @Override
    protected boolean playVideoItem() {
        if (super.playVideoItem()) {
            updateVideoControls();
            return true;
        }

        return false;
    }

    /**
     * Helper method used to verify we can access the EMVideoView#getVideoControls()
     * to update both the text and available next/previous buttons
     */
    private void updateVideoControls() {
        VideoApi videoApi = (VideoApi) getPlaylistManager().getVideoPlayer();
        if (videoApi == null) {
            return;
        }

        EMVideoView videoView = videoApi.getVideoView();
        if (videoView == null) {
            return;
        }

        VideoControls videoControls = videoView.getVideoControls();
        if (videoControls != null) {
            updateVideoControlsText(videoControls);
            updateVideoControlsButtons(videoControls);
        }
    }

    private void updateVideoControlsText(@NonNull VideoControls videoControls) {
        if (currentPlaylistItem != null) {
            videoControls.setTitle(currentPlaylistItem.getTitle());
            videoControls.setSubTitle(currentPlaylistItem.getAlbum());
            videoControls.setDescription(currentPlaylistItem.getArtist());
        }
    }

    private void updateVideoControlsButtons(@NonNull VideoControls videoControls) {
        videoControls.setPreviousButtonEnabled(getPlaylistManager().isPreviousAvailable());
        videoControls.setNextButtonEnabled(getPlaylistManager().isNextAvailable());
    }

    /**
     * A class used to listen to the loading of the large notification images and perform
     * the correct functionality to update the notification once it is loaded.
     * <p>
     * <b>NOTE:</b> This is a Picasso Image loader class
     */
    private class NotificationTarget implements Target {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            largeNotificationImage = bitmap;
            onLargeNotificationImageUpdated();
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            largeNotificationImage = null;
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            //Purposefully left blank
        }
    }

    /**
     * A class used to listen to the loading of the large lock screen images and perform
     * the correct functionality to update the artwork once it is loaded.
     * <p>
     * <b>NOTE:</b> This is a Picasso Image loader class
     */
    private class LockScreenTarget implements Target {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            lockScreenArtwork = bitmap;
            onRemoteViewArtworkUpdated();
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            lockScreenArtwork = null;
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            //Purposefully left blank
        }
    }

    @Override
    protected boolean playAudioItem() {
        stopVideoPlayback();
        initializeAudioPlayer();
        requestAudioFocus();

        mediaProgressPoll.update(audioPlayer);
        mediaProgressPoll.reset();

        final boolean isItemDownloaded = isDownloaded(currentPlaylistItem);
        //noinspection ConstantC§onditions - currentPlaylistItem and the audioPlayer are not null at this point



        final CountDownLatch startSignal = new CountDownLatch(1);



        System.out.println("Javier videoUrlApi " + videoUrlApi);

        final Uri[] trackUri = new Uri[1];

        Observable<VideoUrl> videoUrl = videoUrlApi.getVideoUrl(currentPlaylistItem.getVideoId());
        Observable<VideoUrl> videoUrlObservable = videoUrl.subscribeOn(Schedulers.io());
        //        .observeOn(AndroidSchedulers.mainThread());

        videoUrlObservable.subscribe(new Subscriber<VideoUrl>() {
            @Override
            public void onCompleted() {
                System.out.println("Javier YoutubeVO completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Javier YoutubeVO error " + e.toString());
                startSignal.countDown();
            }

            @Override
            public void onNext(VideoUrl videoUrl) {
                List<Format> formats = videoUrl.getInfo().getFormats();
                Format format = formats.get(0);
                if (format.getFormat().contains("audio only")) {
                    String audioUrl = format.getUrl();
                    System.out.println("Javier YoutubeVO url " + audioUrl);
                    trackUri[0] = Uri.parse(isItemDownloaded ? currentPlaylistItem.getDownloadedMediaUri() : audioUrl);
                    startSignal.countDown();
                }
            }
        });

        try {
            startSignal.await();
        } catch (InterruptedException e) {
            // ignore
        }

        if (trackUri[0] == null) {
            return false;
        }

        System.out.println("Javier afterLatch");
//         trackUri = Uri.parse(isItemDownloaded ? currentPlaylistItem.getDownloadedMediaUri() : currentPlaylistItem.getMediaUrl());

        audioPlayer.setDataSource(this, trackUri[0]);

        setPlaybackState(PlaybackState.PREPARING);
        setupAsForeground();

        audioPlayer.prepareAsync();

        // If we are streaming from the internet, we want to hold a Wifi lock, which prevents
        // the Wifi radio from going to sleep while the song is playing. If, on the other hand,
        // we are NOT streaming, we want to release the lock.
        updateWiFiLock(!isItemDownloaded);
        return true;
    }

}