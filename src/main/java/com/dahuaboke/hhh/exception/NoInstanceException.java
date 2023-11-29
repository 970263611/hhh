package com.dahuaboke.hhh.exception;

/**
 * author: dahua
 * date: 2023/11/29 9:56
 */
public class NoInstanceException extends RuntimeException {

    public NoInstanceException() {
        super();
    }

    public NoInstanceException(String message) {
        super(message);
    }

    public NoInstanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoInstanceException(Throwable cause) {
        super(cause);
    }

    protected NoInstanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
