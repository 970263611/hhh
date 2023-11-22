package com.dahuaboke.hhh.annotation;

import java.lang.annotation.*;

/**
 * author: dahua
 * date: 2023/11/20 11:20
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HhhClient {

    String name() default "";

    String url() default "";
}
