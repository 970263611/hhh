package com.dahuaboke.hhh.config;

import com.dahuaboke.hhh.RequestFactory;
import com.dahuaboke.hhh.SocketClient;
import com.dahuaboke.hhh.bean.ClientBeanProcessor;
import com.dahuaboke.hhh.codec.EncoderAndDecoder;
import com.dahuaboke.hhh.codec.jackson.JacksonEncoderAndDecoder;
import com.dahuaboke.hhh.http.okhttp.OkHttpClient;
import com.dahuaboke.hhh.http.okhttp.OkHttpRequestFactory;
import com.dahuaboke.hhh.property.HhhProperties;
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
    @ConditionalOnMissingBean(EncoderAndDecoder.class)
    public EncoderAndDecoder encoderAndDecoder() {
        return new JacksonEncoderAndDecoder();
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
}