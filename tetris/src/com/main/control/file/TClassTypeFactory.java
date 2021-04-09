package com.main.control.file;

import com.main.control.manager.SignManager.SignType;
import com.main.model.MainSign;
import com.main.model.MainSignGetter;
import com.main.model.MainSignGetter.GetterType;
import com.tool.Direction;
import com.tool.gson.ClassTypeFactory;

public class TClassTypeFactory extends ClassTypeFactory {
	private static  TClassTypeFactory factory;
	
	public TClassTypeFactory() {
		init();
	}

	@Override
	protected void init() {
		this.setClassType("MainSignGetter",MainSignGetter.class);
		this.setClassType("GetterType",GetterType.class);
		this.setClassType("SignType",SignType.class);
		this.setClassType("MainSign",MainSign.class);
		this.setClassType("Direction",Direction.class);
	}
	
	protected static TClassTypeFactory getFactory() {
		if(factory==null) {
			factory=new TClassTypeFactory();
		}
		return factory;
	}

	public static Class<?> getType(String name) {
		return getFactory().getClassType(name);
	}
	
	
}
