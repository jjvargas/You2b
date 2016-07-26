package com.chilliwifi.you2b;

import android.app.Application;

import com.chilliwifi.you2b.videoplayer.manager.PlaylistManager;
import com.facebook.stetho.Stetho;

public class App extends Application {

    private static App application;
    private static PlaylistManager playlistManager;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
        playlistManager = new PlaylistManager();
        Stetho.initializeWithDefaults(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        System.out.println("Javier on terminate");

        application = null;
        playlistManager = null;
    }

    public static PlaylistManager getPlaylistManager() {
        return playlistManager;
    }

    public static App getApplication() {
        return application;
    }
}