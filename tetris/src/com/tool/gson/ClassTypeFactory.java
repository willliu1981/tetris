package com.tool.gson;

import java.util.HashMap;
import java.util.Map;

import com.test.gson.model.Eagle;
import com.test.gson.model.Penguin;
import com.test.gson.model.Sparrow;

public abstract class ClassTypeFactory {

	private Map<String, Class<?>> mapClass = new HashMap<>();

	/*
	 * get and set
	 */

	public ClassTypeFactory getFactory() {
		return new ClassTypeFactory() {};
	}

	public void setClassType(String name, Class<?> clazz) {
		mapClass.put(name, clazz);
	}

	public Class<?> getClassType(String name) {
		Class<?> clazz = mapClass.get(name);
		if (clazz == null) {
			throw new RuntimeException("ClassTypeFactory : not match with " + name);
		}
		return clazz;
	}

}
