package com.main.control.signgetter;

import java.util.HashMap;
import java.util.Map;

import com.main.model.Sign;
import com.tool.Cycle;

/*
 * for get Sign
 */
public abstract class SignGetter<V extends Sign> implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Map<Enum<?>, Sign> mapSign = new HashMap<>();
	
	public  void remove(Enum<?> type) {
		this.mapSign.remove(type);
	}
	
	/*
	 * get and set
	 */

	public Sign getSign(Enum<?> type) {
		return this.mapSign.get(type);
	}

	abstract public void addSign(Enum<?> type, int x, int y);

	public void addSign(Enum<?> type, Sign sign) {
		mapSign.put(type, sign);
	}

	abstract public Enum<?>[] getTypes();

	abstract public V getDefaultSign();

	public Map<Enum<?>, Sign> getSignMap() {
		return this.mapSign;
	}
}
