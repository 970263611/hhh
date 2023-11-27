package com.dahuaboke.hhh.bean;

import com.dahuaboke.hhh.HhhConfig;
import com.dahuaboke.hhh.SocketContext;
import com.dahuaboke.hhh.handler.DirectHandler;
import com.dahuaboke.hhh.handler.LoadBalancerHandler;
import com.dahuaboke.hhh.handler.RequestHandler;
import com.dahuaboke.hhh.property.HhhProperties;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Proxy;

/**
 * author: dahua
 * date: 2023/11/20 13:20
 */
public class HhhFactoryBean implements FactoryBean {


    private HhhConfig hhhConfig;

    public HhhFactoryBean(HhhConfig hhhConfig) {
        this.hhhConfig = hhhConfig;
    }

    @Override
    public Object getObject() {
        RequestHandler requestHandler;
        String key = hhhConfig.getUrl();
        if (StringUtils.isEmpty(key)) {
            key = hhhConfig.getName();
            Assert.isNull(key, "param name and url can not all empty");
            requestHandler = new LoadBalancerHandler();
        } else {
            requestHandler = new DirectHandler(hhhConfig.isEnableHttps());
        }
        Class clazz = hhhConfig.getClazz();
        hhhConfig.setRequestHandler(requestHandler);
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new ProxyInstance(hhhConfig));
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
