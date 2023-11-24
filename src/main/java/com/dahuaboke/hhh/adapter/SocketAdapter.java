package com.dahuaboke.hhh.adapter;

import java.lang.reflect.Method;

/**
 * author: dahua
 * date: 2023/11/24 16:37
 */
public interface SocketAdapter {

    boolean match(Method method);

    void register();

    String getUri(Method method);
}
