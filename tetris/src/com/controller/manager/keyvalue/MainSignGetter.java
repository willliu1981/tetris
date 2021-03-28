package com.controller.manager.keyvalue;

import com.model.MainSign;

public class MainSignGetter extends SignGetter<MainSign> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static public enum GetterType {
		SignS, SignZ, SignT, SignL, SignJ, SignO, SignI
	}

	public MainSignGetter() {

	}

	@Override
	public void addSign(Enum<?> type, int x, int y) {
		this.mapSign.put((GetterType) type, new MainSign(x, y));
	}


}
