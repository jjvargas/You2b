package com.chilliwifi.you2b.repos.exception;

public class NetworkException extends Exception {

  public NetworkException() {
  }

  public NetworkException(String detailMessage) {
    super(detailMessage);
  }

  public NetworkException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

  public NetworkException(Throwable throwable) {
    super(throwable);
  }
}