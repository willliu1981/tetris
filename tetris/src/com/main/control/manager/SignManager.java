package com.main.control.manager;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.GsonBuilder;
import com.main.control.file.gson.serializer.EnumSerializer;
import com.main.model.Sign;
import com.tool.direction.Direction;

/*
 * 存取Sign相關資訊的管理器
 */
public class SignManager {

	public enum SignType {
		MAINSIGN, DIGITSIGN, OBSTACLE;

		public String toString() {
			return new GsonBuilder().registerTypeAdapter(this.getClass(), new EnumSerializer()).create().toJson(this);
		}
	}

	/*
	 * 用於 get SignGetter 的 sign type
	 */
	private SignType type;

	private SignManager(SignType type) {
		this.type = type;
	}

	public static SignManager getManager(SignType type) {
		return new SignManager(type);
	}

	static private Map<SignType, SignGetter<? extends Sign>> mapSignGetter;

	/*
	 * factory-init 首先創造一個繼承SignGetter類,於ClassTypeFactory 註冊SignGetter Class Type
	 * 和GetterSignType Class Type, 再到這裡新增一個SignType,於下註冊建好的SignGetter, 最後到
	 * AppManager 註冊SignGetter類 的GetterSignType
	 */
	static {
		mapSignGetter = new HashMap<>();
		mapSignGetter.put(SignType.MAINSIGN, new MainSignGetter());
		mapSignGetter.put(SignType.OBSTACLE, new ObstacleSignGetter());
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

	public void createNewSign(Enum<?> type) {
		this.getSignGetter().addSign(type, 0, 0);
	}

	public void setPoint(Enum<?> type, int x, int y) {
		this.getSignGetter().getSign(type).setPoint(x, y);
	}

	public void setPivot(Enum<?> type, int x, int y) {
		this.getSignGetter().getSign(type).setPivot(x, y);
	}

//	public Direction getDirection() {
//		return this.getSignGetter().getSign(type).getDirection();
//	}

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
