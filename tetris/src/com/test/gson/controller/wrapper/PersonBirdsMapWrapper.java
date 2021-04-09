package com.test.gson.controller.wrapper;

import java.util.Map;

import com.google.gson.GsonBuilder;
import com.test.gson.controller.deserializer.BirdsDeserializer;
import com.test.gson.controller.deserializer.PersonDeserializer;
import com.test.gson.controller.serializer.BirdsSerializer;
import com.test.gson.model.Birds;
import com.test.gson.model.Person;

public class PersonBirdsMapWrapper extends GsonWrapper<Map<Person, Birds<?>>> {

	public PersonBirdsMapWrapper() {

	}

	public PersonBirdsMapWrapper(Map<Person, Birds<?>> mapBirds) {
		super(mapBirds);
	}

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {

		return builder.registerTypeAdapter(Birds.class, new BirdsSerializer())
				.registerTypeAdapter(Birds.class, new BirdsDeserializer())
				.registerTypeAdapter(Person.class, new PersonDeserializer());
	}

}
