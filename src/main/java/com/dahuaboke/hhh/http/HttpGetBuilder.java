package com.dahuaboke.hhh.http;

import com.dahuaboke.hhh.Request;
import com.dahuaboke.hhh.SocketContext;
import com.dahuaboke.hhh.codec.CodecConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * author: dahua
 * date: 2023/11/27 16:40
 */
public abstract class HttpGetBuilder implements HttpBuilder {

    @Autowired
    private CodecConverter codecConverter;

    @Override
    public Request build(SocketContext socketContext) {
        Method method = socketContext.getMethod();
        Parameter[] parameters = method.getParameters();
        Object[] params = socketContext.getParams();
        Assert.isTrue(parameters.length == params.length, "params length error");
        StringBuffer stringBuffer = new StringBuffer("?");
        for (int i = 0; i < parameters.length; i++) {
            stringBuffer.append(parameters[i].getName());
            stringBuffer.append("=");
            stringBuffer.append(codecConverter.encode(params[i]));
            stringBuffer.append("&");
        }
        String param = stringBuffer.substring(0, stringBuffer.length() - 1);
        String url = socketContext.getUrl();
        Map<String, String> headers = socketContext.getHeaders();
        return buildGetRequest(url, headers, param);
    }

    protected abstract Request buildGetRequest(String url, Map<String, String> headers, String param);
}
