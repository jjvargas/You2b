package com.chilliwifi.you2b.mock;


import com.chilliwifi.you2b.searchyou2b.model.YouTubeApi;
import com.chilliwifi.you2b.searchyou2b.model.YoutubeVO;

import java.io.IOException;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.http.Query;
import rx.Observable;

public class MockTestApi implements YouTubeApi {

    @Override
    public void getVideos(@Query("maxResults") int maxResult, @Query("q") String text, @Query("key") String api_key, @Query("pageToken") String nextPage, Callback<YoutubeVO> callback) {
        if (!shouldFail) {
            callback.success((YoutubeVO) data, null);
        } else {
            RetrofitError error = null;
            switch (reason) {
                case NETWORK:
                    error = RetrofitError.networkError("mock url", new IOException("Mock Network Exception"));
                    break;
                case STATUS_CODE:
                    error = RetrofitError.httpError("mock url",
                            new Response("mock url", FailReason.httpcode, "Forbidden", new ArrayList<Header>(), null), null, null);
                    break;

                case UNKNOWN:
                default:
                    error = RetrofitError.unexpectedError("mock url", new Exception("Mock Exception"));
                    break;
            }
            callback.failure(error);
        }
    }

    @Override
    public Observable<YoutubeVO> getVideosRx(@Query("maxResults") int maxResult, @Query("q") String text, @Query("key") String api_key, @Query("pageToken") String nextPage) {
        Observable<YoutubeVO> observable;

        if (shouldFail) {
            observable = Observable.error(new Exception("Mock Exception"));
        } else {
            observable = Observable.just((YoutubeVO) data);

        }
        return observable;
    }

    @Override
    public Observable<YoutubeVO> getVideoUrl(@Query("url") String videoId) {
        return null;
    }

    public enum FailReason {
        UNKNOWN,
        NETWORK,
        STATUS_CODE;

        public static int httpcode = 403;
    }

    private FailReason reason;
    private boolean shouldFail;
    private Object data;


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public FailReason getReason() {
        return reason;
    }

    public void setReason(FailReason reason) {
        this.reason = reason;
    }

    public boolean isShouldFail() {
        return shouldFail;
    }

    public void setShouldFail(boolean shouldFail) {
        this.shouldFail = shouldFail;
    }
}