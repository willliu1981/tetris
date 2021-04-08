package com.test.gson.controller.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.test.gson.controller.factory.ClassTypeFactory;
import com.test.gson.model.Person;

public class PersonDeserializer implements CustDeserializer<Person> {

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return null;
	}

	@Override
	public Person deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context, Gson gson) {
		String js = elem.getAsString();
		JsonObject jo = JsonParser.parseString(js).getAsJsonObject();
		String type = jo.get("type").getAsString();
		Person p = (Person) new Gson().fromJson(jo.get("data"), ClassTypeFactory.getClassType(type));
		return p;
	}

}
