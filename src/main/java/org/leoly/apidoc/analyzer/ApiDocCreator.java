/*
 * @Copyright: Copyright (c) 2015-2018 此代码属于智慧享联，在未经允许的情况下禁止复制传播
 * @Company:智慧享联
 * @filename ApiDocCreator
 * @author guguihe
 * @date 2018-11-26 14:09
 */

package org.leoly.apidoc.analyzer;

import com.google.gson.JsonArray;
import org.leoly.apidoc.IApiDescription;
import org.leoly.apidoc.annotation.ApiDoc;
import org.leoly.apidoc.annotation.ApiInstance;
import org.leoly.apidoc.utils.ApiUtils;
import org.leoly.apidoc.utils.GsonUtils;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.*;

/**
 * TODO:
 *
 * @author guguihe
 * @Date 2018-11-26 14:09
 */
public class ApiDocCreator {

    /**
     * @Description: 通过reflections工具扫描含有ApidDoc注解的类，并进行解析和生成API数据文档
     * @Author: Leoly
     * @Date: 2018/11/29
     */
    public static String createApiDatas() {
        Reflections reflections = new Reflections();
        Set<Class<?>> apiDocSet = reflections.getTypesAnnotatedWith(ApiDoc.class);
        List<Map<String, Object>> datas = new ArrayList<>();
        if (!ApiUtils.isEmpty(apiDocSet)) {
            apiDocSet.forEach(cls -> {
                collactDatas(cls, datas);
            });
        }

        return GsonUtils.toString(datas);
    }

    /**
     * @Description: 创建属性集合
     * @Param:
     * @return:
     * @Author: guguihe
     * @Date: 2018/11/29
     */
    private static Map<String, Object> createAttr(String id, String pId, String name, String desc, String method, String callUrl,
                                                  String createDate, String encrypt, Map<String, Object> params, Map<String, Object> body) {
        Map<String, Object> attrMap = new HashMap<>();
        attrMap.put("id", id);
        attrMap.put("pId", pId);
        attrMap.put("name", name);
        attrMap.put("desc", desc);
        attrMap.put("method", method);
        attrMap.put("callUrl", callUrl);
        attrMap.put("createDate", createDate);
        attrMap.put("encrypt", encrypt);
        attrMap.put("params", params);
        attrMap.put("body", body);
        return attrMap;
    }

    /**
     * @Description: 收集数据
     * @Param:
     * @return:
     * @Author: guguihe
     * @Date: 2018/11/29
     */
    private static void collactDatas(Class cls, List<Map<String, Object>> datas) {
        ApiDoc apiDoc = (ApiDoc) cls.getAnnotation(ApiDoc.class);
        Map<String, String> root = new HashMap<>();
        String rootId = UUID.randomUUID().toString();
        String subRootId = UUID.randomUUID().toString();
        // 所属组为空，则自己为root节点
        if (!ApiUtils.isEmpty(apiDoc.group())) {
            if (!root.containsKey(apiDoc.group())) {
                datas.add(createAttr(rootId, "root", apiDoc.group(), null, null, null, null, null, null, null));
                root.put(apiDoc.group(), rootId);
            } else {
                rootId = root.get(apiDoc.group());
            }
        }

        datas.add(createAttr(subRootId, rootId, apiDoc.name(), apiDoc.desc(), null, null, null, null, null, null));
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            ApiInstance apiInstance = method.getAnnotation(ApiInstance.class);
            if (null != apiInstance) {
                Class<?> apiDescriptionClass = apiInstance.apiDescClass();
                Map<String, Object> params = null;
                Map<String, Object> body = null;
                if (null != apiDescriptionClass) {
                    try {
                        IApiDescription description = (IApiDescription) apiDescriptionClass.newInstance();
                        params = description.getParams();
                        body = description.getBody();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                datas.add(createAttr(UUID.randomUUID().toString(), subRootId, apiInstance.name(), apiInstance.desc(), apiInstance.method(), apiInstance.url(), apiInstance.createDate(), apiInstance.encrypt(), params, body));
            }
        }
    }

    /**
     * @Description: 通过传入ApidDoc注解的类，并进行解析和生成API数据文档。
     * <pre>
     *     此方法可以通过Spring来获取项目中所有包含ApiDoc注解的类。
     *     如：Map<String, Object> apidocMap applicationContext.getBeansWithAnnotation(ApiDoc.class)
     * </pre>
     * @Author: Leoly
     * @Date: 2018/11/29
     */
    public static String createApiDatas(Map<String, Object> apidocMap) {
        List<Map<String, Object>> datas = new ArrayList<>();
        apidocMap.forEach((key, value) -> {
            Class<?> clazz = value.getClass();
            collactDatas(clazz, datas);
        });

        return GsonUtils.toString(datas);
    }
}
