package com.test.gson.controller.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.test.gson.controller.factory.ClassTypeFactory;
import com.test.gson.model.Birds;
import com.test.gson.model.Birds.Name;

public class NameDeserializer implements CustDeserializer<Birds.Name> {

	@Override
	public Name deserialize(JsonElement name, Type typeOfOri, JsonDeserializationContext context, Gson gson,JsonObject jo) {
		System.out.println("name ds * nnnnnnnn");
		String nType = name.getAsJsonObject().get("type").getAsString();
		JsonObject nData = name.getAsJsonObject().get("data").getAsJsonObject();
		return (Name) gson.fromJson(nData, ClassTypeFactory.getClassType(nType));
	}

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return null;
	}

}
