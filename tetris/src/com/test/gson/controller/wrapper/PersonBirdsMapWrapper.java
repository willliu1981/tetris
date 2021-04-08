package com.test.gson.controller.wrapper;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.reflect.TypeToken;
import com.test.gson.controller.deserializer.BirdsDeserializer;
import com.test.gson.controller.deserializer.PersonDeserializer;
import com.test.gson.controller.factory.ClassTypeFactory;
import com.test.gson.controller.serializer.BirdsSerializer;
import com.test.gson.model.Birds;
import com.test.gson.model.Person;

public class PersonBirdsMapWrapper extends GsonWrapper<Map<Person, Birds>> {

	public PersonBirdsMapWrapper() {

	}

	public PersonBirdsMapWrapper(Map<Person, Birds> mapBirds) {
		super(mapBirds);
	}

	@Override
	public JsonElement serialize(Map<Person, Birds> elem, Type typeOfOri, JsonSerializationContext context, Gson gson,
			JsonObject jsonObj) {
		return gson.toJsonTree(elem);
	}

	@Override
	public Map<Person, Birds> deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context,
			Gson gson) {

		return gson.fromJson(elem, new TypeToken<Map<Person, Birds>>() {
		}.getType());
	}

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {

		return builder.registerTypeAdapter(Birds.class, new BirdsSerializer())
				.registerTypeAdapter(Birds.class, new BirdsDeserializer())
				.registerTypeAdapter(Person.class, new PersonDeserializer());
	}

}
