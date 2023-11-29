package com.dahuaboke.hhh.handler;

import com.dahuaboke.hhh.SocketContext;
import com.dahuaboke.hhh.loadbalance.LoadBalancer;
import com.dahuaboke.hhh.utils.SpringUtils;

/**
 * author: dahua
 * date: 2023/11/20 17:26
 */
public class LoadBalancerHandler extends AbstractHandler {

    @Override
    public String getUrl(SocketContext socketContext) {
        String name = socketContext.getName();
        LoadBalancer loadBalancer = SpringUtils.getBean(LoadBalancer.class);
        return loadBalancer.getInstanceAddress(name);
    }
}
