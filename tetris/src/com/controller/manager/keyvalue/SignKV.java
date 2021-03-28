package com.controller.manager.keyvalue;

import java.util.HashMap;
import java.util.Map;

import com.model.Sign;

/*
 * for get Sign
 */
public abstract class SignKV<K extends Enum<K>, V extends Sign> {

	protected Map<K, V> mapSign;
	
	protected SignKV() {
		mapSign=new HashMap<>();
	}

	public Sign getSign(Enum<?> key) {
		return this.mapSign.get(key);
	}

	abstract protected void addSign(Enum<K> type, int x,int y);
}
