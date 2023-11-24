package com.dahuaboke.hhh.codec;

import org.springframework.lang.Nullable;

import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * author: dahua
 * date: 2023/11/24 14:41
 */
public interface EncoderAndDecoder {

    String encode(Object... objs);

    Object decode(InputStream inputStream, Type type, @Nullable Class<?> contextClass);
}
