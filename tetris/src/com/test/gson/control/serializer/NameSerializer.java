package com.test.gson.control.serializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.test.gson.model.Birds.Name;

public class NameSerializer extends Serializer<Name> {

	@Override
	public JsonElement serialize(Name name, Type typeOfOri, JsonSerializationContext context, Gson gson,
			JsonObject nameJsonObj) {
		nameJsonObj.addProperty("type", name.getClass().getSimpleName());
		nameJsonObj.add("data", gson.toJsonTree(name));
		return nameJsonObj;
	}

	@Override
	public GsonBuilder registerSubClassTypeAdapter(GsonBuilder builder) {
		return null;
	}

}
