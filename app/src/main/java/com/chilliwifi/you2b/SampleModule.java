package com.chilliwifi.you2b;

import android.content.Context;

import com.chilliwifi.you2b.repos.exception.ErrorMessageDeterminer;
import com.chilliwifi.you2b.searchyou2b.model.YouTubeApi;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

@Module(
)
public class SampleModule {

  private Context context;

  public SampleModule(Context context) {
    this.context = context;
  }

  @Provides public Context provideContext() {
    return context;
  }

  @Provides @Singleton Picasso providesPicasso() {
    return Picasso.with(context);
  }


  @Provides @Singleton public YouTubeApi providesYouTubeApi() {

    OkHttpClient client = new OkHttpClient();
    client.networkInterceptors().add(new StethoInterceptor());

    client.setCache(new Cache(context.getCacheDir(), 10 * 1024 * 1024));

    RestAdapter restAdapter = new RestAdapter.Builder()
            .setClient(new OkClient(client))
            .setEndpoint("https://www.googleapis" +
                    ".com/youtube/v3")
            .build();

    return restAdapter.create(YouTubeApi.class);
  }

  @Provides @Singleton
  public ErrorMessageDeterminer providesErrorMessageDeterminer(){
    return new ErrorMessageDeterminer();
  }

}
