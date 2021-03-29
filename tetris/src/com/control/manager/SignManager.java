package com.control.manager;

import java.util.HashMap;
import java.util.Map;

import com.control.manager.keyvalue.MainSignGetter;
import com.control.manager.keyvalue.SignGetter;
import com.model.Sign;
import com.tool.Direction;

public class SignManager {

	public enum SignType {
		MainSign, DigitSign
	}

	private SignType type;

	private SignManager(SignType type) {
		this.type = type;
	}

	public static SignManager getManager(SignType type) {
		return new SignManager(type);
	}

	static private Map<SignType, SignGetter<? extends Sign>> mapSignGetter;

	/*
	 * factory-init
	 */
	static {
		mapSignGetter = new HashMap<>();
		mapSignGetter.put(SignType.MainSign, new MainSignGetter());
	}

	/*
	 * get and set
	 */

	public Sign getSign(Enum<?> type) {
		return this.getSignGetter().getSign(type);
	}

	public void addSign(Enum<?> type, int x, int y) {
		this.getSignGetter().addSign(type, x, y);
	}

	public void setPoint(Enum<?> type, int x, int y) {
		this.getSignGetter().getSign(type).setPoint(x, y);
	}

	public void setPivot(Enum<?> type, int x, int y) {
		this.getSignGetter().getSign(type).setPivot(x, y);
	}

	public Direction getDirection() {
		return this.getSignGetter().getSign(type).getDirection();
	}

	public SignGetter<?> getSignGetter() {
		return SignManager.mapSignGetter.get(this.type);
	}

	public static Map<SignType, SignGetter<? extends Sign>> getSignGetterMap() {
		return mapSignGetter;
	}

	public static void setSignGetterMap(Map<SignType, SignGetter<? extends Sign>> map) {
		mapSignGetter = map;
	}

}
