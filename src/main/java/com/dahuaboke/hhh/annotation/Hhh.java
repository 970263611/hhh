package com.dahuaboke.hhh.annotation;

import com.dahuaboke.hhh.consts.HhhConst;

import java.lang.annotation.*;

/**
 * author: dahua
 * date: 2023/11/20 11:20
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Hhh {

    String name() default "";

    String url() default "";

    String contentType() default HhhConst.DEFAULT_REQUEST_CONTENT_TYPE;
}
