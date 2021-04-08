package com.main.control.file.gson.wrapper;

import java.util.Map;

import com.google.gson.GsonBuilder;
import com.main.control.file.gson.deserializer.EnumDeserializer;
import com.main.control.file.gson.deserializer.SignGetterDeserializer;
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
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return builder.registerTypeAdapter(SignGetter.class, new SignGetterSerializer())
				.registerTypeAdapter(SignType.class, new EnumDeserializer())
				.registerTypeAdapter(SignGetter.class , new SignGetterDeserializer());
	}

}
