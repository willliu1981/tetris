package com.tool.gson;

public abstract class GsonWrapper<T> implements Register {

	protected T t;

	public GsonWrapper() {
	}

	public GsonWrapper(T t) {
		this.t = t;
	}

	public String toJson() {
		return this.getGson().toJson(this);
	}

	public GsonWrapper<?> fromJson(String jsonStr) {
		return this.getGson().fromJson(jsonStr, this.getClass());
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

}
