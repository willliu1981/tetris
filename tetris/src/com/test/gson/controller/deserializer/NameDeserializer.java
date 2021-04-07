package com.test.gson.controller.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.test.gson.model.Birds;
import com.test.gson.model.Birds.Name;

public class NameDeserializer extends Deserializer<Birds.Name> {

	@Override
	public Name deserialize(JsonElement name, Type typeOfOri, JsonDeserializationContext context, Gson gson) {
		String nType = name.getAsJsonObject().get("type").getAsString();
		JsonObject nData = name.getAsJsonObject().get("data").getAsJsonObject();
		switch (nType) {
		case "Name":
			return gson.fromJson(nData, Birds.Name.class);
		case "FullName":
			return gson.fromJson(nData, Birds.FullName.class);
		default:
			throw new IllegalArgumentException("No match class");
		}
	}

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return null;
	}

}
