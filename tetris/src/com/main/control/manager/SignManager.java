package com.main.control.manager;

import java.util.HashMap;
import java.util.Map;

import com.main.model.MainSignGetter;
import com.main.model.Sign;
import com.main.model.SignGetter;
import com.tool.Direction;

public class SignManager {

	public enum SignType {
		MainSign, DigitSign;

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

	public static void initialize() {
		SignManager manager = SignManager.getManager(SignManager.SignType.MainSign);
		manager.addSign(MainSignGetter.GetterType.SignS);
		manager.addSign(MainSignGetter.GetterType.SignZ);
		manager.addSign(MainSignGetter.GetterType.SignT);
		manager.addSign(MainSignGetter.GetterType.SignL);
		manager.addSign(MainSignGetter.GetterType.SignJ);
		manager.addSign(MainSignGetter.GetterType.SignO);
		manager.addSign(MainSignGetter.GetterType.SignI);

		/*
		Sign signS = manager.getSign(MainSignGetter.GetterType.SignS);
		signS.setSize(4, 4);
		Sign signZ = manager.getSign(MainSignGetter.GetterType.SignZ);
		signZ.setSize(3, 7);
		Sign signT = manager.getSign(MainSignGetter.GetterType.SignT);
		signT.setSize(7, 3);
		*/
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

	public void addSign(Enum<?> type) {
		this.getSignGetter().addSign(type, 0, 0);
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
