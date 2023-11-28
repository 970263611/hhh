package com.dahuaboke.hhh.exception;

/**
 * author: dahua
 * date: 2023/11/28 16:49
 */
public class NeedRequestParamException extends RuntimeException {

    public NeedRequestParamException() {
        super();
    }

    public NeedRequestParamException(String message) {
        super(message);
    }

    public NeedRequestParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public NeedRequestParamException(Throwable cause) {
        super(cause);
    }

    protected NeedRequestParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
