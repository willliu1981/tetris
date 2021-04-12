package com.test;

import com.main.control.file.FileManager;
import com.main.control.manager.MainSignGetter;
import com.main.control.manager.SignManager;
import com.main.model.Sign;

public class TestSignFile {
	public static void main(String s[]) {
		//*
		SignManager manager = SignManager.getManager(SignManager.SignType.MAINSIGN);
		manager.addSign(MainSignGetter.GetterMainSginType.SIGNS, 4, 4);
		manager.addSign(MainSignGetter.GetterMainSginType.SIGNZ, 8, 8);

		Sign sign = manager.getSign(MainSignGetter.GetterMainSginType.SIGNS);
		System.out.println("signS=" + sign);
		System.out.println("getter=" + SignManager.getSignGetterMap().get(SignManager.SignType.MAINSIGN));
		System.out.println("getter map size=" + SignManager.getSignGetterMap().size());
		//*
		FileManager.writeSignDate();
		 ///*/
		FileManager.loadSignDate();

		System.out.println("---------------");
		SignManager manager2 = SignManager.getManager(SignManager.SignType.MAINSIGN);
		Sign sign2 = manager2.getSign(MainSignGetter.GetterMainSginType.SIGNZ);
		System.out.println("signS=" + sign2);
		System.out.println("getter=" + SignManager.getSignGetterMap().get(SignManager.SignType.MAINSIGN));
		System.out.println("getter map size=" + SignManager.getSignGetterMap().size());
	}
}
