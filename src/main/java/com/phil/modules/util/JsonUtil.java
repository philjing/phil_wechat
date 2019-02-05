/**
 * FileName: nUtil
 * Author:   Phil
 * Date:     8/1/2018 10:32
 * Description: Json Simple Util
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.modules.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 〈Json Simple Util〉
 *
 * @author Phil
 * @create 8/1/2018
 * @since 1.0.0
 */
public class JsonUtil {

    private JsonUtil() {
    }

    private static final Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();

    /**
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return "{}";
        }
        if (obj instanceof String && StringUtils.isEmpty(obj.toString())) {
            return "{}";
        }

        String json;
        try {
            json = gson.toJson(obj);
        } catch (JsonIOException e) {
            json = "{}";
        }
        return json;
    }

    /**
     * 转成bean
     *
     * @param json
     * @param cls
     * @return
     */
    public static <T> T fromJson(String json, Class<T> cls) {
        if (json == null) {
            return null;
        }
        return gson.fromJson(json, cls);
    }

    /**
     * 转成list
     *
     * @param json
     * @param cls
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> cls) {
        if (json == null) {
            return null;
        }
        return gson.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }

    /**
     * 转成map
     *
     * @param json
     * @return
     */
    public static <T> Map<String, T> toMap(String json) {
        return gson.fromJson(json, new TypeToken<Map<String, T>>() {
        }.getType());
    }
}
