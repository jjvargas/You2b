package com.chilliwifi.you2b.searchyou2b.model;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface YouTubeApi {

    @GET("/search?part=snippet&type=video")
    void getVideos(@Query("maxResults") int maxResult, @Query("q") String text, @Query
            ("key") String api_key, @Query("pageToken") String nextPage, Callback<YoutubeVO> callback);

    @GET("/search?part=snippet&type=video")
    Observable<YoutubeVO> getVideosRx(@Query("maxResults") int maxResult, @Query("q") String text, @Query
            ("key") String api_key, @Query("pageToken") String nextPage);


    @GET("/api/info")
    Observable<YoutubeVO> getVideoUrl(@Query("url") String videoId);

}
