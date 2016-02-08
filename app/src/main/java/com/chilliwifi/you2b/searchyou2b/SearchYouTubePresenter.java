package com.chilliwifi.you2b.searchyou2b;

import com.chilliwifi.you2b.base.LceRetrofitPresenter;
import com.chilliwifi.you2b.searchyou2b.model.YouTubeApi;
import com.chilliwifi.you2b.searchyou2b.model.YoutubeVO;
import com.chilliwifi.you2b.searchyou2b.view.SearchYouTubeView;

import javax.inject.Inject;

public class SearchYouTubePresenter extends LceRetrofitPresenter<SearchYouTubeView, YoutubeVO> {

    YouTubeApi youTubeApi;

    public String searchTerm="Muse";

    @Inject
    public SearchYouTubePresenter(YouTubeApi api) {
        this.youTubeApi = api;
    }

    public void getVideos(boolean pullToRefresh) {
        youTubeApi.getVideos(20, searchTerm, Constants.DEVELOPER_KEY,
                "", new LceCallback(pullToRefresh));
    }

}