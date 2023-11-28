package com.dahuaboke.hhh.http;

import com.dahuaboke.hhh.Request;
import com.dahuaboke.hhh.SocketContext;

import java.util.Map;

/**
 * author: dahua
 * date: 2023/11/27 16:40
 */
public abstract class HttpPostBuilder extends AbstractHttpBuilder {

    @Override
    protected Request buildHttp(SocketContext socketContext) {
        String url = socketContext.getUrl();
        Map<String, String> headers = socketContext.getHeaders();
        String body = socketContext.getBody();
        String contentType = socketContext.getContentType();
        return buildPostRequest(url, headers, body, contentType);
    }

    protected abstract Request buildPostRequest(String url, Map<String, String> headers, String body, String contentType);
}
