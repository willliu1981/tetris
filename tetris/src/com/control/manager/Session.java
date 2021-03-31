package com.control.manager;

import java.util.HashMap;
import java.util.Map;

public class Session {
	private Map<String ,Object> session=new HashMap<>();
	
	public void addSesstion(String name,Object obj) {
		this.session.put(name, obj);
	}
	
	public Object getSession(String name) {
		if(this.session.containsKey(session)) {
			return this.session.get(name);
		}else {
			return null;
		}
	}
	
}
