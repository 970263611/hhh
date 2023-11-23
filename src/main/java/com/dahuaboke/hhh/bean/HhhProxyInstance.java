package com.dahuaboke.hhh.bean;

import com.dahuaboke.hhh.model.HhhClientModel;
import com.dahuaboke.hhh.utils.JsonUtil;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import okhttp3.*;
import org.springframework.core.GenericTypeResolver;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * author: dahua
 * date: 2023/11/20 12:20
 */
public class HhhProxyInstance implements InvocationHandler {

    private HhhClientModel hhhClientModel;

    public HhhProxyInstance(HhhClientModel hhhClientModel) {
        this.hhhClientModel = hhhClientModel;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        AtomicReference<Object> objectAtomicReference = new AtomicReference<>();

        OkHttpClient httpClient = new OkHttpClient
                .Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JsonUtil.toString(args), JSON);
        Request.Builder builder = new Request.Builder();
        Request request = builder.post(requestBody).url("http://localhost:8081/hello").build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                Type genericReturnType = method.getGenericReturnType();
                ObjectMapper objectMapper = new ObjectMapper();
                JavaType javaType = getJavaType(objectMapper, genericReturnType, hhhClientModel.getClazz());
                Object o = null;
                try {
                    o = objectMapper.readValue(response.body().byteStream(), javaType);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                objectAtomicReference.set(o);
            }
        });
        return objectAtomicReference.get();
    }

    protected JavaType getJavaType(ObjectMapper objectMapper, Type type, @Nullable Class<?> contextClass) {
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        return typeFactory.constructType(GenericTypeResolver.resolveType(type, contextClass));
    }
}
