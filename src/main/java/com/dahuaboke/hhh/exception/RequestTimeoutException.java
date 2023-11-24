package com.dahuaboke.hhh.exception;

/**
 * author: dahua
 * date: 2023/11/24 15:53
 */
public class RequestTimeoutException extends RequestException {

    public RequestTimeoutException() {
        super();
    }

    public RequestTimeoutException(String message) {
        super(message);
    }

    public RequestTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestTimeoutException(Throwable cause) {
        super(cause);
    }

    protected RequestTimeoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
