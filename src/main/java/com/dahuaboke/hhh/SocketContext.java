package com.dahuaboke.hhh;

import com.dahuaboke.hhh.adapter.SocketAdapter;
import com.dahuaboke.hhh.handler.RequestHandler;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * author: dahua
 * date: 2023/11/20 17:20
 */
public class SocketContext {

    private String name;
    private String url;
    private String contentType;
    private Class clazz;
    private String param;
    private RequestHandler requestHandler;
    private Callback callback;
    private Method method;
    private static final List<SocketAdapter> socketAdapters = new ArrayList();

    public SocketContext() {
    }

    public SocketContext(String name, String url, String contentType, Class clazz) {
        this.name = name;
        this.url = url;
        this.contentType = contentType;
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

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public RequestHandler getRequestHandler() {
        return requestHandler;
    }

    public void setRequestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public static List<SocketAdapter> getSocketAdapters() {
        return socketAdapters;
    }

    public static void setSocketAdapter(SocketAdapter socketAdapter) {
        socketAdapters.add(socketAdapter);
    }
}
