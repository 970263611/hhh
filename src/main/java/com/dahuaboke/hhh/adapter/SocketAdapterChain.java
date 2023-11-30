package com.dahuaboke.hhh.adapter;

import com.dahuaboke.hhh.enums.SocketMethod;

import java.util.List;
import java.util.Map;

/**
 * author: dahua
 * date: 2023/11/30 9:47
 */
public interface SocketAdapterChain {

    void addMethodConvert(Class clazz, SocketMethod socketMethod);

    Map<Class, SocketMethod> getMethodConvert();

    void addAdapter(SocketAdapter socketMethod);

    List<SocketAdapter> getAdapters();
}
