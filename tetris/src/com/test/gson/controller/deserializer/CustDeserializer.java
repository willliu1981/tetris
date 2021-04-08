package com.test.gson.controller.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.test.gson.controller.Register;

public interface CustDeserializer<T> extends JsonDeserializer<T>, Register {

	@Override
	public default T deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context)
			throws JsonParseException {
		return deserialize(elem, typeOfOri, context, this.getGson(),new JsonObject());
	}

	public abstract T deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context, Gson gson,JsonObject jo);

}
