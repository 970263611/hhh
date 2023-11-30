package com.dahuaboke.hhh.adapter.http;

import com.dahuaboke.hhh.enums.HttpSocketMethod;
import com.dahuaboke.hhh.enums.SocketMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * author: dahua
 * date: 2023/11/24 16:45
 */
public abstract class AbstractHttpMethodAdapter implements HttpMethodAdapter {

    @Autowired
    private HttpAdapterChain httpAdapterChain;

    @PostConstruct
    public void init() {
        register();
    }

    public void register() {
        httpAdapterChain.addMethodConvert(matchSpringMvcClass(), matchHttpMethod());
        httpAdapterChain.addAdapter(this);
    }

    @Override
    public boolean match(Method method) {
        HttpSocketMethod httpMethod = findHttpMethod(method);
        return matchHttpMethod().equals(httpMethod);
    }

    public HttpSocketMethod findHttpMethod(Method method) {
        for (Map.Entry<Class, SocketMethod> entry : httpAdapterChain.getMethodConvert().entrySet()) {
            Annotation annotation = AnnotationUtils.findAnnotation(method, entry.getKey());
            if (annotation != null) {
                return (HttpSocketMethod) entry.getValue();
            }
        }
        return HttpSocketMethod.GET;
    }

    abstract HttpSocketMethod matchHttpMethod();
}
