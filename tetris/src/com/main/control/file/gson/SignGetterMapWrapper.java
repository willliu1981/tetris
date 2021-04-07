package com.main.control.file.gson;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.reflect.TypeToken;
import com.main.control.file.gson.serializer.SignGetterSerializer;
import com.main.control.manager.SignManager.SignType;
import com.main.model.Sign;
import com.main.model.SignGetter;
import com.tool.gson.GsonWrapper;

public class SignGetterMapWrapper extends GsonWrapper<Map<SignType, SignGetter<? extends Sign>>> {

	public SignGetterMapWrapper() {
	}

	public SignGetterMapWrapper(Map<SignType, SignGetter<? extends Sign>> map) {
		super(map);
	}

	@Override
	public JsonElement serialize(Map<SignType, SignGetter<? extends Sign>> elem, Type typeOfOri,
			JsonSerializationContext context, Gson gson, JsonObject jsonObj) {
		return gson.toJsonTree(elem, new TypeToken<Map<SignType, SignGetter<? extends Sign>>>(){}.getType());
	}

	@Override
	public Map<SignType, SignGetter<? extends Sign>> deserialize(JsonElement elem, Type typeOfOri,
			JsonDeserializationContext context, Gson gson) {
		return null;
	}

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return builder.registerTypeAdapter(SignGetter.class, new SignGetterSerializer());
	}

}
