package com.chilliwifi.you2b;

import com.chilliwifi.you2b.searchyou2b.model.YoutubeVO;
import com.chilliwifi.you2b.searchyou2b.rx.MvpLceRxPresenter;
import com.chilliwifi.you2b.searchyou2b.view.SearchYouTubeView;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author Hannes Dorfmann
 */
public class TestPresenter extends MvpLceRxPresenter<SearchYouTubeView, YoutubeVO> {

  private Object data;
  private boolean fail;

  public void setData(Object data) {
    this.data = data;
  }

  public void setFail(boolean fail) {
    this.fail = fail;
  }

  public void loadData(boolean pullToRefresh) {
    System.out.println("Javier loadData");
    Observable<YoutubeVO> observable;

    if (fail) {
      observable = Observable.error(new Exception("Mock Exception"));
    } else {
      observable = Observable.just((YoutubeVO)data);
    }

    System.out.println("Javier subscribe in test presenter");
    subscribe(observable, pullToRefresh);
  }

//  @Override
  protected Observable<Object> applyScheduler(Observable<Object> observable) {
    return observable.subscribeOn(Schedulers.immediate()).observeOn(Schedulers.immediate());
  }
}