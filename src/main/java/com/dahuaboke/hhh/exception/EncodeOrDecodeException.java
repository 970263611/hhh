package com.dahuaboke.hhh.exception;

/**
 * author: dahua
 * date: 2023/11/24 14:51
 */
public class EncodeOrDecodeException extends RuntimeException {

    public EncodeOrDecodeException() {
        super();
    }

    public EncodeOrDecodeException(String message) {
        super(message);
    }

    public EncodeOrDecodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncodeOrDecodeException(Throwable cause) {
        super(cause);
    }

    protected EncodeOrDecodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
