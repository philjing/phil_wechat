package com.phil.modules.annotation;

import java.lang.annotation.*;

/**
 * @description: 该注解表示非必填项,仅仅作为标识
 * @author: Phil Jing
 * @date: 2018-07-31 20:47
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface NotRequire {

    boolean value() default true;
}
