package com.chilliwifi.you2b.base;

import com.chilliwifi.you2b.repos.exception.NetworkException;
import com.chilliwifi.you2b.repos.exception.UnexpectedStatusCodeException;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LceRetrofitPresenter<V extends MvpLceView<M>, M> extends MvpBasePresenter<V> {


  protected class LceCallback implements Callback<M> {

    private final boolean pullToRefresh;

    public LceCallback(boolean pullToRefresh) {
      this.pullToRefresh = pullToRefresh;
      if (isViewAttached()) {
        getView().showLoading(pullToRefresh);
      }
    }

    @Override public void success(M m, Response response) {
      if (isViewAttached()) {
        getView().setData(m);
        getView().showContent();
      }
    }

    @Override public void failure(RetrofitError error) {
      if (isViewAttached()) {
        Throwable t;
        if (error.getKind() == RetrofitError.Kind.HTTP && error.getResponse() != null) {
          t = new UnexpectedStatusCodeException(error.getResponse().getStatus());
        } else if (error.getKind() == RetrofitError.Kind.NETWORK) {
          t = new NetworkException(error.getCause());
        } else {
          t = error.getCause();
        }

        getView().showError(t, pullToRefresh);
      }
    }
  }
}