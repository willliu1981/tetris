package com.test.gson.controller.deserializer;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.test.gson.model.Birds;


public class BirdsMapDeserializer extends Deserializer<Map<String, Birds>> {

	@Override
	public Map<String, Birds> deserialize(JsonElement map, Type typeOfOri, JsonDeserializationContext context,
			Gson gson) {
		return gson.fromJson(map, new TypeToken<Map<String, Birds>>() {
		}.getType());
	}

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return builder.registerTypeAdapter(Birds.class, new BirdsDeserializer());
	}

}
