package com.dahuaboke.hhh.config;

import com.dahuaboke.hhh.RequestFactory;
import com.dahuaboke.hhh.SocketClient;
import com.dahuaboke.hhh.bean.ClientBeanProcessor;
import com.dahuaboke.hhh.codec.CodecConverter;
import com.dahuaboke.hhh.codec.DefaultConverter;
import com.dahuaboke.hhh.http.okhttp.OkHttpClient;
import com.dahuaboke.hhh.http.okhttp.OkHttpRequestFactory;
import com.dahuaboke.hhh.loadbalance.LoadBalancer;
import com.dahuaboke.hhh.loadbalance.nacos.NacosLoadBalancer;
import com.dahuaboke.hhh.property.HhhProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * author: dahua
 * date: 2023/11/21 11:11
 */
@Configuration
@ComponentScan("com.dahuaboke.hhh")
@Import({ClientBeanProcessor.class})
@EnableConfigurationProperties(HhhProperties.class)
public class HhhAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(CodecConverter.class)
    public CodecConverter encoderAndDecoder() {
        return new DefaultConverter();
    }

    @Bean
    @ConditionalOnMissingBean(SocketClient.class)
    public SocketClient httpClient() {
        return new OkHttpClient();
    }

    @Bean
    @ConditionalOnMissingBean(RequestFactory.class)
    public RequestFactory httpRequestFactory() {
        return new OkHttpRequestFactory();
    }

    @Bean
    @ConditionalOnMissingBean(LoadBalancer.class)
    @ConditionalOnClass(name = "com.alibaba.nacos.api.naming.NamingFactory")
    public LoadBalancer loadBalancer() {
        return new NacosLoadBalancer();
    }
}
