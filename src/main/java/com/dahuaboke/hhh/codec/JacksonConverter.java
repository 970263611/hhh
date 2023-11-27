package com.dahuaboke.hhh.codec;

import com.dahuaboke.hhh.exception.CodecException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.core.GenericTypeResolver;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * author: dahua
 * date: 2023/11/24 14:46
 */
public class JacksonConverter implements CodecConverter {

    private static class singleJson {
        private static final ObjectMapper INSTANCE = new ObjectMapper();
    }

    @Override
    public String encode(Object... objs) {
        try {
            return INSTANCE().writeValueAsString(objs);
        } catch (JsonProcessingException e) {
            throw new CodecException();
        }
    }

    @Override
    public Object decode(InputStream inputStream, Type type, @Nullable Class<?> contextClass) {
        TypeFactory typeFactory = INSTANCE().getTypeFactory();
        JavaType javaType = typeFactory.constructType(GenericTypeResolver.resolveType(type, contextClass));
        try {
            return INSTANCE().readValue(inputStream, javaType);
        } catch (IOException e) {
            throw new CodecException(e);
        }
    }

    @Override
    public boolean canEncode() {
        return true;
    }

    @Override
    public boolean canDecode() {
        return false;
    }

    public ObjectMapper INSTANCE() {
        return singleJson.INSTANCE;
    }
}
