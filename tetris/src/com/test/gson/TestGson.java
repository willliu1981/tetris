package com.test.gson;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.test.gson.control.deserializer.BirdsDeserializer;
import com.test.gson.control.serializer.BirdsSerializer;
import com.test.gson.model.Birds;
import com.test.gson.model.Eagle;
import com.test.gson.model.Penguin;

public class TestGson {

	public static void main(String[] args) {
		Map<String, Birds> mapPet = new HashMap<>();
		Eagle eagle = new Eagle();
		eagle.setNameInfo(new Birds.Name("Alice"), Birds.NameInfoType.Self);
		eagle.setNameInfo(new Birds.FullName("Peter", "Wu"), Birds.NameInfoType.Master);
		eagle.setFlyHeight(100);
		eagle.setHuntingAtk(3600);

		Penguin penguin = new Penguin();
		penguin.setNameInfo(new Birds.Name("Tiffany"), Birds.NameInfoType.Self);
		penguin.setNameInfo(new Birds.FullName("Mary", "Li"), Birds.NameInfoType.Master);
		penguin.setSwimmingSpeed(20);

		Map<String, Birds> toJsonPetMap = new HashMap<>();// <master,pet>
		toJsonPetMap.put("Peter", eagle);
		toJsonPetMap.put("Mary", penguin);

		String json = toJson(toJsonPetMap);

		System.out.println("toJson" + json);
		System.out.println("fromJsonPetMap->" + fromJson(json));

	}

	static String toJson(Map<String, Birds> mapPet) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Map.class, new BirdsSerializer()).create();

		return gson.toJson(mapPet);
	}

	static Map<String, Birds> fromJson(String jsonStr) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Birds.class, new BirdsDeserializer()).create();

		return gson.fromJson(jsonStr, new TypeToken<Map<String, Birds>>() {
		}.getType());

	}

}
