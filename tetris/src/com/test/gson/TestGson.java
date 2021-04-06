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
		Eagle eagle = new Eagle();
		eagle.setNameInfo(new Birds.Name("Alice"), Birds.NameInfoType.Self);
		eagle.setNameInfo(new Birds.FullName("Peter", "Wu"), Birds.NameInfoType.Master);
		eagle.setFlyHeight(100);
		eagle.setHuntingAtk(3600);

		Penguin penguin = new Penguin();
		penguin.setNameInfo(new Birds.Name("Tiffany"), Birds.NameInfoType.Self);
		penguin.setNameInfo(new Birds.FullName("Mary", "Li"), Birds.NameInfoType.Master);
		penguin.setSwimmingSpeed(20);

		Map<String, Birds> mapBirds = new HashMap<>();// <master,pet>
		mapBirds.put("Peter", eagle);
		mapBirds.put("Mary", penguin);

		String jsonStr = toJson(mapBirds);

		System.out.println("toJson" + jsonStr);
		System.out.println("fromJsonPetMap->" + fromJson(jsonStr));

	}

	static String toJson(Map<String, Birds> mapPet) {
		/*
		 * 這裡比較奇怪,序列map 時,需要將每個可能的子類別type 註冊上去,
		 * 若該map 是屬於一個類別下的組件,註冊其父類別(map 的 element 的父類別)就可以,
		 * 例如:BirdSerializer 註冊 Name
		 */
		Gson gson = new GsonBuilder().registerTypeAdapter(Eagle.class, new BirdsSerializer())
				.registerTypeAdapter(Penguin.class, new BirdsSerializer()).create();

		return gson.toJson(mapPet);
	}

	static Map<String, Birds> fromJson(String jsonStr) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Birds.class, new BirdsDeserializer()).create();

		return gson.fromJson(jsonStr, new TypeToken<Map<String, Birds>>() {
		}.getType());

	}

}
