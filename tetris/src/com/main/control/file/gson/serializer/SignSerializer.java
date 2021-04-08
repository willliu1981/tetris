package com.main.control.file.gson.serializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.main.model.Sign;
import com.tool.gson.CustSerializer;

public class SignSerializer implements  CustSerializer<Sign>{

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return null;
	}

	@Override
	public JsonElement serialize(Sign elem, Type typeOfOri, JsonSerializationContext context, Gson gson,
			JsonObject jsonObj) {
		jsonObj.addProperty("type", elem.getClass().getSimpleName());
		jsonObj.add("data", this.getGson().toJsonTree(elem,Sign.class));
		System.out.println("sign s "+jsonObj);
		return jsonObj;
	}

}
