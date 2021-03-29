package com.editor.sign.control;

import java.util.HashMap;
import java.util.Map;

public abstract class Behavior {
	
	protected Map<String,Object> request=new HashMap<>();
	
	public void setRequest(String name,Object obj) {
		this.request.put(name, obj);
	}
	
	public Object getRequest(String name) {
		return this.request.get(name);
	}
	
	public abstract void run() ;
}
