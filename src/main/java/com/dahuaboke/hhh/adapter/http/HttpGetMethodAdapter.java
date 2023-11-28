package com.dahuaboke.hhh.adapter.http;

import com.dahuaboke.hhh.enums.HttpMethod;
import com.dahuaboke.hhh.http.HttpBuilder;
import com.dahuaboke.hhh.http.HttpGetBuilder;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * author: dahua
 * date: 2023/11/24 16:40
 */
@Component
public class HttpGetMethodAdapter extends AbstractHttpMethodAdapter {

    @Override
    HttpMethod matchHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    public String getUri(Method method) {
        GetMapping getMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);
        if (getMapping == null) {
            RequestMapping requestMapping = AnnotationUtils.getAnnotation(method, RequestMapping.class);
            return requestMapping.value()[0];
        }
        return getMapping.value()[0];
    }

    @Override
    public Class matchSpringMvcClass() {
        return GetMapping.class;
    }

    @Override
    public Class<? extends HttpBuilder> matchHttpBuilder() {
        return HttpGetBuilder.class;
    }
}
