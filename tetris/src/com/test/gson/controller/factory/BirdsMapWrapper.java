package com.test.gson.controller.factory;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.reflect.TypeToken;
import com.test.gson.controller.deserializer.BirdsDeserializer;
import com.test.gson.controller.serializer.BirdsSerializer;
import com.test.gson.model.Birds;

public class BirdsMapWrapper extends GsonWrapper<Map<String, Birds>> {
	
	public BirdsMapWrapper() {
		
	}

	public BirdsMapWrapper(Map<String, Birds> mapBirds) {
		super(mapBirds);
	}

	@Override
	public JsonElement serialize(Map<String, Birds> elem, Type typeOfOri, JsonSerializationContext context, Gson gson,
			JsonObject jsonObj) {
		return gson.toJsonTree(elem);
	}

	@Override
	public Map<String, Birds> deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context,
			Gson gson) {
		return gson.fromJson(elem, new TypeToken<Map<String, Birds>>() {
		}.getType());
	}

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {

		return builder.registerTypeAdapter(Birds.class, new BirdsSerializer()).registerTypeAdapter(Birds.class,
				new BirdsDeserializer());
	}

}
