package com.dahuaboke.hhh.http;

import com.dahuaboke.hhh.SocketClient;
import com.dahuaboke.hhh.property.HhhProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * author: dahua
 * date: 2023/11/24 14:19
 */
public abstract class HttpClient implements SocketClient {

    @Autowired
    private HhhProperties hhhProperties;

    protected int httpConnectTimeout;
    protected int httpReadTimeout;

    @PostConstruct
    public void init() {
        this.httpConnectTimeout = hhhProperties.getSocketConnectTimeout();
        this.httpReadTimeout = hhhProperties.getSocketReadTimeout();
    }
}
