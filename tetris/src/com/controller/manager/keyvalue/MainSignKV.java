package com.controller.manager.keyvalue;

import com.controller.manager.keyvalue.MainSignKV.MainSignType;
import com.model.MainSign;

public class MainSignKV extends SignKV<MainSignType, MainSign> {

	static public enum MainSignType {
		SignS, SignZ, SignT, SignL, SignJ, SignO, SignI
	}

	public MainSignKV() {
		this.addSign(MainSignType.SignS, 1, 2);
		this.addSign(MainSignType.SignZ, 2, 8);
	}

	@Override
	protected void addSign(Enum<MainSignType> type, int x, int y) {
		MainSign s = new MainSign(x, y);
		this.mapSign.put((MainSignType) type, s);

	}

}
