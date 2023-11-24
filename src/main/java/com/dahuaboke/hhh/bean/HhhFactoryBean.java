package com.dahuaboke.hhh.bean;

import com.dahuaboke.hhh.handler.DirectHandler;
import com.dahuaboke.hhh.handler.LoadBalancerHandler;
import com.dahuaboke.hhh.handler.RequestHandler;
import com.dahuaboke.hhh.SocketContext;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Proxy;

/**
 * author: dahua
 * date: 2023/11/20 13:20
 */
public class HhhFactoryBean implements FactoryBean {

    private SocketContext socketContext;

    public HhhFactoryBean(SocketContext socketContext) {
        this.socketContext = socketContext;
    }

    @Override
    public Object getObject() {
        RequestHandler requestHandler;
        String key = socketContext.getUrl();
        if (StringUtils.isEmpty(key)) {
            key = socketContext.getName();
            Assert.isNull(key, "param name and url can not all empty");
            requestHandler = new LoadBalancerHandler();
        } else {
            requestHandler = new DirectHandler();
        }
        Class clazz = socketContext.getClazz();
        socketContext.setRequestHandler(requestHandler);
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new ProxyInstance(socketContext));
    }

    @Override
    public Class<?> getObjectType() {
        return socketContext.getClazz();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
