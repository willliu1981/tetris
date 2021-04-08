package com.main.control.file.gson.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.main.model.Sign;
import com.main.model.SignGetter;
import com.tool.gson.CustDeserializer;

public class SignGetterDeserializer implements CustDeserializer<SignGetter<? extends Sign>> {

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
	
		return null;
	}

	@Override
	public SignGetter<? extends Sign> deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context,
			Gson gson,JsonObject jo) {
		
		System.out.println("(signgetter ds) " +elem.toString());
		String js=elem.toString();
		JsonObject je=JsonParser.parseString(js).getAsJsonObject();
		jo=elem.getAsJsonObject();
		String type=elem.getAsJsonObject().get("type").getAsString();
		System.out.println("(signgetter ds) " +type);
		
		return null;
	}

}
