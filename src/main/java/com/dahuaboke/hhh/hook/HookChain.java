package com.dahuaboke.hhh.hook;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * author: dahua
 * date: 2023/11/27 17:38
 */
@Component
public class HookChain {

    private List<Hook> hooks = new ArrayList();

    public void setHook(Hook hook) {
        hooks.add(hook);
    }

    public List<Hook> getHookChain() {
        return hooks;
    }
}
