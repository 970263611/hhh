package com.dahuaboke.hhh.codec;

import org.springframework.lang.Nullable;

import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * author: dahua
 * date: 2023/11/24 14:41
 */
public interface CodecConverter {

    String encode(Object obj);

    Object decode(InputStream inputStream, Type type, @Nullable Class<?> clazz);
}
