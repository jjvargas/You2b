package com.chilliwifi.you2b;

import com.chilliwifi.you2b.mock.MockTestApi;
import com.chilliwifi.you2b.searchyou2b.SearchYouTubeRxPresenter;
import com.chilliwifi.you2b.searchyou2b.model.YoutubeVO;
import com.chilliwifi.you2b.searchyou2b.view.SearchYouTubeView;

import junit.framework.Assert;

import org.junit.Test; 
 
/** 
 * @author Hannes Dorfmann 
 */ 
public class SearchMvpLceRxPresenterTest extends MvpLcePresenterTest<YoutubeVO, SearchYouTubeView, SearchYouTubeRxPresenter> {

  MockTestApi youTubeApi = new MockTestApi();
  private SearchYouTubeRxPresenter presenter = new SearchYouTubeRxPresenter(youTubeApi);
  private YoutubeVO data = new YoutubeVO();

  @Override protected void beforeTestNotFailing() {
    youTubeApi.setShouldFail(false);
    youTubeApi.setData(data);
  }

  @Override protected void beforeTestFailing() {
    youTubeApi.setShouldFail(true);
    youTubeApi.setReason(MockTestApi.FailReason.UNKNOWN);
  }

 
  @Override protected Class<SearchYouTubeView> getViewClass() {
    return SearchYouTubeView.class;
  } 
 
  @Override protected SearchYouTubeRxPresenter createPresenter() {
    return presenter;
  } 
 
  @Override protected void loadData(SearchYouTubeRxPresenter presenter, boolean pullToRefresh) {
//    presenter.loadData(pullToRefresh);
    presenter.setTestMode(true);
    presenter.getVideosRx(pullToRefresh);
  } 
 
  @Override protected void verifyData(YoutubeVO data) {
    Assert.assertTrue(data == this.data);
  } 
 
  @Test
  public void testPresenter(){ 
    super.startLceTests(false);
  } 
} 