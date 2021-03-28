package com.test;

import com.controller.file.FileManager;
import com.controller.manager.SignManager;
import com.controller.manager.keyvalue.MainSignGetter;
import com.model.Sign;

public class TestSignFile {
	public static void main(String s[]) {
		//*
		SignManager manager = SignManager.getManager(SignManager.SignType.MainSign);
		manager.addSign(MainSignGetter.GetterType.SignS, 4, 4);
		manager.addSign(MainSignGetter.GetterType.SignZ, 8, 8);

		Sign sign = manager.getSign(MainSignGetter.GetterType.SignS);
		System.out.println("signS=" + sign);
		System.out.println("getter=" + SignManager.getSignGetterMap().get(SignManager.SignType.MainSign));
		System.out.println("getter map size=" + SignManager.getSignGetterMap().size());
		//*
		FileManager.writeSignDate();
		 ///*/
		FileManager.loadSignDate();

		System.out.println("---------------");
		SignManager manager2 = SignManager.getManager(SignManager.SignType.MainSign);
		Sign sign2 = manager2.getSign(MainSignGetter.GetterType.SignZ);
		System.out.println("signS=" + sign2);
		System.out.println("getter=" + SignManager.getSignGetterMap().get(SignManager.SignType.MainSign));
		System.out.println("getter map size=" + SignManager.getSignGetterMap().size());
	}
}
