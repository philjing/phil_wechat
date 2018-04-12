package com.phil.modules.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * Json Util
 * @author phil
 *
 */
public class JsonUtil {

	public static String toJsonString(Object obj) {
		if (obj == null) {
			return "{}";
		}
		if (obj instanceof String) {
			if (StringUtils.isEmpty(obj.toString())) {
				return "{}";
			}
		}
		Gson gson = new Gson();
		String json;
		try {
			json = gson.toJson(obj);
		} catch (Exception e) {
			return "{}";
		}
		return json;
	}

	public static <T> T fromJsonString(String json, Class<T> classOfT) {
		Gson gson = new Gson();
		T t = gson.fromJson(json, classOfT);
		return t;
	}

	/**
	 * 待完善
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
