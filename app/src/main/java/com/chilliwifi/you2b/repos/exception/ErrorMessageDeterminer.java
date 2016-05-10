package com.chilliwifi.you2b.repos.exception;


import retrofit.RetrofitError;

public class ErrorMessageDeterminer {

  public String getErrorMessage(Throwable e, boolean pullToRefresh) {
    if (e instanceof RetrofitError && ((RetrofitError) e).getKind() == RetrofitError.Kind.NETWORK) {
      return "Network Error: Are you connected to the internet?";
    }

    return "An unknown error has occurred";
  }
}
