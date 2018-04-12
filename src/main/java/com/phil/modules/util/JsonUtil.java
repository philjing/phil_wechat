package com.phil.modules.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Json Util
 * 
 * @author phil
 *
 */
public class JsonUtil {

	private static Gson gson = null;
	static {
		if (gson == null) {
			gson = new GsonBuilder().serializeNulls().create();
		}
	}
	
	public static String toJsonString(Object obj) {
		if (obj == null) {
			return "{}";
		}
		if (obj instanceof String) {
			if (StringUtils.isEmpty(obj.toString())) {
				return "{}";
			}
		}
		String json = "";
		try {
			if (gson != null) {
				json = gson.toJson(obj);
			}
		} catch (Exception e) {
			return "{}";
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
	public static <T> T fromJsonString(String json, Class<T> classOfT) {
		T t = null;
		if (gson != null) {
			t = gson.fromJson(json, classOfT);
		}
		return t;
	}

	/**
	 * 转成list
	 * 
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T> List<T> toList(String json, Class<T> cls) {
		List<T> list = null;
		if (gson != null) {
			list = gson.fromJson(json, new TypeToken<List<T>>() {
			}.getType());
		}
		return list;
	}

	/**
	 * 转成list中有map的
	 * 
	 * @param json
	 * @return
	 */
	public static <T> List<Map<String, T>> toListMaps(String json) {
		List<Map<String, T>> list = null;
		if (gson != null) {
			list = gson.fromJson(json, new TypeToken<List<Map<String, T>>>() {
			}.getType());
		}
		return list;
	}

	/**
	 * 转成map的
	 * 
	 * @param json
	 * @return
	 */
	public static <T> Map<String, T> toMap(String json) {
		Map<String, T> map = null;
		if (gson != null) {
			map = gson.fromJson(json, new TypeToken<Map<String, T>>() {
			}.getType());
		}
		return map;
	}

	/**
	 * 待完善
	 * 
	 * @param json
	 * @param key
	 * @return
	 */
	public static String fromJsonString(String json, String key) {
		ObjectMapper mapper = new ObjectMapper();
		String value;
		JsonNode root;
		try {
			root = mapper.readTree(json);
			JsonNode data = root.path(key);
			value = data.asText();
		} catch (IOException e) {
			value = null;
		}
		return value;
	}
}
