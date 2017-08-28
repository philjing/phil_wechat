/**
 * 
 */
package com.phil.common.util;

import com.google.gson.Gson;

/**
 * 
 * @author phil
 * @date 2017年7月31日
 *
 */
public class JsonUtil {

	public static String toJson(Object object) {
		Gson gson = new Gson();
		String result = gson.toJson(object);
		gson = null;
		return result;
	}
	
	
	public static <T> T fromJson(String json, Class<T> classOfT){
		Gson gson = new Gson();
		T t = gson.fromJson(json, classOfT);
		gson = null;//
		return t;
	}
}
