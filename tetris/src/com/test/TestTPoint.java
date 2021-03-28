package com.test;

import java.util.Optional;

import com.controller.manager.SignManager;
import com.controller.manager.keyvalue.MainSignGetter;
import com.model.MainSign;
import com.model.Sign;
import com.sun.glass.ui.Size;


public class TestTPoint {
	static Optional <Size> ss=Optional.empty();

	public static void main(String[] args) {
		Sign s=new MainSign();
		System.out.println(s);
		Sign s2=new MainSign("xxx");
		System.out.println(s2);
		Sign s3=new MainSign(2,3);
		s3.setPivot(5, 6);
		s3.addCubeMap( 7, 9);
		s3.addCubeMap( 7, 9);
		System.out.println(s3);
		System.out.println(s3.getCubeMap());
		
		
		
		//ss=Optional.of(new Size(1,1));
		System.out.println(ss);
		setSize(8, 8);
		System.out.println(ss);
		
		System.out.println("manager...");
		SignManager manager= SignManager.getManager(SignManager.SignType.MainSign);
		Sign sign=manager.getSign(MainSignGetter.GetterType.SignS);
		System.out.println(ss);
	}
	
	static void setSize(int w,int h) {
		getSize().width=w;
		getSize().height=h;
	}
	
	static Size getSize() {
		ss=Optional.of(ss.orElseGet(Size::new));
		return ss.get();
	}

}

