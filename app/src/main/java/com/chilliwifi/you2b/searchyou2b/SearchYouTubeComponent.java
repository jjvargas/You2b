package com.chilliwifi.you2b.searchyou2b;


import com.chilliwifi.you2b.SampleModule;
import com.chilliwifi.you2b.repos.ReposAdapter;
import com.chilliwifi.you2b.searchyou2b.view.SearchYouTubeFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(
    modules = SampleModule.class)
public interface SearchYouTubeComponent {

  public void inject(SearchYouTubeFragment fragment);

  public SearchYouTubeRxPresenter presenter();

  public ReposAdapter adapter();
}
