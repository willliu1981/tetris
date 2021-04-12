package com.tool;

import java.util.HashMap;
import java.util.Map;

public class Request {
private Map<String ,Object> mapRequest=new HashMap<>();
	
	public void addParameter(String name,Object obj) {
		this.mapRequest.put(name, obj);
	}
	
	public Object getParameter(String name) {
		if(this.mapRequest.containsKey(name)) {
			return this.mapRequest.get(name);
		}else {
			return null;
		}
	}
	
	public void clear() {
		this.mapRequest.clear();
	}
}
