package com.tool.gson;

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
	
	/*
	 * 與get 不同的是,它經由 jsonString 建立新的物件
	 */
	public T getFromJson(String jsonStr) {
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
