package com.test.gson.controller.factory;

import java.util.HashMap;
import java.util.Map;

import com.test.gson.model.Birds;
import com.test.gson.model.Eagle;
import com.test.gson.model.Penguin;
import com.test.gson.model.Person;
import com.test.gson.model.Sparrow;

public class ClassTypeFactory {

	private static Map<String, Class> mapClass = new HashMap<>();

	/*
	 * init
	 */
	static {
		setClassType("Eagle", Eagle.class);
		setClassType("Penguin", Penguin.class);
		setClassType("Sparrow", Sparrow.class);
		setClassType("Person",Person.class);
		setClassType("FullName",Birds.FullName.class);
		setClassType("Name",Birds.Name.class);
	}

	/*
	 * get and set
	 */

	public static void setClassType(String name, Class clazz) {
		mapClass.put(name, clazz);
	}

	public static Class getClassType(String name) {
		Class clazz = mapClass.get(name);
		if (clazz == null) {
			throw new RuntimeException("ClassTypeFactory : not match with " + name);
		}
		return clazz;
	}

}
