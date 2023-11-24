package com.dahuaboke.hhh.adapter.http;

import com.dahuaboke.hhh.enums.HttpMethod;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.lang.reflect.Method;

/**
 * author: dahua
 * date: 2023/11/24 16:40
 */
@Component
public class HttpDeleteMethodAdapter extends AbstractHttpMethodAdapter {

    @Override
    HttpMethod matchHttpMethod() {
        return HttpMethod.DELETE;
    }

    @Override
    public String getUri(Method method) {
        DeleteMapping annotation = AnnotationUtils.findAnnotation(method, DeleteMapping.class);
        return annotation.value()[0];
    }

    @Override
    public Class matchSpringMvcClass() {
        return DeleteMapping.class;
    }
}
