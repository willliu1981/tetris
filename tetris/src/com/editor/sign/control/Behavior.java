package com.editor.sign.control;

import java.util.HashMap;
import java.util.Map;

public abstract class Behavior {
	
	protected Map<String,Object> session=new HashMap<>();
	
	public void setSession(String name,Object obj) {
		this.session.put(name, obj);
	}
	
	public Object getSession(String name) {
		return this.session.get(name);
	}
	
	public abstract void run() ;
}
