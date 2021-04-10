package com.main.control.file.gson.serializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.tool.direction.Direction;
import com.tool.gson.CustSerializer;

public class DirectionSerializer implements CustSerializer<Direction> {

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return null;
	}

	@Override
	public JsonElement serialize(Direction elem, Type typeOfOri, JsonSerializationContext context, Gson gson,
			JsonObject jsonObj) {
		jsonObj.addProperty("type", elem.getClass().getSimpleName());
		jsonObj.add("data", gson.toJsonTree(elem));
		return jsonObj;
	}

}
