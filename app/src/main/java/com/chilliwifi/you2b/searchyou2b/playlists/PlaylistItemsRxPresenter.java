package com.chilliwifi.you2b.searchyou2b.playlists;

import com.chilliwifi.you2b.searchyou2b.Constants;
import com.chilliwifi.you2b.searchyou2b.model.YouTubeApi;
import com.chilliwifi.you2b.searchyou2b.model.YoutubeVO;
import com.chilliwifi.you2b.searchyou2b.playlists.model.Playlists;
import com.chilliwifi.you2b.searchyou2b.rx.MvpLceRxPresenter;

import javax.inject.Inject;

import rx.Observable;

public class PlaylistItemsRxPresenter extends MvpLceRxPresenter<PlaylistItemsView, Playlists> {

    YouTubeApi youTubeApi;

//    public String playlistId = "PLFOZdcG2_1jStMa-ebhb6Vq_5f1ycmQhB"; //Radiohead Boston
    public String playlistId = "FL00lYuhX8STBAD-A3Adx2Lw"; //Favourites

    @Inject
    public PlaylistItemsRxPresenter(YouTubeApi api) {
        this.youTubeApi = api;
    }

    public void getVideosRx(boolean pullToRefresh) {
        Observable<Playlists> videosRx = youTubeApi.getPlaylistItems(40, playlistId, Constants.DEVELOPER_KEY);
        subscribe(videosRx, pullToRefresh);
    }



}