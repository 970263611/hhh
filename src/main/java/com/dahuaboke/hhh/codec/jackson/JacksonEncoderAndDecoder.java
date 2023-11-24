package com.dahuaboke.hhh.codec.jackson;

import com.dahuaboke.hhh.codec.EncoderAndDecoder;
import com.dahuaboke.hhh.exception.EncodeOrDecodeException;
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
public class JacksonEncoderAndDecoder implements EncoderAndDecoder {

    private static class singleJson {
        private static final ObjectMapper INSTANCE = new ObjectMapper();
    }

    @Override
    public String encode(Object... objs) {
        try {
            return INSTANCE().writeValueAsString(objs);
        } catch (JsonProcessingException e) {
            throw new EncodeOrDecodeException();
        }
    }

    @Override
    public Object decode(InputStream inputStream, Type type, @Nullable Class<?> contextClass) {
        TypeFactory typeFactory = INSTANCE().getTypeFactory();
        JavaType javaType = typeFactory.constructType(GenericTypeResolver.resolveType(type, contextClass));
        try {
            return INSTANCE().readValue(inputStream, javaType);
        } catch (IOException e) {
            throw new EncodeOrDecodeException();
        }
    }

    public ObjectMapper INSTANCE() {
        return singleJson.INSTANCE;
    }
}
