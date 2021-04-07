package com.test.gson;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.test.gson.controller.deserializer.BirdsDeserializer;
import com.test.gson.controller.serializer.BirdsSerializer;
import com.test.gson.model.Birds;
import com.test.gson.model.Cage;
import com.test.gson.model.Eagle;
import com.test.gson.model.Penguin;

public class TestGson2 {

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

		Cage cage = new Cage(mapBirds);

		String jsonStr = toJson(cage);

		System.out.println("toJson" + jsonStr);
		Cage newCage= fromJson(jsonStr);
		System.out.println("fromJsonPetMap->" );
		newCage.showInfo();

	}

	static String toJson(Cage cage) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Birds.class, new BirdsSerializer()).create();

		return gson.toJson(cage);
	}

	static Cage fromJson(String jsonStr) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Birds.class, new BirdsDeserializer()).create();

		return gson.fromJson(jsonStr, Cage.class);

	}

}
