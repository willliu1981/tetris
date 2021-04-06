package com.test.gson.control.serializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.test.gson.model.Birds;
import com.test.gson.model.Birds.FullName;
import com.test.gson.model.Birds.Name;

public class BirdsSerializer extends Serializer<Birds> {

	@Override
	public JsonElement serialize(Birds birds, Type typeOfOri, JsonSerializationContext context, Gson gson,
			JsonObject birdsJsonObj) {
		birdsJsonObj.addProperty("type", birds.getClass().getSimpleName());
		birdsJsonObj.add("data", gson.toJsonTree(birds));
		return birdsJsonObj;
	}

	@Override
	public GsonBuilder registerSubClassTypeAdapter(GsonBuilder builder) {
		return builder.registerTypeAdapter(Name.class, new NameSerializer());
	}

}
