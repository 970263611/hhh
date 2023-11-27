package com.dahuaboke.hhh;

import com.dahuaboke.hhh.adapter.SocketAdapter;
import com.dahuaboke.hhh.handler.RequestHandler;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * author: dahua
 * date: 2023/11/20 17:20
 */
public class SocketContext {

    private String name;
    private String url;
    private String contentType;
    private Class clazz;
    private RequestHandler requestHandler;
    private Callback callback;
    private Method method;
    private static final List<SocketAdapter> socketAdapters = new ArrayList();
    private volatile SocketAdapter useSocketAdapter;
    private boolean enableHttps;

    private String uri;
    private Object[] params;
    private Map<String, String> headers = new LinkedHashMap();

    public SocketContext() {
    }

    public SocketContext(String name, String url, String contentType, Class clazz, boolean enableHttps) {
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

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
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

    public SocketAdapter getUseSocketAdapter() {
        return useSocketAdapter;
    }

    public void setUseSocketAdapter(SocketAdapter useSocketAdapter) {
        this.useSocketAdapter = useSocketAdapter;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setHeader(String k, String v) {
        headers.put(k, v);
    }

    public boolean isEnableHttps() {
        return enableHttps;
    }

    public void setEnableHttps(boolean enableHttps) {
        this.enableHttps = enableHttps;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
