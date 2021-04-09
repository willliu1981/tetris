package com.main.control.file.gson.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.main.control.file.TClassTypeFactory;
import com.tool.gson.CustDeserializer;

public class EnumDeserializer implements CustDeserializer<Enum<?>> {

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return null;
	}

	@Override
	public Enum<?> deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context, Gson gson,
			JsonObject jo) {
		/*
		 * JsonElement 有可能來自於 Map 的 key, 由於Map key 是原物件的 toString 字串, 如果toString 已格式化為"類似"
		 * jsonString,則再做一次分析以取得正確的 jsonString
		 */
		if (!elem.isJsonObject()) {
			elem = JsonParser.parseString(elem.getAsString());
		}
		jo = elem.getAsJsonObject();
		String type = jo.get("type").getAsString();
		return (Enum<?>) gson.fromJson(jo.get("data"), TClassTypeFactory.getType(type));
	}

}
