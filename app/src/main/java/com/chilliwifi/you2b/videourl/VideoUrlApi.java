package com.chilliwifi.you2b.videourl;

import com.chilliwifi.you2b.videourl.model.VideoUrl;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface VideoUrlApi {


//    https://youtube-dl.appspot.com/api/info?url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DIZSmX3G-IRk&flatten=false

    @GET("/api/info")
    Observable<VideoUrl> getVideoUrl(@Query("url") String videoId);



}
