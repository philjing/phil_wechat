package com.phil.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解表示非必填项,仅仅作为标识
 * @author phil
 */
@Documented
@Target(ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface NotRequire {

	boolean value() default true;
	
}
