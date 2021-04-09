package com.main.control.file.gson.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.main.control.file.TClassTypeFactory;
import com.main.model.Sign;
import com.main.model.SignGetter;
import com.tool.gson.CustDeserializer;

public class SignGetterDeserializer implements CustDeserializer<SignGetter<? extends Sign>> {

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return builder.registerTypeAdapter(Sign.class, new SignDeserializer()).registerTypeAdapter(Enum.class,
				new EnumDeserializer());
	}

	@Override
	public SignGetter<? extends Sign> deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context,
			Gson gson, JsonObject jo) {
		jo = elem.getAsJsonObject();
		String type = jo.get("type").getAsString();

		return (SignGetter<? extends Sign>) gson.fromJson(jo.get("data"), TClassTypeFactory.getType(type));
	}

}
