package com.test.gson.control.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.test.gson.model.Birds;
import com.test.gson.model.Eagle;
import com.test.gson.model.Penguin;
import com.test.gson.model.Birds.Name;

public class BirdsDeserializer extends Deserializer<Birds> {

	@Override
	public Birds deserialize(JsonElement birds, Type typeOfOri, JsonDeserializationContext context, Gson gson) {
		String bType = birds.getAsJsonObject().get("type").getAsString();
		JsonObject bData = birds.getAsJsonObject().get("data").getAsJsonObject();

		switch (bType) {
		case "Eagle":
			return gson.fromJson(bData, Eagle.class);
		case "Penguin":
			return gson.fromJson(bData, Penguin.class);
		default:
			throw new IllegalArgumentException("No match class");
		}
	}

	@Override
	public GsonBuilder registerSubClassTypeAdapter(GsonBuilder builder) {
		return builder.registerTypeAdapter(Name.class, new NameDeserializer()).registerTypeAdapter(Enum.class,
				new EnumDeserializer());
	}

}
