package com.test.gson.controller.factory;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.reflect.TypeToken;
import com.test.gson.model.Birds;

public class BirdsMapWrapper extends GsonWrapper<Map<String, Birds>> {

	public BirdsMapWrapper(Map<String, Birds> mapBirds) {
		super(mapBirds);
	}

	@Override
	public JsonElement serialize(Map<String, Birds> arg0, Type arg1, JsonSerializationContext arg2) {

		return new Gson().toJsonTree(arg0);
	}

	@Override
	public Map<String, Birds> deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		
		return new Gson().fromJson(arg0, new TypeToken<Map<String, Birds>>() {
		}.getType());
	}

}
