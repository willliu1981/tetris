package com.test;

import com.controller.file.FileManager;
import com.controller.manager.SignManager;
import com.controller.manager.keyvalue.MainSignGetter;
import com.model.Sign;

public class TestSignFile {
	public static void main(String s[]) {
		//*
		SignManager manager=SignManager.getManager(SignManager.SignType.MainSign);
		manager.addSign(MainSignGetter.GetterType.SignS, 4, 4);
		manager.addSign(MainSignGetter.GetterType.SignZ, 8, 8);

		Sign sign=manager.getSign(MainSignGetter.GetterType.SignS);
		System.out.println("before io signS="+sign);
		System.out.println("after io getter="+manager.getSignGetterMap().get(SignManager.SignType.MainSign));
		System.out.println("before io getter map size="+manager.getSignGetterMap().size());
		System.out.println("after io sign map="+manager.getSignGetterMap().get(SignManager.SignType.MainSign).getMap_Test());
		//*
		FileManager.writeSignDate();
		 //*/
		FileManager.loadSignDate();
		
		System.out.println("---------------");
		SignManager manager2=SignManager.getManager(SignManager.SignType.MainSign);
		Sign sign2=manager2.getSign(MainSignGetter.GetterType.SignS);
		System.out.println("before io signS="+sign2);
		System.out.println("after io getter="+manager2.getSignGetterMap().get(SignManager.SignType.MainSign));
		System.out.println("before io getter map size="+manager2.getSignGetterMap().size());
		System.out.println("after io sign map="+manager2.getSignGetterMap().get(SignManager.SignType.MainSign).getMap_Test());
	}
}
