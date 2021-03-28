package com.controller.manager.keyvalue;

import com.model.MainSign;

public class MainSignGetter extends SignGetter< MainSign> {

	static public enum GetterType {
		SignS, SignZ, SignT, SignL, SignJ, SignO, SignI
	}

	public MainSignGetter() {
		
	}

	@Override
	public void addSign(Enum<?> type, int x, int y) {
		//if(this.mapSign.co)
		MainSign s = new MainSign(x, y);
		this.mapSign.put((GetterType) type, s);

	}



}
