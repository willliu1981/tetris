package com.main.control.file.gson.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.main.control.file.TClassTypeFactory;
import com.main.model.Sign;
import com.main.model.Sign.Cube;
import com.sun.javafx.collections.MappingChange.Map;
import com.tool.Direction;
import com.tool.gson.CustDeserializer;

public class SignDeserializer implements CustDeserializer<Sign> {

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return builder.registerTypeAdapter(new TypeToken<Map<Direction, Cube>>() {
		}.getType(), new JsonDeserializer<Map<Direction, Cube>>() {

			@Override
			public Map<Direction, Cube> deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
					throws JsonParseException {
				return new Gson().fromJson(arg0, new TypeToken<Map<Direction, Cube>>() {
				}.getType());
			}
		});
	}

	@Override
	public Sign deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context, Gson gson,
			JsonObject jo) {
		System.out.println("sign ds * xxxxxxxxxxxxxx");
		jo = JsonParser.parseString(elem.getAsString()).getAsJsonObject();
		String type = jo.get("type").getAsString();
		return (Sign) gson.fromJson(jo.get("data"), TClassTypeFactory.getType(type));
	}

}
