package com.test.gson;

import java.util.HashMap;
import java.util.Map;

import com.test.gson.controller.wrapper.PersonBirdsMapWrapper;
import com.test.gson.model.Birds;
import com.test.gson.model.Eagle;
import com.test.gson.model.Penguin;
import com.test.gson.model.Person;

public class TestGson4 {

	/*
	 * Map 的 key 不能反序列?
	 */
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

		Map<Person, Birds<? >> mapBirds = new HashMap<>();// <master,pet>
		mapBirds.put(new Person("Peter"), eagle);
		mapBirds.put(new Person("Mary"), penguin);
		
		PersonBirdsMapWrapper wrapper=new PersonBirdsMapWrapper(mapBirds);

		String jsonStr = wrapper.toJson();

		System.out.println("toJson->" + jsonStr);
		
		PersonBirdsMapWrapper  newWrapper= (PersonBirdsMapWrapper) wrapper.fromJson(jsonStr);
		
		System.out.println("fromJson->");
		newWrapper.get() .forEach((x, y) -> System.out.format("%s , %s\n", x, y));

		System.out.println("test get->"+newWrapper.get() .get(new Person("Mary")));
	}

}
