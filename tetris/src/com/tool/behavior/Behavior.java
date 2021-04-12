package com.tool.behavior;

import java.util.HashMap;
import java.util.Map;

public abstract class Behavior implements IBehavior {

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

}
