package com.test;

import com.main.control.manager.SignManager.SignType;

public class Test7 {

	public static void main(String[] args) {
		String s="DigitSign";
		Object o=s;
		
		
		SignType t=SignType.valueOf(s);
		
		System.out.println(t.toString());
	}

}
