package com.test.gson.controller.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.test.gson.controller.factory.ClassTypeFactory;
import com.test.gson.model.Birds;
import com.test.gson.model.Eagle;
import com.test.gson.model.Penguin;
import com.test.gson.model.Birds.Name;

public class BirdsDeserializer implements CustDeserializer<Birds> {

	@Override
	public Birds deserialize(JsonElement birds, Type typeOfOri, JsonDeserializationContext context, Gson gson,JsonObject jo) {
		System.out.println("bird ds * bbbbbbbbb");
		String bType = birds.getAsJsonObject().get("type").getAsString();
		JsonObject bData = birds.getAsJsonObject().get("data").getAsJsonObject();

		Class<?> clazz= ClassTypeFactory.getClassType(bType);
		if(clazz==null) {
			new IllegalArgumentException("No match class");
		}
		return (Birds) gson.fromJson(bData, clazz);

	}

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return builder.registerTypeAdapter(Name.class, new NameDeserializer()).registerTypeAdapter(Enum.class,
				new EnumDeserializer());
	}

}
