package com.chilliwifi.you2b.searchyou2b;

import com.chilliwifi.you2b.searchyou2b.model.YouTubeApi;
import com.chilliwifi.you2b.searchyou2b.model.YoutubeVO;
import com.chilliwifi.you2b.searchyou2b.rx.MvpLceRxPresenter;
import com.chilliwifi.you2b.searchyou2b.view.SearchYouTubeView;

import javax.inject.Inject;

import rx.Observable;

public class SearchYouTubeRxPresenter extends MvpLceRxPresenter<SearchYouTubeView, YoutubeVO> {

    YouTubeApi youTubeApi;

    public String searchTerm = "Radiohead";

    @Inject
    public SearchYouTubeRxPresenter(YouTubeApi api) {
        this.youTubeApi = api;
    }

    public void getVideosRx(boolean pullToRefresh) {
        Observable<YoutubeVO> videosRx = youTubeApi.getVideosRx(20, searchTerm, Constants.DEVELOPER_KEY, "");
        subscribe(videosRx, pullToRefresh);
    }



}