package com.main.model;

import com.google.gson.GsonBuilder;
import com.main.control.file.gson.serializer.EnumSerializer;

public class MainSignGetter extends SignGetter<MainSign> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static public enum GetterType {
		SignS, SignZ, SignT, SignL, SignJ, SignO, SignI;

		public String toString() {
			return new GsonBuilder().registerTypeAdapter(this.getClass(), new EnumSerializer()).create().toJson(this);
		}
	}

	@Override
	public void addSign(Enum<?> type, int x, int y) {
		this.mapSign.put((GetterType) type, new MainSign(x, y));
	}

}
