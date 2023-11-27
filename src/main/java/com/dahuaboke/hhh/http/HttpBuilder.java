package com.dahuaboke.hhh.http;

import com.dahuaboke.hhh.Request;
import com.dahuaboke.hhh.SocketContext;

/**
 * author: dahua
 * date: 2023/11/27 16:37
 */
public interface HttpBuilder {

    Request build(SocketContext socketContext);
}
