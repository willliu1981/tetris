package com.tool.behavior;

import java.util.HashMap;
import java.util.Map;

public abstract class Behavior implements IBehavior {

	static public class Request {
		private Map<String, Object> mapRequest = new HashMap<>();

		public void addParameter(String name, Object obj) {
			this.mapRequest.put(name, obj);
		}

		public Object getParameter(String name) {
			if (this.mapRequest.containsKey(name)) {
				return this.mapRequest.get(name);
			} else {
				return null;
			}
		}

		public void clear() {
			this.mapRequest.clear();
		}
	}

	protected Map<String, Object> mapRequest = new HashMap<>();
	
	/*
	 * get and set
	 */

	public void setParameter(String name, Object obj) {
		this.mapRequest.put(name, obj);
	}

	public Object getParameter(String name) {
		if (this.mapRequest.containsKey(name)) {
			return this.mapRequest.get(name);
		} else {
			return null;
		}
	}
	
	public void setRequest(Request request) {
		this.mapRequest.putAll(request.mapRequest);
		
	}

}
