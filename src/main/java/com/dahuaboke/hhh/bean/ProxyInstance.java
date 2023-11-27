package com.dahuaboke.hhh.bean;

import com.dahuaboke.hhh.HhhConfig;
import com.dahuaboke.hhh.codec.CodecConverter;
import com.dahuaboke.hhh.exception.RequestException;
import com.dahuaboke.hhh.exception.RequestTimeoutException;
import com.dahuaboke.hhh.property.HhhProperties;
import com.dahuaboke.hhh.utils.SpringUtils;

import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * author: dahua
 * date: 2023/11/20 12:20
 */
public class ProxyInstance implements InvocationHandler {

    private HhhConfig hhhConfig;

    public ProxyInstance(HhhConfig hhhConfig) {
        this.hhhConfig = hhhConfig;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        long beginTime = System.currentTimeMillis();
        CompletableFuture completableFuture = new CompletableFuture();
        HhhProperties hhhProperties = SpringUtils.getBean(HhhProperties.class);
        int requestTimeout = hhhProperties.getRequestTimeout();
        CodecConverter codecConverter = SpringUtils.getBean(CodecConverter.class);
        socketContext.setParams(args);
        com.dahuaboke.hhh.Callback callback = new com.dahuaboke.hhh.Callback() {
            @Override
            public void onSuccess(InputStream is) {
                completableFuture.complete(is);
            }

            @Override
            public void onFailure(Exception e) {
                completableFuture.complete(e);
            }
        };
        socketContext.setCallback(callback);
        socketContext.setMethod(method);
        socketContext.getRequestHandler().handler(socketContext);
        long nowTime = System.currentTimeMillis();
        long userTime = nowTime - beginTime;
        if (userTime >= requestTimeout) {
            throw new RequestTimeoutException();
        }
        Object res;
        try {
            long lastTime = requestTimeout - userTime;
            res = completableFuture.get(lastTime, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            throw new RequestTimeoutException(e);
        } catch (Exception e) {
            throw new RequestException(e);
        }
        if (res instanceof Exception) {
            throw new RequestException((Exception) res);
        }
        InputStream is = (InputStream) res;
        Type type = method.getGenericReturnType();
        return codecConverter.decode(is, type, socketContext.getClazz());
    }
}
