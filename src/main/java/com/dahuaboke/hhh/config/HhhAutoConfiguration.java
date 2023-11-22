package com.dahuaboke.hhh.config;

import com.dahuaboke.hhh.bean.HhhClientBeanProcessor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * author: dahua
 * date: 2023/11/21 11:11
 */
@Configuration
@ComponentScan("com.dahuaboke.hhh")
@Import({HhhClientBeanProcessor.class})
public class HhhAutoConfiguration {

}
