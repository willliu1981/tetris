package com.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

public class TestGson {
	static Map<CType, Car> map = new HashMap<>();
	static Map<CType, Car> newMap = new HashMap<>();

	public static void main(String[] args) {
		String fname = "data/testC.txt";
		Car c1 = new BatCar("bbb");
		Car c2 = new SuperCar("sss");
		map.put(CType.C1, c1);
		map.put(CType.C2, c2);
		// write(fname);
		read(fname);

		System.out.println(newMap.get(CType.C1));
		System.out.println(newMap.get(CType.C2));
		System.out.println(newMap);

	}

	static void read(String fname) {
		try (FileReader fr = new FileReader(fname); BufferedReader br = new BufferedReader(fr)) {
			StringBuilder sb = new StringBuilder();
			while (br.ready()) {
				sb.append(br.readLine());
			}
			Gson g = new GsonBuilder().registerTypeAdapter(Car.class, new JsonDeserializer<Car>() {

				@Override
				public Car deserialize(JsonElement json, Type arg1, JsonDeserializationContext context)
						throws JsonParseException {
					String type = json.getAsJsonObject().get("type").getAsString();
					JsonObject data = json.getAsJsonObject().get("data").getAsJsonObject();

					switch (type) {
					case "BatCar":
						return context.deserialize(data, BatCar.class);
					case "SuperCar":
						return context.deserialize(data, SuperCar.class);
					default:
						throw new IllegalArgumentException("No match");
					}

				}
			}).create();

			Type type = new TypeToken<Map<CType, Car>>() {
			}.getType();

			newMap = g.fromJson(sb.toString(), type);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void write(String fname) {
		try (FileWriter fw = new FileWriter(fname)) {
			JsonSerializer<Car> carJsonSerializer = new JsonSerializer<Car>() {
				@Override
				public JsonElement serialize(Car car, Type arg1, JsonSerializationContext context) {
					final JsonObject wrapper = new JsonObject();
					wrapper.addProperty("type", car.getClass().getSimpleName());
					wrapper.add("data", new Gson().toJsonTree(car));
					return wrapper;
				}

			};

			Gson g = new GsonBuilder().registerTypeAdapter(BatCar.class, carJsonSerializer)
					.registerTypeAdapter(SuperCar.class, carJsonSerializer).create();

			String j = g.toJson(map);
			fw.write(j);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static abstract class Car {
		String name;

		public Car() {
		}

		public Car(String name) {
			this.name = name;
		}

		public String toString() {
			return this.name;
		}
	}

	static class BatCar extends Car {

		public BatCar(String name) {
			super(name);
		}

	}

	static class SuperCar extends Car {

		public SuperCar(String name) {
			super(name);
		}

	}

	static enum CType {
		C1, C2
	}

}
