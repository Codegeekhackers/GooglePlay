package com.lan.googleplay.uitls;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.text.TextUtils;

public class JsonUtil {
	public static String parseMapToJson(Map<?, ?> map) {
		Gson gson = new Gson();
		try {
			return gson.toJson(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T parseJsonToBean(String json, Class<T> cls) {
		Gson gson = new Gson();
		T t = null;
		try {
			t = gson.fromJson(json, cls);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return t;
	}

	public static Map<String, Object> parseJsonToMap(String json) {
		Gson gson = new Gson();
		//Type interface
		Type type = new TypeToken<Map<String, Object>>() {
		}.getType();
		Map<String, Object> map = null;
		try {
			map = gson.fromJson(json, type);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static List<?> parseJsonToList(String json, Type type) {
		Gson gson = new Gson();
		List<?> list = null;
		try {
			list = gson.fromJson(json, type);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static String getFieldValue(String json, String key) {
		if (TextUtils.isEmpty(json)) {
			return null;
		}
		String value =null;
		try {
			JSONObject jsonObject = new JSONObject(json);
			value = jsonObject.getString(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}
}
