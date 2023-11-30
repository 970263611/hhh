package com.dahuaboke.hhh.adapter.http;

import com.dahuaboke.hhh.adapter.SocketAdapter;
import com.dahuaboke.hhh.adapter.SocketAdapterChain;
import com.dahuaboke.hhh.enums.SocketMethod;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: dahua
 * date: 2023/11/30 9:48
 */
@Component
public class HttpAdapterChain implements SocketAdapterChain {

    private List<SocketAdapter> adapters;
    private Map<Class, SocketMethod> httpMethodAnnotationClasses;

    public HttpAdapterChain() {
        adapters = new ArrayList();
        this.httpMethodAnnotationClasses = new HashMap();
    }

    @Override
    public void addMethodConvert(Class clazz, SocketMethod socketMethod) {
        httpMethodAnnotationClasses.put(clazz, socketMethod);
    }

    @Override
    public Map<Class, SocketMethod> getMethodConvert() {
        return httpMethodAnnotationClasses;
    }

    @Override
    public void addAdapter(SocketAdapter socketAdapter) {
        adapters.add(socketAdapter);
    }

    @Override
    public List<SocketAdapter> getAdapters() {
        return adapters;
    }
}
