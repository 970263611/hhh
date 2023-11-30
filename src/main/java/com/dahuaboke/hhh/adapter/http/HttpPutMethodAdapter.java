package com.dahuaboke.hhh.adapter.http;

import com.dahuaboke.hhh.enums.HttpSocketMethod;
import com.dahuaboke.hhh.http.HttpBuilder;
import com.dahuaboke.hhh.http.HttpPutBuilder;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;

import java.lang.reflect.Method;

/**
 * author: dahua
 * date: 2023/11/24 16:40
 */
@Component
public class HttpPutMethodAdapter extends AbstractHttpMethodAdapter {

    @Override
    HttpSocketMethod matchHttpMethod() {
        return HttpSocketMethod.PUT;
    }

    @Override
    public String getUri(Method method) {
        PutMapping annotation = AnnotationUtils.findAnnotation(method, PutMapping.class);
        return annotation.value()[0];
    }

    @Override
    public Class matchSpringMvcClass() {
        return PutMapping.class;
    }

    @Override
    public Class<? extends HttpBuilder> matchHttpBuilder() {
        return HttpPutBuilder.class;
    }
}
