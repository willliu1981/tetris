package com.test.gson.controller.factory;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

public abstract class GsonWrapper<T> implements JsonSerializer<T>,JsonDeserializer<T> {
															   
	protected T t;

	public GsonWrapper() {
	}

	public GsonWrapper(T t) {
		this.t = t;
	}

	public void set(T t) {
		this.t = t;
	}

	public T get() {
		return this.t;
	}
}
