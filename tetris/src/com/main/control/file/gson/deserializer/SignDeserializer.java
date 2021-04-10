package com.main.control.file.gson.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.main.control.file.gson.TClassTypeFactory;
import com.main.model.Sign;
import com.tool.Direction;
import com.tool.gson.CustDeserializer;

public class SignDeserializer implements CustDeserializer<Sign> {

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return builder.registerTypeAdapter(Direction.class, new  DirectionDeserializer());
	}

	@Override
	public Sign deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context, Gson gson,
			JsonObject jo) {
		jo =elem.getAsJsonObject();
		String type = jo.get("type").getAsString();
		return (Sign) gson.fromJson(jo.get("data") , TClassTypeFactory.getType(type));
	}

}
