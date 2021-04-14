package com.main.control.file.gson.serializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.reflect.TypeToken;
import com.main.control.signgetter.SignGetter;
import com.main.model.Sign;
import com.tool.gson.CustSerializer;

public class SignGetterSerializer implements CustSerializer<SignGetter<? extends Sign>> {

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return builder.registerTypeAdapter(Sign.class, new SignSerializer())
				.registerTypeAdapter(Enum.class, new EnumSerializer());
	}

	@Override
	public JsonElement serialize(SignGetter<? extends Sign> elem, Type typeOfOri, JsonSerializationContext context,
			Gson gson, JsonObject jsonObj) {
		jsonObj.addProperty("type", elem.getClass().getSimpleName());
		jsonObj.add("data", this.getGson().toJsonTree(elem, new TypeToken<SignGetter<? extends Sign>>() {
		}.getType()));

		return jsonObj;
	}

}
