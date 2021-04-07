package com.test.gson.controller.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.test.gson.model.Birds;


public class EnumDeserializer extends Deserializer<Enum<?>> {

	@Override
	public Enum<?> deserialize(JsonElement inum, Type typeOfOri, JsonDeserializationContext context, Gson gson) {

		return gson.fromJson(inum, Birds.NameInfoType.class);
	}

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return null;
	}

}

