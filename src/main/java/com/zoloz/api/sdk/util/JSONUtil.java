package com.zoloz.api.sdk.util;

import com.alibaba.fastjson.JSON;

/**
 * JSON serialization/deserialization utility
 *
 * @author Zhang Fang
 */
public class JSONUtil {
    /**
     * convert jave object to JSON string
     * @param object java object
     * @return JSON string
     */
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * parse java object from JSON string
     * @param str JSON string
     * @param clazz class of the target java object type
     * @param <T> target type
     * @return java object
     */
    public static <T> T parseObject(String str, Class<T> clazz) {
        return JSON.parseObject(str, clazz);
    }
}
