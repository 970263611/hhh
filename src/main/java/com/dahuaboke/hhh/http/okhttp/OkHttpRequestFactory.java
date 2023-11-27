package com.dahuaboke.hhh.http.okhttp;

import com.dahuaboke.hhh.Request;
import com.dahuaboke.hhh.SocketContext;
import com.dahuaboke.hhh.adapter.SocketAdapter;
import com.dahuaboke.hhh.adapter.http.HttpAdapter;
import com.dahuaboke.hhh.http.HttpBuilder;
import com.dahuaboke.hhh.http.HttpRequestFactory;
import com.dahuaboke.hhh.utils.SpringUtils;

/**
 * author: dahua
 * date: 2023/11/24 14:09
 */
public class OkHttpRequestFactory extends HttpRequestFactory {

    @Override
    public Request createHttpRequest(SocketContext socketContext) {
        SocketAdapter useSocketAdapter = socketContext.getUseSocketAdapter();
        if (useSocketAdapter == null) {
            throw new IllegalArgumentException("can not find match adapter");
        }
        HttpAdapter httpAdapter = (HttpAdapter) useSocketAdapter;
        Class<? extends HttpBuilder> aClass = httpAdapter.matchHttpBuilder();
        HttpBuilder httpBuilder = SpringUtils.getBean(aClass);
        return httpBuilder.build(socketContext);
    }
}
