package com.tool;

import java.util.HashMap;
import java.util.Map;

public class Session {
	private Map<String ,Object> mapSession=new HashMap<>();
	
	public void addAttribute(String name,Object obj) {
		this.mapSession.put(name, obj);
	}
	
	public Object getAttribute(String name) {
		if(this.mapSession.containsKey(name)) {
			return this.mapSession.get(name);
		}else {
			return null;
		}
	}
	
	public void clear() {
		this.mapSession.clear();
	}
	
}
