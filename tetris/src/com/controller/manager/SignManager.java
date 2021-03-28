package com.controller.manager;

import java.util.HashMap;
import java.util.Map;

import com.controller.manager.keyvalue.MainSignKV;
import com.controller.manager.keyvalue.SignKV;
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

	static private Map<SignType, SignKV<? extends Enum<?>, ? extends Sign>> mapSignKV;

	/*
	 * init
	 */
	{
		mapSignKV = new HashMap<>();
		mapSignKV.put(SignType.MainSign, new MainSignKV());
	}

	public Sign getSign(Enum<?> type) {
		return SignManager.mapSignKV.get(this.type).getSign(type);
	}

}
