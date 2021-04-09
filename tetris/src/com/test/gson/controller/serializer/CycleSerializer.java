package com.test.gson.controller.serializer;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.reflect.TypeToken;
import com.main.control.file.gson.serializer.DirectionSerializer;
import com.main.model.Sign.Cube;
import com.tool.Cycle;
import com.tool.Direction;

public class CycleSerializer implements CustSerializer<Cycle<Map<Direction, Cube>> >{

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return builder.registerTypeAdapter(Direction.class, new DirectionSerializer());
	}

	@Override
	public JsonElement serialize(Cycle<Map<Direction, Cube>> elem, Type typeOfOri, JsonSerializationContext context,
			Gson gson, JsonObject jsonObj) {
		return gson.toJsonTree(elem);
	}

}
