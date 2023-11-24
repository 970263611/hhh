package com.dahuaboke.hhh.exception;

/**
 * author: dahua
 * date: 2023/11/24 15:08
 */
public class ClientTypeErrorException extends RuntimeException {

    public ClientTypeErrorException() {
        super();
    }

    public ClientTypeErrorException(String message) {
        super(message);
    }

    public ClientTypeErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientTypeErrorException(Throwable cause) {
        super(cause);
    }

    protected ClientTypeErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
