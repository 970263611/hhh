package com.dahuaboke.hhh.bean;

import com.dahuaboke.hhh.handler.DirectHandler;
import com.dahuaboke.hhh.handler.LoadBalancerHandler;
import com.dahuaboke.hhh.handler.RequestHandler;
import com.dahuaboke.hhh.model.HhhClientModel;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Proxy;

/**
 * author: dahua
 * date: 2023/11/20 13:20
 */
public class HhhFactoryBean implements FactoryBean {

    private HhhClientModel hhhClientModel;

    public HhhFactoryBean(HhhClientModel hhhClientModel) {
        this.hhhClientModel = hhhClientModel;
    }

    @Override
    public Object getObject() {
        RequestHandler requestHandler;
        String key = hhhClientModel.getUrl();
        if (StringUtils.isEmpty(key)) {
            key = hhhClientModel.getName();
            Assert.isNull(key, "param name or url can not all empty");
            requestHandler = new LoadBalancerHandler();
        } else {
            requestHandler = new DirectHandler();
        }
        Class clazz = hhhClientModel.getClazz();
        hhhClientModel.setRequestHandler(requestHandler);
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new HhhProxyInstance(hhhClientModel));
    }

    @Override
    public Class<?> getObjectType() {
        return hhhClientModel.getClazz();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
