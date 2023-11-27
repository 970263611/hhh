package com.dahuaboke.hhh.hook;

import java.util.ArrayList;
import java.util.List;

/**
 * author: dahua
 * date: 2023/11/27 17:38
 */
public class HookChain {

    private static List<Hook> hooks = new ArrayList();

    public static void setHook(Hook hook) {
        hooks.add(hook);
    }

    public static List<Hook> getHookChain() {
        return hooks;
    }
}
