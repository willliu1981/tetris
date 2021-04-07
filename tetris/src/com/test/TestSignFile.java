package com.test;

import com.main.control.file.FileManager;
import com.main.control.manager.SignManager;
import com.main.control.manager.getter.MainSignGetter;
import com.main.model.Sign;

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
