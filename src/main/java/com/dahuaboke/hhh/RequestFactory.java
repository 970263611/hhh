package com.dahuaboke.hhh;

/**
 * author: dahua
 * date: 2023/11/24 14:04
 */
public interface RequestFactory {

    Request createRequest(String url, String contentType, String param);
}
