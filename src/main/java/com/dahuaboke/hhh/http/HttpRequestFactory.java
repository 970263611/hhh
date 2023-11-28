package com.dahuaboke.hhh.http;

import com.dahuaboke.hhh.Request;
import com.dahuaboke.hhh.RequestFactory;
import com.dahuaboke.hhh.SocketContext;

/**
 * author: dahua
 * date: 2023/11/24 14:05
 */
public abstract class HttpRequestFactory implements RequestFactory {

    @Override
    public Request createRequest(SocketContext socketContext) {
        return createHttpRequest(socketContext);
    }

    protected abstract Request createHttpRequest(SocketContext socketContext);
}