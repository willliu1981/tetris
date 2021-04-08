package com.test.gson.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import com.test.gson.controller.serializer.CustSerializer;

public class Person {
	private String name;

	public Person(String name) {
		this.name = name;
	}

	/*
	 * get and set
	 */

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Person)) {
			return false;
		}

		Person n = (Person) obj;
		if (this.name == null && n.name == null) {
			return true;
		}

		if (n.name != null && this.name != null && this.name.equals(n.name)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		if (this.name == null) {
			return 0;
		} else {
			return this.name.hashCode();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String firstName) {
		name = firstName;
	}

	public String toString() {
		String jsonStr=new GsonBuilder().registerTypeAdapter(this.getClass(), (JsonSerializer<Person>)  ( elem,typeOfOri,context)->{
				JsonObject jo=new JsonObject();
				jo.addProperty("type", elem.getClass().getSimpleName());
				jo.add("data", new Gson().toJsonTree(elem));
			return jo;
		}).create() .toJson(this);
		return jsonStr;
	}

}
