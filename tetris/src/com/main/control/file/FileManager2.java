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
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
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
import com.main.model.Sign;

public class FileManager2 {
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
					.registerTypeAdapter(Map.class, new JsonDeserializer<Map<SignType, SignGetter<? extends Sign>>>() {
						@Override
						public Map<SignType, SignGetter<? extends Sign>> deserialize(JsonElement jElement, Type jType,
								JsonDeserializationContext context) throws JsonParseException {
							Set<Entry<String, JsonElement>> jObjSet = jElement.getAsJsonObject().entrySet();
							Map<SignType, SignGetter<? extends Sign>> map = new HashMap<>();
							;
							for (Entry entry : jObjSet) {
								JsonObject value = (JsonObject) entry.getValue();
								SignGetter<? extends Sign> getter = null;
								SignType type = SignType.valueOf((String) entry.getKey());
								System.out.println(type);
								System.out.println(value);
								switch (type) {
								case MainSign:
									Gson g = new GsonBuilder().registerTypeAdapter(MainSignGetter.class,
											new JsonDeserializer<MainSignGetter>() {
												@Override
												public MainSignGetter deserialize(JsonElement jEle, Type Jtype,
														JsonDeserializationContext context) throws JsonParseException {
													System.out.println("getter "+jEle);
													Type type = new TypeToken<Map<Enum<?>, ?>>() {
													}.getType();
													MainSignGetter getter = new Gson().fromJson(jElement, type);
													return getter;
												}
											}).create();
									getter = new Gson().fromJson(value, MainSignGetter.class);
									map.put(type, getter);
								default:
									throw new IllegalArgumentException("Gson:class no match");
								}
							}
							return map;

							/*
							 * String myType = jElement.getAsJsonObject().get("type").getAsString();
							 * JsonObject data = jElement.getAsJsonObject().get("data").getAsJsonObject();
							 * switch (myType) { case "MainSignGetter": return context.deserialize(data,
							 * MainSignGetter.class); default: throw new
							 * IllegalArgumentException("Gson:class no match"); }
							 */
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
			JsonSerializer<Map<SignType, SignGetter<? extends Sign>>> jsonSerializer = new JsonSerializer<Map<SignType, SignGetter<? extends Sign>>>() {
				@Override
				public JsonElement serialize(Map<SignType, SignGetter<? extends Sign>> jElement, Type type,
						JsonSerializationContext context) {
					JsonObject wrapper = new JsonObject();
					wrapper.addProperty("type", jElement.getClass().getSimpleName());
					wrapper.add("data", context.serialize(jElement));
					return wrapper;
				}
			};

			Gson g = new GsonBuilder().registerTypeAdapter(Map.class, jsonSerializer).create();

			writer.write(new Gson().toJson(map));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	class JsonAdapter implements JsonSerializer<Object>, JsonDeserializer<Object> {
		private static final String Classname = "classname";
		private static final String Data = "data";

		@Override
		public Object deserialize(JsonElement jElement, Type type, JsonDeserializationContext jContext)
				throws JsonParseException {
			JsonObject jObj = jElement.getAsJsonObject();
			JsonPrimitive prim = (JsonPrimitive) jObj.get(Classname);
			String className = prim.getAsString();
			Class Klass = getObjectClass(className);

			return jContext.deserialize(jObj.get(Data), Klass);
		}

		@Override
		public JsonElement serialize(Object jElement, Type type, JsonSerializationContext jContext) {
			JsonObject jObj = new JsonObject();
			jObj.addProperty(Classname, jElement.getClass().getName());
			jObj.add(Data, jContext.serialize(jElement));
			return jObj;
		}

		public Class getObjectClass(String className) {
			try {
				return Class.forName(className);
			} catch (ClassNotFoundException ex) {
				throw new JsonParseException(ex.getMessage());
			}
		}

	}

}
