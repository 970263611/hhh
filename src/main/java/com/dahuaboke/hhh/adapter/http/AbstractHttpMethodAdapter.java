package com.dahuaboke.hhh.adapter.http;

import com.dahuaboke.hhh.HhhConfig;
import com.dahuaboke.hhh.SocketContext;
import com.dahuaboke.hhh.enums.HttpMethod;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * author: dahua
 * date: 2023/11/24 16:45
 */
public abstract class AbstractHttpMethodAdapter implements HttpMethodAdapter {

    private static Map<Class, HttpMethod> httpMethodAnnotationClasses = new HashMap();

    @PostConstruct
    public void init() {
        register();
    }

    public void register() {
        HhhConfig.setSocketAdapter(this);
        httpMethodAnnotationClasses.put(matchSpringMvcClass(), matchHttpMethod());
    }

    @Override
    public boolean match(Method method) {
        HttpMethod httpMethod = findHttpMethod(method);
        return matchHttpMethod().equals(httpMethod);
    }

    public HttpMethod findHttpMethod(Method method) {
        for (Map.Entry<Class, HttpMethod> entry : httpMethodAnnotationClasses.entrySet()) {
            Annotation annotation = AnnotationUtils.findAnnotation(method, entry.getKey());
            if (annotation != null) {
                return entry.getValue();
            }
        }
        return HttpMethod.GET;
    }

    abstract HttpMethod matchHttpMethod();
}
