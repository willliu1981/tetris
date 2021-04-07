package com.test.gson.controller.serializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.test.gson.controller.Register;

public  interface CustSerializer<T> extends JsonSerializer<T> , Register {

	@Override
	public default JsonElement serialize(T elem, Type typeOfOri, JsonSerializationContext context) {
		return this.serialize(elem, typeOfOri, context,  getGson(), new JsonObject());
	}

	public abstract JsonElement serialize(T elem, Type typeOfOri, JsonSerializationContext context, Gson gson,
			JsonObject jsonObj);



}
