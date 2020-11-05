package com.racoon.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * 业务注入，注入接口
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPService {
    String value() default "";
}
