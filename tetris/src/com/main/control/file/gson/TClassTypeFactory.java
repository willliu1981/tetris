package com.main.control.file.gson;

import com.main.control.manager.MainSignGetter;
import com.main.control.manager.MainSignGetter.GetterMainSginType;
import com.main.control.manager.ObstacleSignGetter;
import com.main.control.manager.ObstacleSignGetter.GetterObstacleSignType;
import com.main.control.manager.SignManager.SignType;
import com.main.model.MainSign;
import com.tool.direction.Direction;
import com.tool.gson.ClassTypeFactory;

/*
 * teteris 專用 ClassTypeFactory
 */
public class TClassTypeFactory extends ClassTypeFactory {
	private static  TClassTypeFactory factory;
	
	public TClassTypeFactory() {
		init();
	}

	@Override
	protected void init() {
		this.setClassType("MainSignGetter",MainSignGetter.class);
		this.setClassType("ObstacleSignGetter", ObstacleSignGetter.class);
		this.setClassType("GetterMainSginType",GetterMainSginType.class);
		this.setClassType("GetterObstacleSignType",GetterObstacleSignType.class);
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
