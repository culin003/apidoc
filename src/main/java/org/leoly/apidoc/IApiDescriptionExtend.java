package org.leoly.apidoc;

import org.leoly.apidoc.analyzer.ApiDataEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * Api描述接口
 */
public interface IApiDescriptionExtend extends IApiDescription{

    /**
     * 获取默认参数构建对象
     * @return
     */
    default Map<String, Object> getDefaultParamsMap(){
        Map<String, Object> defulatMap = new HashMap<>();
        return defulatMap;
    }

    /**
     * 获取默认响应构建对象
     * @return
     */
    default Map<String, Object> getDefaultBodyMap(){
        Map<String, Object> defulatMap = new HashMap<>();
        return defulatMap;
    }

    /**
     * 获取参数信息
     */
    Map<String,Object> getParams();

    /**
     * 获取响应信息
     */
    Map<String,Object> getBody();
}
