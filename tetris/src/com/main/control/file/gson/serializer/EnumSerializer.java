package com.main.control.file.gson.serializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.reflect.TypeToken;
import com.tool.gson.CustSerializer;

public class EnumSerializer implements CustSerializer<Enum<?>> {

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return null;
	}

	@Override
	public JsonElement serialize(Enum<?> elem, Type typeOfOri, JsonSerializationContext context, Gson gson,
			JsonObject jsonObj) {
		System.out.println("xxx");
		jsonObj.addProperty("type", elem.getClass().getSimpleName());
		jsonObj.add("data", this.getGson().toJsonTree(elem));
		return jsonObj;
	}

}
