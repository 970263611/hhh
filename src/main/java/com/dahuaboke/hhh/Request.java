package com.dahuaboke.hhh;

/**
 * author: dahua
 * date: 2023/11/24 14:27
 */
public class Request {

    private Object request;
    private Callback callback;

    public Request() {
    }

    public Request(Callback callback) {
        this.callback = callback;
    }

    public Request(Object request) {
        this.request = request;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
