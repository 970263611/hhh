package com.dahuaboke.hhh.loadbalance;

/**
 * author: dahua
 * date: 2023/11/29 9:42
 */
public interface LoadBalancer {

    String getInstanceAddress(String name);
}
