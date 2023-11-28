package com.dahuaboke.hhh.http;

import com.dahuaboke.hhh.Request;
import com.dahuaboke.hhh.SocketContext;

import java.util.Map;

/**
 * author: dahua
 * date: 2023/11/27 16:40
 */
public abstract class HttpGetBuilder extends AbstractHttpBuilder {

    @Override
    protected Request buildHttp(SocketContext socketContext) {
        String url = socketContext.getUrl();
        Map<String, String> headers = socketContext.getHeaders();
        return buildGetRequest(url, headers);
    }

    protected abstract Request buildGetRequest(String url, Map<String, String> headers);
}
