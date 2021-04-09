package com.test.gson.controller.deserializer;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.main.control.file.gson.deserializer.DirectionDeserializer;
import com.main.model.Sign.Cube;
import com.tool.Cycle;
import com.tool.Direction;

public class CycleDeserializer  implements CustDeserializer<Cycle<Map<Direction, Cube>> >{

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return builder.registerTypeAdapter(Direction.class, new DirectionDeserializer());
	}

	@Override
	public Cycle<Map<Direction, Cube>> deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context,
			Gson gson, JsonObject jo) {
		System.out.println("cycle ds ** cccccccc " + elem);
		return gson.fromJson(elem, new TypeToken<Cycle<Map<Direction, Cube>>>(){}.getType());
	}

}
