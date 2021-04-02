package com.test;

import java.util.Optional;

import com.main.control.manager.SignManager;
import com.main.control.manager.getter.MainSignGetter;
import com.main.control.manager.getter.MainSignGetter.GetterType;
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
		//s3.addCube( 7, 9);
		//s3.addCube( 7, 9);
		System.out.println(s3);
		//System.out.println(s3.getCubeMap());
		
		
		
		//ss=Optional.of(new Size(1,1));
		System.out.println(ss);
		setSize(8, 8);
		System.out.println(ss);
		
		System.out.println("manager...");
		SignManager manager= SignManager.getManager(SignManager.SignType.MainSign);
		manager.addSign(GetterType.SignS, 1, 2);
		manager.addSign(GetterType.SignZ, 3, 5);
		Sign sign=manager.getSign(MainSignGetter.GetterType.SignL);
		System.out.println(sign);
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

