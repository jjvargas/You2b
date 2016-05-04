package com.chilliwifi.you2b;

import com.chilliwifi.you2b.mock.MockTestApi;
import com.chilliwifi.you2b.repos.exception.NetworkException;
import com.chilliwifi.you2b.repos.exception.UnexpectedStatusCodeException;
import com.chilliwifi.you2b.searchyou2b.SearchYouTubePresenter;
import com.chilliwifi.you2b.searchyou2b.model.YoutubeVO;
import com.chilliwifi.you2b.searchyou2b.view.SearchYouTubeView;
import com.hannesdorfmann.mosby.testing.presenter.MvpLcePresenterTest;

import org.junit.Assert;
import org.junit.Test;

public class LceRetrofitPresenterTest extends MvpLcePresenterTest<YoutubeVO, SearchYouTubeView, SearchYouTubePresenter> {

  private MockTestApi youTubeApi = new MockTestApi();
  private SearchYouTubePresenter presenter = new SearchYouTubePresenter(youTubeApi);
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

  @Override protected SearchYouTubePresenter createPresenter() {
    return presenter;
  }

  @Override protected void loadData(SearchYouTubePresenter presenter, boolean pullToRefresh) {
    presenter.getVideos(pullToRefresh);
  }

  @Override
  protected void verifyData(YoutubeVO data) {
    Assert.assertTrue(data == this.data);
  }

  @Test
  public void testPresenter() {
    super.startLceTests(false);
  }

  @Test public void testNetworkException() {
    youTubeApi.setShouldFail(true);
    youTubeApi.setReason(MockTestApi.FailReason.NETWORK);

    SearchYouTubeView view = new SearchYouTubeView() {

      @Override public void showError(Throwable e, boolean pullToRefresh) {
        Assert.assertTrue(e instanceof NetworkException);
      }

      @Override
      public void setData(YoutubeVO data) {
        Assert.fail("setData() called, but should not have been called");
      }

      @Override
      public void loadData(boolean pullToRefresh) {

      }

      @Override public void showLoading(boolean pullToRefresh) {
        // Not to check
      }

      @Override public void showContent() {
        Assert.fail("showContent() called, but should not have been called");
      }

    };

    presenter.attachView(view);
    presenter.getVideos(false);
    presenter.getVideos(true);
  }

  @Test public void testStatusCodeException() {
    youTubeApi.setShouldFail(true);
    youTubeApi.setReason(MockTestApi.FailReason.STATUS_CODE);

    SearchYouTubeView view = new SearchYouTubeView() {

      @Override public void showError(Throwable e, boolean pullToRefresh) {
        Assert.assertTrue(e instanceof UnexpectedStatusCodeException);
        Assert.assertEquals(((UnexpectedStatusCodeException) e).getStatusCode(),
            MockTestApi.FailReason.httpcode);
      }

      @Override
      public void setData(YoutubeVO data) {
        Assert.fail("setData() called, but should not have been called");
      }

      @Override public void showLoading(boolean pullToRefresh) {
        // Not to check
      }

      @Override public void showContent() {
        Assert.fail("showContent() called, but should not have been called");
      }

      @Override public void loadData(boolean pullToRefresh) {
      }
    };

    presenter.attachView(view);
    presenter.getVideos(false);
    presenter.getVideos(true);
  }
}