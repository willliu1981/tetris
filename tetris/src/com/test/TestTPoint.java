package com.test;

import com.model.MainSign;
import com.model.Sign;

public class TestTPoint {

	public static void main(String[] args) {
		Sign s=new MainSign();
		System.out.println(s);
		Sign s2=new MainSign("xxx");
		System.out.println(s2);
		Sign s3=new MainSign(2,3);
		System.out.println(s3);
	}

}
