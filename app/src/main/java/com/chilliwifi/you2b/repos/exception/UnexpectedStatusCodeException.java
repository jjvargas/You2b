package com.chilliwifi.you2b.repos.exception;

public class UnexpectedStatusCodeException extends Exception {

    private int statusCode;

    public UnexpectedStatusCodeException(int statusCode) {
        super("An unexcpected http status code in http response: " + statusCode);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}