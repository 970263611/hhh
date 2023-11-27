package com.dahuaboke.hhh.adapter.http;

import com.dahuaboke.hhh.http.HttpBuilder;

/**
 * author: dahua
 * date: 2023/11/24 16:38
 */
public interface HttpAdapter {

    Class matchSpringMvcClass();

    Class<? extends HttpBuilder> matchHttpBuilder();
}
