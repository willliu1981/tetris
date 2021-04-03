package com.main.control.file;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.main.control.exception.FileErrorException;
import com.main.control.manager.SignManager;
import com.main.control.manager.SignManager.SignType;
import com.main.control.manager.getter.MainSignGetter;
import com.main.control.manager.getter.SignGetter;
import com.model.Sign;

public class FileManager {
	private static final String Basepath = "data";
	private static final String MainSignFileName = "mainsign.txt";
	private static String fname = Basepath + File.separator + MainSignFileName;

	public static void loadSignDate() {
		File f = new File(fname);
		if (!f.exists()) {
			throw new FileErrorException("can't find file " + fname);
		}
		Map<SignType, SignGetter<? extends Sign>> map = new HashMap<>();
		try (FileReader reader = new FileReader(f); BufferedReader br = new BufferedReader(reader)) {
			StringBuilder sb = new StringBuilder();
			while (br.ready()) {
				sb.append(br.readLine());
			}

			Gson g = new GsonBuilder()
					.registerTypeAdapter(SignGetter.class, new JsonDeserializer<SignGetter<? extends Sign>>() {
						@Override
						public SignGetter<? extends Sign> deserialize(JsonElement json, Type type,
								JsonDeserializationContext context) throws JsonParseException {
							String myType = json.getAsJsonObject().get("type").getAsString();
							JsonObject data = json.getAsJsonObject().get("data").getAsJsonObject();
							switch (myType) {
							case "MainSignGetter":
								return context.deserialize(data, MainSignGetter.class);
							default:
								throw new IllegalArgumentException("Gson:class no match");
							}
						}
					}).create();
			Type type = new TypeToken<Map<SignType, SignGetter<? extends Sign>>>() {
			}.getType();

			map = g.fromJson(sb.toString(), type);

		} catch (JsonSyntaxException e) {
			System.out.println(e.getMessage());
		} catch (EOFException e) {
			System.out.println(e.getMessage());
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		SignManager.setSignGetterMap(map);
	}

	public static void writeSignDate() {
		Map<SignType, SignGetter<? extends Sign>> map = SignManager.getSignGetterMap();
		try (FileWriter writer = new FileWriter(fname);) {
			JsonSerializer<SignGetter<? extends Sign>> jsonSerializer = new JsonSerializer<SignGetter<? extends Sign>>() {
				@Override
				public JsonElement serialize(SignGetter<? extends Sign> signGetter, Type type,
						JsonSerializationContext context) {
					final JsonObject wrapper = new JsonObject();
					wrapper.addProperty("type", signGetter.getClass().getSimpleName());
					wrapper.add("data", new Gson().toJsonTree(signGetter));
					return wrapper;
				}
			};

			Gson g = new GsonBuilder().registerTypeAdapter(MainSignGetter.class, jsonSerializer).create();

			writer.write(g.toJson(map));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
	
	class JsonAdapter implements JsonSerializer<Object>,JsonDeserializer<Object>{
		private static final String Classname="classname";
		private static final String Data="data";
		
		
		@Override
		public Object deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
				throws JsonParseException {
			
			
			return null;
		}

		@Override
		public JsonElement serialize(Object arg0, Type arg1, JsonSerializationContext arg2) {
			return null;
		}
		
	}

}
