package com.main.control.signgetter;

import com.google.gson.GsonBuilder;
import com.main.control.file.gson.serializer.EnumSerializer;
import com.main.model.MainSign;
import com.main.model.ObstacleSign;
import com.main.model.Sign;

public class ObstacleSignGetter extends SignGetter<ObstacleSign> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static public enum GetterObstacleSignType {
		WALL, REMAINS, WALLCUBE;

		public String toString() {
			return new GsonBuilder().registerTypeAdapter(this.getClass(), new EnumSerializer()).create().toJson(this);
		}
	}

	@Override
	public void addSign(Enum<?> type, int x, int y) {
		this.mapSign.put((GetterObstacleSignType) type, new ObstacleSign(x, y));
	}

	@Override
	public Enum<?>[] getTypes() {
		return GetterObstacleSignType.values();
	}

	@Override
	public ObstacleSign getDefaultSign() {
		ObstacleSign sign = new ObstacleSign(0, 0);
		sign.addCube(0, 0);
		return sign;
	}

}
