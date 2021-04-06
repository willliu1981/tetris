package com.test.gson.control.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public abstract class Deserializer<T> implements JsonDeserializer<T> {

	@Override
	public T deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context)
			throws JsonParseException {
		return deserialize(elem, typeOfOri, context, this.getGson());
	}

	public abstract T deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context, Gson gson);

	protected Gson getGson() {
		GsonBuilder builder = this.registerSubClassTypeAdapter(new GsonBuilder());
		if (builder == null) {
			return new Gson();
		}
		return builder.create();
	}

	public abstract GsonBuilder registerSubClassTypeAdapter(GsonBuilder builder);

}
