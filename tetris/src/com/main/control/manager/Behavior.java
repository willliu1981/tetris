package com.main.control.manager;

import java.util.HashMap;
import java.util.Map;

public abstract class Behavior {

	protected Map<String, Object> request = new HashMap<>();

	public void setParameter(String name, Object obj) {
		this.request.put(name, obj);
	}

	public Object getParameter(String name) {
		if (this.request.containsKey(name)) {
			return this.request.get(name);
		} else {
			return null;
		}
	}

	public abstract void run();
}
