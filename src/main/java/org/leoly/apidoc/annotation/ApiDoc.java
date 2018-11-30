package org.leoly.apidoc.annotation;

import org.leoly.apidoc.utils.ApiConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * API描述
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiDoc {

    /**
     * 接口名称
     */
    String name() default ApiConstants.EMPTY_STRING;

    /**
     * 接口描述
     */
    String desc() default ApiConstants.EMPTY_STRING;

    /**
     * 接口所属组
     */
    String group() default ApiConstants.EMPTY_STRING;
}
