package com.main.control.file.gson.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tool.gson.CustDeserializer;

public class EnumDeserializer implements CustDeserializer<Enum<?>> {

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return null;
	}

	@Override
	public Enum<?> deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context, Gson gson,JsonObject jo) {
		System.out.println("(enum ds) "+elem.getAsString());
		
		return null;
	}

}
