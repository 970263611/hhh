package com.dahuaboke.hhh.http;

import com.dahuaboke.hhh.Request;
import com.dahuaboke.hhh.SocketContext;
import com.dahuaboke.hhh.codec.CodecConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * author: dahua
 * date: 2023/11/27 16:40
 */
public abstract class HttpDeleteBuilder implements HttpBuilder {

    @Autowired
    private CodecConverter codecConverter;

    @Override
    public Request build(SocketContext socketContext) {
        String url = socketContext.getUrl();
        String contentType = socketContext.getContentType();
        Object[] params = socketContext.getParams();
        String param = codecConverter.encode(params);
        Map<String, String> headers = socketContext.getHeaders();
        return buildDeleteRequest(url, headers, param, contentType);
    }

    protected abstract Request buildDeleteRequest(String url, Map<String, String> headers, String param, String contentType);
}