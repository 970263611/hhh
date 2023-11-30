package com.dahuaboke.hhh.adapter.http;

import com.dahuaboke.hhh.enums.HttpSocketMethod;
import com.dahuaboke.hhh.http.HttpBuilder;
import com.dahuaboke.hhh.http.HttpPatchBuilder;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PatchMapping;

import java.lang.reflect.Method;

/**
 * author: dahua
 * date: 2023/11/24 16:40
 */
@Component
public class HttpPatchMethodAdapter extends AbstractHttpMethodAdapter {

    @Override
    HttpSocketMethod matchHttpMethod() {
        return HttpSocketMethod.PATCH;
    }

    @Override
    public String getUri(Method method) {
        PatchMapping annotation = AnnotationUtils.findAnnotation(method, PatchMapping.class);
        return annotation.value()[0];
    }

    @Override
    public Class matchSpringMvcClass() {
        return PatchMapping.class;
    }

    @Override
    public Class<? extends HttpBuilder> matchHttpBuilder() {
        return HttpPatchBuilder.class;
    }
}
