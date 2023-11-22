package com.dahuaboke.hhh.model;

import com.dahuaboke.hhh.handler.RequestHandler;

/**
 * author: dahua
 * date: 2023/11/20 17:20
 */
public class HhhClientModel {

    private String name;
    private String url;
    private Class clazz;
    private RequestHandler requestHandler;

    public HhhClientModel() {
    }

    public HhhClientModel(String name, String url, Class clazz) {
        this.name = name;
        this.url = url;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public RequestHandler getRequestHandler() {
        return requestHandler;
    }

    public void setRequestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }
}
