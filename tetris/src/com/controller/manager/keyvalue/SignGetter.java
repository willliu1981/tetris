package com.controller.manager.keyvalue;

import java.util.HashMap;
import java.util.Map;

import com.model.Sign;

/*
 * for get Sign
 */
public abstract class SignGetter< V extends Sign> {

	protected Map<Enum<?>, V> mapSign;
	
	protected SignGetter() {
		mapSign=new HashMap<>();
	}

	public Sign getSign(Enum<?> type) {
		return this.mapSign.get(type);
	}

	abstract public void addSign(Enum<?> type, int x,int y);
}
