package org.leoly.apidoc.annotation;

import org.leoly.apidoc.IApiDescription;
import org.leoly.apidoc.utils.ApiConstants;
import sun.security.krb5.internal.APOptions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * API实例
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiInstance {

    /**
     * 接口名称
     */
    String name() default ApiConstants.EMPTY_STRING;

    /**
     * 接口描述
     */
    String desc() default ApiConstants.EMPTY_STRING;

    /**
     * 接口创建时间
     */
    String createDate() default ApiConstants.EMPTY_STRING;

    /**
     * 接口创建时间
     */
    String method() default ApiConstants.METHOD_GET;

    /**
     * 接口创建时间
     */
    String encrypt() default ApiConstants.EMPTY_STRING;

    /**
     * 接口地址
     */
    String url() default ApiConstants.EMPTY_STRING;

    /**
     * API信息描述类
     */
    Class<?> apiDescClass() default Object.class;

}
