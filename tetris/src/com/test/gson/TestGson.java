package com.test.gson;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.test.gson.controller.deserializer.BirdsDeserializer;
import com.test.gson.controller.serializer.BirdsSerializer;
import com.test.gson.model.Birds;
import com.test.gson.model.Eagle;
import com.test.gson.model.Penguin;
import com.test.gson.model.Sparrow;

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

		Sparrow sparrow = new Sparrow();
		sparrow.setNameInfo(new Birds.Name("Cherry"), Birds.NameInfoType.Self);
		sparrow.setNameInfo(new Birds.FullName("John", "Wood"), Birds.NameInfoType.Master);
		sparrow.setNoise(25);

		Map<String, Birds> mapBirds = new HashMap<>();// <master,pet>
		mapBirds.put("Peter", eagle);
		mapBirds.put("Mary", penguin);
		// mapBirds.put("John", sparrow);//BirdDeserializer -> match type 的問題

		String jsonStr = toJson(mapBirds);

		System.out.println("toJson" + jsonStr);
		System.out.println("fromJsonPetMap->" + fromJson(jsonStr));

	}

	static String toJson(Map<String, Birds> mapPet) {
		/*
		 * 原始問題
		 *
		 * Gson gson = new GsonBuilder().registerTypeAdapter(Eagle.class, new
		 * BirdsSerializer()) .registerTypeAdapter(Penguin.class, new
		 * BirdsSerializer()).create();
		 * 
		 * return gson.toJson(mapPet);
		 */
		
		/*
		 * 解決後的程式片段,參考 Phip Giboli 大大的留言回覆
		 */
		Gson gson = new GsonBuilder().registerTypeAdapter(Birds.class, new BirdsSerializer())
			.create();

		return gson.toJson(mapPet,new TypeToken<Map<String, Birds>>() {
		}.getType());
	}

	static Map<String, Birds> fromJson(String jsonStr) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Birds.class, new BirdsDeserializer()).create();

		return gson.fromJson(jsonStr, new TypeToken<Map<String, Birds>>() {
		}.getType());

	}

}
