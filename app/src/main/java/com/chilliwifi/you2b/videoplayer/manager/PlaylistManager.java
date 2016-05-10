package com.chilliwifi.you2b.videoplayer.manager;

import android.app.Application;
import android.app.Service;
import android.support.annotation.NonNull;

import com.chilliwifi.you2b.App;
import com.chilliwifi.you2b.videoplayer.data.MediaItem;
import com.chilliwifi.you2b.videoplayer.service.MediaService;
import com.devbrackets.android.playlistcore.manager.BasePlaylistManager;
import com.devbrackets.android.playlistcore.manager.ListPlaylistManager;

/**
 * A PlaylistManager that extends the {@link BasePlaylistManager} for use with the
 * {@link MediaService} which extends {@link com.devbrackets.android.playlistcore.service.BasePlaylistService}.
 */
public class PlaylistManager extends ListPlaylistManager<MediaItem> {

    @NonNull
    @Override
    protected Application getApplication() {
        return App.getApplication();
    }

    @NonNull
    @Override
    protected Class<? extends Service> getMediaServiceClass() {
        return MediaService.class;
    }
}
