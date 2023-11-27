package com.dahuaboke.hhh;

import com.dahuaboke.hhh.handler.RequestHandler;

/**
 * author: dahua
 * date: 2023/11/27 18:37
 */
public class HhhConfig {

    private String name;
    private String url;
    private String contentType;
    private Class clazz;
    private boolean enableHttps;
    private RequestHandler requestHandler;

    public HhhConfig(String name, String url, String contentType, Class clazz, boolean enableHttps) {
        this.name = name;
        this.url = url;
        this.contentType = contentType;
        this.clazz = clazz;
        this.enableHttps = enableHttps;
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public boolean isEnableHttps() {
        return enableHttps;
    }

    public void setEnableHttps(boolean enableHttps) {
        this.enableHttps = enableHttps;
    }

    public RequestHandler getRequestHandler() {
        return requestHandler;
    }

    public void setRequestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }
}
