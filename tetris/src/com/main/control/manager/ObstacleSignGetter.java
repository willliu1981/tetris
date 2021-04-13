package com.main.control.manager;

import com.google.gson.GsonBuilder;
import com.main.control.file.gson.serializer.EnumSerializer;
import com.main.model.MainSign;
import com.main.model.ObstacleSign;

public class ObstacleSignGetter extends SignGetter<MainSign> {

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

}
