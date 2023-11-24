package com.dahuaboke.hhh.handler;

import com.dahuaboke.hhh.SocketContext;

/**
 * author: dahua
 * date: 2023/11/20 17:25
 */
public interface RequestHandler {

    void handler(SocketContext socketContext);

    String calculatingUrl(SocketContext socketContext);
}
