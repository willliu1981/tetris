package com.tool;

import java.util.HashMap;
import java.util.Map;

public class Session {
	private Map<String ,Object> session=new HashMap<>();
	
	public void addAttribute(String name,Object obj) {
		this.session.put(name, obj);
	}
	
	public Object getAttribute(String name) {
		if(this.session.containsKey(name)) {
			return this.session.get(name);
		}else {
			return null;
		}
	}
	
	public void clear() {
		this.session.clear();
	}
	
}
