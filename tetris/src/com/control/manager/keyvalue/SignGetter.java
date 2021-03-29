package com.control.manager.keyvalue;

import java.util.HashMap;
import java.util.Map;

import com.model.Sign;

/*
 * for get Sign
 */
public abstract class SignGetter< V extends Sign> implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Map<Enum<?>, V> mapSign;
	
	protected SignGetter() {
		mapSign=new HashMap<>();
	}

	public Sign getSign(Enum<?> type) {
		return this.mapSign.get(type);
	}

	abstract public void addSign(Enum<?> type, int x,int y);
	
	
	public Map<Enum<?>, V> getSignMap(){
		return this.mapSign;
	}
}