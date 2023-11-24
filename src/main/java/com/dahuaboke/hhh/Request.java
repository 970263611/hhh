package com.dahuaboke.hhh;

/**
 * author: dahua
 * date: 2023/11/24 14:27
 */
public class Request {

    private Object requestParam;
    private Callback callback;

    public Request() {
    }

    public Request(Callback callback) {
        this.callback = callback;
    }

    public Request(Object requestParam) {
        this.requestParam = requestParam;
    }

    public Object getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(Object requestParam) {
        this.requestParam = requestParam;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
