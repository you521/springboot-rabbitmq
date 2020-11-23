package com.you.wstro.util;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 关于java相关一些方法封装
 * @author Administrator
 *
 */

public class JavaUtil
{
    /**
     * 判断你一个类是否存在某个属性（字段）
     *
     * @param field 字段
     * @param obj   类对象
     * @return true:存在，false:不存在, null:参数不合法
     */
    public static Boolean isExistField(String field, Object obj) {
        if (obj == null || StringUtils.isEmpty(field)) {
            return null;
        }
        // 将实体类转换成json对象
        Object object = JSON.toJSON(obj);
        JSONObject jsonObj = new JSONObject();
        if (object instanceof JSONObject) {
            jsonObj = (JSONObject) object;
        }
        return jsonObj.containsKey(field);
    }
}
