package com.main.control.file.gson.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.main.control.file.TClassTypeFactory;
import com.tool.Direction;
import com.tool.gson.CustDeserializer;

public class DirectionDeserializer implements CustDeserializer<Direction> {

	@Override
	public GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder) {
		return null;
	}

	@Override
	public Direction deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context, Gson gson,
			JsonObject jo) {
		System.out.println("direction ds ** dddddd " + elem);
		JsonElement je;
		if(!elem.isJsonObject()) {
			jo = JsonParser.parseString(elem.getAsString()).getAsJsonObject();
			je=jo.get("data");
			System.out.println("direction ds  ** not jsonObject");
			
		}else {
			jo =elem.getAsJsonObject();
			je=jo;
			System.out.println("direction ds  ** is jsonObject");
			
		}
		
		Direction d= (Direction) gson.fromJson(je, TClassTypeFactory.getType("Direction"));
		System.out.println("direction ds d ** "+d);
		//String type = jo.get("type").getAsString();
		
		//String type = jo.get("type").getAsString();
		//System.out.println("direction ds ** " + type + " , " + jo.get("data"));
		//return (Direction) gson.fromJson(jo.get("data"), TClassTypeFactory.getType(type));
		return d;
	}

}
