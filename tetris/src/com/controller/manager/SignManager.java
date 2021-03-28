package com.controller.manager;

import java.util.HashMap;
import java.util.Map;

import com.controller.manager.keyvalue.MainSignGetter;
import com.controller.manager.keyvalue.MainSignGetter.GetterType;
import com.controller.manager.keyvalue.SignGetter;
import com.model.Sign;

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
	{
		mapSignGetter = new HashMap<>();
		mapSignGetter.put(SignType.MainSign, new MainSignGetter());
	}

	/*
	 * get and set
	 */

	public Sign getSign(Enum<?> type) {
		return this.getGetter().getSign(type);
	}

	public void addSign(Enum<?> type, int x, int y) {
		this.getGetter().addSign(type, x, y);
	}

	protected SignGetter<?> getGetter() {
		return SignManager.mapSignGetter.get(this.type);
	}
	
	public static Map<SignType, SignGetter<? extends Sign>> getSignGetterMap(){
		return mapSignGetter;
	}
	
	public static void setSignGetterMap(Map<SignType, SignGetter<? extends Sign>> map) {
		mapSignGetter=map;
	}

}
