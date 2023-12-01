package com.dahuaboke.hhh.config;

import com.dahuaboke.hhh.consts.HhhConst;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;

/**
 * author: dahua
 * date: 2023/12/1 10:34
 */
public class HhhApplicationRunListener implements SpringApplicationRunListener {

    private SpringApplication application;
    private String[] args;

    public HhhApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {
        Class aClass = this.application.getMainApplicationClass();
        String mainBasePaceName = aClass.getPackage().getName();
        System.setProperty(HhhConst.MAIN_BASE_PACKAGE, mainBasePaceName);
    }
}
