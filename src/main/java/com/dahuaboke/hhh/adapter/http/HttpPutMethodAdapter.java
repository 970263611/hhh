package com.dahuaboke.hhh.adapter.http;

import com.dahuaboke.hhh.enums.HttpMethod;
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
    HttpMethod matchHttpMethod() {
        return HttpMethod.PUT;
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
}
