
package com.chilliwifi.you2b;

import com.chilliwifi.you2b.mock.MockTestApi;
import com.chilliwifi.you2b.searchyou2b.model.YoutubeVO;
import com.chilliwifi.you2b.searchyou2b.view.SearchYouTubeView;

import junit.framework.Assert;

import org.junit.Test;

/** 
 * @author Hannes Dorfmann 
 */ 
public class MockMvpLceRxPresenterTest extends MvpLcePresenterTest<YoutubeVO, SearchYouTubeView, TestPresenter> {

  MockTestApi youTubeApi = new MockTestApi();
  private TestPresenter presenter = new TestPresenter();
  private YoutubeVO data = new YoutubeVO();

  @Override protected void beforeTestNotFailing() {
    presenter.setFail(false);
    presenter.setData(data);

  }

  @Override protected void beforeTestFailing() {
    presenter.setFail(true);
    youTubeApi.setReason(MockTestApi.FailReason.UNKNOWN);
  }

 
  @Override protected Class<SearchYouTubeView> getViewClass() {
    return SearchYouTubeView.class;
  } 
 
  @Override protected TestPresenter createPresenter() {
    return presenter;
  } 
 
  @Override protected void loadData(TestPresenter presenter, boolean pullToRefresh) {
    presenter.setTestMode(true);
    presenter.loadData(pullToRefresh);
  }
 
  @Override protected void verifyData(YoutubeVO data) {
    Assert.assertTrue(data == this.data);
  } 
 
  @Test
  public void testPresenter(){ 
    super.startLceTests(false);
  } 
} 