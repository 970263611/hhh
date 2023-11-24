package com.dahuaboke.hhh.property;

import com.dahuaboke.hhh.consts.HhhConst;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * author: dahua
 * date: 2023/11/24 14:11
 */
@ConfigurationProperties(prefix = "hhh")
public class HhhProperties {

    private int requestTimeout;
    private int socketConnectTimeout;
    private int socketReadTimeout;

    public int getRequestTimeout() {
        return requestTimeout == 0 ? HhhConst.DEFAULT_REQUEST_TIMEOUT : requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public int getSocketConnectTimeout() {
        return socketConnectTimeout == 0 ? HhhConst.DEFAULT_SOCKET_CONNECT_TIMEOUT : socketConnectTimeout;
    }

    public void setSocketConnectTimeout(int socketConnectTimeout) {
        this.socketConnectTimeout = socketConnectTimeout;
    }

    public int getSocketReadTimeout() {
        return socketReadTimeout == 0 ? HhhConst.DEFAULT_SOCKET_READ_TIMEOUT : socketReadTimeout;
    }

    public void setSocketReadTimeout(int socketReadTimeout) {
        this.socketReadTimeout = socketReadTimeout;
    }
}
