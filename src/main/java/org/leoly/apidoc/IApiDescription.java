package org.leoly.apidoc;

import org.leoly.apidoc.analyzer.ApiDataEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * Api描述接口
 */
public interface IApiDescription {

    /**
     * 获取默认参数构建对象
     * @return
     */
    default Map<String, Object> getDefaultParamsMap(){
        Map<String, Object> defulatMap = new HashMap<>();
        Map<String, Object> headMap = new HashMap<>();
        headMap.put("timestamp", ApiDataEnum.LONG.desc("接口调用时的时间戳"));
        headMap.put("userId", ApiDataEnum.INTEGER.desc("用户ID"));
        headMap.put("token", ApiDataEnum.STRING.desc("用户登录凭证"));
        headMap.put("sessionId", ApiDataEnum.STRING.desc("用户登录的SESSION凭据"));
        defulatMap.put("head", headMap);
        return defulatMap;
    }

    /**
     * 获取默认响应构建对象
     * @return
     */
    default Map<String, Object> getDefaultBodyMap(){
        Map<String, Object> defulatMap = new HashMap<>();
        defulatMap.put("code", ApiDataEnum.INTEGER.desc("接口响应编码"));
        defulatMap.put("desc", ApiDataEnum.STRING.desc("接口响应描述"));
        defulatMap.put("rts", ApiDataEnum.STRING.desc("权限状态位"));
        defulatMap.put("sign", ApiDataEnum.STRING.desc("响应对象JSON字符串签名"));
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
