package com.dahuaboke.hhh.hook;

/**
 * author: dahua
 * date: 2023/11/27 17:41
 */
public abstract class AbstractHook implements Hook {

    public AbstractHook() {
        HookChain.setHook(this);
    }
}
