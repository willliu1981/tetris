package com.test.gson.controller.wrapper;

import java.util.Map;

import com.google.gson.GsonBuilder;
import com.test.gson.controller.deserializer.BirdsDeserializer;
import com.test.gson.controller.serializer.BirdsSerializer;
import com.test.gson.model.Birds;

public class StringBirdsMapWrapper extends GsonWrapper<Map<String, Birds>> {

	public StringBirdsMapWrapper() {

	}

	public StringBirdsMapWrapper(Map<String, Birds> mapBirds) {
		super(mapBirds);
	}

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {

		return builder.registerTypeAdapter(Birds.class, new BirdsSerializer()).registerTypeAdapter(Birds.class,
				new BirdsDeserializer());
	}

}
