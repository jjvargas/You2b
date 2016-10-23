package com.chilliwifi.you2b.searchyou2b.playlists;


import com.chilliwifi.you2b.SampleModule;
import com.chilliwifi.you2b.repos.ReposAdapter;
import com.chilliwifi.you2b.searchyou2b.model.YouTubeApi;
import com.chilliwifi.you2b.videourl.VideoUrlApi;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(
    modules = SampleModule.class)
public interface PlaylistItemsComponent {

  public void inject(PlaylistItemsFragment fragment);

  public PlaylistItemsRxPresenter presenter();

  public PlaylistAdapter adapter();

  public YouTubeApi youTubeApi();
}
