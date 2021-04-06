package com.test.gson.control.serializer;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.test.gson.model.Birds;

public class BirdsMapSerializer extends Serializer<Map<String, Birds>> {

	@Override
	public JsonElement serialize(Map<String, Birds> map, Type typeOfOri, JsonSerializationContext context, Gson gson,
			JsonObject jsonObj) {
		return gson.toJsonTree(map);
	}

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return builder.registerTypeAdapter(Birds.class, new BirdsSerializer());
	}

}
