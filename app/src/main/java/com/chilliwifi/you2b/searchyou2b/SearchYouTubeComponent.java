package com.chilliwifi.you2b.searchyou2b;


import com.chilliwifi.you2b.SampleModule;
import com.chilliwifi.you2b.repos.ReposAdapter;
import com.chilliwifi.you2b.searchyou2b.view.SearchYouTubeFragment;
import com.chilliwifi.you2b.videoplayer.AudioPlayerActivity;
import com.chilliwifi.you2b.videoplayer.service.MediaService;
import com.chilliwifi.you2b.videourl.VideoUrlApi;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(
    modules = SampleModule.class)
public interface SearchYouTubeComponent {

  public void inject(SearchYouTubeFragment fragment);

  public void inject(AudioPlayerActivity audioPlayerActivity);

  public void inject(MediaService mediaService);

  public SearchYouTubeRxPresenter presenter();

  public ReposAdapter adapter();

  public VideoUrlApi videoUrlApi();
}
