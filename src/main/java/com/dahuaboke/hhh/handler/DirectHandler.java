package com.dahuaboke.hhh.handler;

import com.dahuaboke.hhh.SocketContext;
import com.dahuaboke.hhh.adapter.SocketAdapter;
import com.dahuaboke.hhh.consts.HhhConst;

import java.lang.reflect.Method;

/**
 * author: dahua
 * date: 2023/11/20 17:20
 */
public class DirectHandler extends AbstractHandler {

    @Override
    public String getUrl(SocketContext socketContext) {
        return socketContext.getUrl();
    }

}
