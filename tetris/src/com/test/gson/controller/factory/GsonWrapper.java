package com.test.gson.controller.factory;

import com.google.gson.Gson;
import com.test.gson.controller.deserializer.CustDeserializer;
import com.test.gson.controller.serializer.CustSerializer;

public abstract class GsonWrapper<T> implements CustSerializer<T>,CustDeserializer<T> {
															   
	protected T t;

	public GsonWrapper() {
	}

	public GsonWrapper(T t) {
		this.t = t;
	}
	
	public String toJson() {
		return this.getGson().toJson(this);
	}
	
	public T fromJson(String jsonStr) {
		return (T) this.getGson().fromJson(jsonStr, this.getClass());
	}
	
	
	/*
	 * get and set
	 */

	public void set(T t) {
		this.t = t;
	}

	public T get() {
		return this.t;
	}

	@Override
	public Gson getGson() {
		return CustSerializer.super.getGson();
	}
}
