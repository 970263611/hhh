package com.dahuaboke.hhh.hook;

import com.dahuaboke.hhh.SocketContext;

/**
 * author: dahua
 * date: 2023/11/27 17:40
 */
public interface Hook {

    void beforeSendRequest(SocketContext socketContext);
}
