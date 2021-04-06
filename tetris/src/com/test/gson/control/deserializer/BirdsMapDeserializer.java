package com.test.gson.control.deserializer;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.test.gson.model.Birds;

/*
 * 這個類,可能可以利用於 TestGson 的第46行, 取得Gson (尚未實測)
 */
public class BirdsMapDeserializer extends Deserializer<Map<String, Birds>> {

	@Override
	public Map<String, Birds> deserialize(JsonElement map, Type typeOfOri, JsonDeserializationContext context,
			Gson gson) {
		return gson.fromJson(map, new TypeToken<Map<String, Birds>>() {
		}.getType());
	}

	@Override
	public GsonBuilder registerSubClassTypeAdapter(GsonBuilder builder) {
		return builder.registerTypeAdapter(Birds.class, new BirdsDeserializer());
	}

}
