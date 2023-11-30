package com.dahuaboke.hhh.hook;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * author: dahua
 * date: 2023/11/27 17:41
 */
public abstract class AbstractHook implements Hook {

    @Autowired
    private HookChain hookChain;

    @PostConstruct
    public void init() {
        hookChain.setHook(this);
    }
}
