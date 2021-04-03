package com.main.control.manager.getter;

import com.model.MainSign;

public class MainSignGetter extends SignGetter<MainSign> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static public enum GetterType {
		SignS, SignZ, SignT, SignL, SignJ, SignO, SignI
	}



	@Override
	public void addSign(Enum<?> type, int x, int y) {
		this.mapSign .put((GetterType) type, new MainSign(x, y));
	}


}
