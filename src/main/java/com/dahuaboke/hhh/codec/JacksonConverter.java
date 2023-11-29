package com.dahuaboke.hhh.codec;

import com.dahuaboke.hhh.exception.CodecException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.core.GenericTypeResolver;
import org.springframework.lang.Nullable;

import java.io.*;
import java.lang.reflect.Type;
import java.util.stream.Collectors;

/**
 * author: dahua
 * date: 2023/11/24 14:46
 */
public class JacksonConverter implements CodecConverter {

    private static class singleJson {
        private static final ObjectMapper INSTANCE = new ObjectMapper();
    }

    @Override
    public String encode(Object obj) {
        try {
            return INSTANCE().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new CodecException();
        }
    }

    @Override
    public Object decode(InputStream inputStream, Type type, @Nullable Class<?> contextClass) {
        if (type.getTypeName().equals(String.class.getTypeName())) {
            try {
                return new BufferedReader(new InputStreamReader(inputStream, "utf-8"))
                        .lines().collect(Collectors.joining(System.lineSeparator()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }
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
