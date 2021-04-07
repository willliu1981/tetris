package com.test.gson;

import java.util.HashMap;
import java.util.Map;

import com.test.gson.controller.factory.BirdsMapWrapper;
import com.test.gson.model.Birds;
import com.test.gson.model.Eagle;
import com.test.gson.model.Penguin;

public class TestGson3 {

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

		/*
		 * toJson
		 */
		String jsonStr = new BirdsMapWrapper(mapBirds).toJson();
		System.out.println("toJson->" + jsonStr);
		
		/*
		 * fromJson
		 */
		BirdsMapWrapper newWraper = (BirdsMapWrapper) new BirdsMapWrapper().fromJson(jsonStr);
		System.out.println("fromJsonPetMap->");
		newWraper.get().forEach((x, y) -> System.out.format("%s , %s\n", x, y));

	}

}
