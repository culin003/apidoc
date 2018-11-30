/*
 * @Copyright: Copyright (c) 2015-2018 此代码属于智慧享联，在未经允许的情况下禁止复制传播
 * @Company:智慧享联
 * @filename ApiUtils
 * @author guguihe
 * @date 2018-11-26 15:45
 */

package org.leoly.apidoc.utils;

import org.leoly.apidoc.analyzer.ApiDataEnum;

import java.util.Set;

/**
 * TODO:
 *
 * @author guguihe
 * @Date 2018-11-26 15:45
 */
public class ApiUtils {

    /** 
    * @Description: 字符串是否为空
    * @Param:
    * @return:
    * @Author: guguihe
    * @Date: 2018/11/26 
    */ 
    public static boolean isEmpty(String source){
        return null == source || source.length() < 1;
    }

    public static boolean isEmpty(Set<?> source){
        return null == source || source.isEmpty();
    }

    public static String defaultIfEmpty(String source, String defaultValue){
        return isEmpty(source) ? defaultValue : source;
    }

    public static void main(String[] args) {
        System.out.println(ApiDataEnum.DATETIME.desc("创建日期"));
    }
}
