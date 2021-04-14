package com.test;

import java.util.regex.Pattern;

import com.main.control.exception.TCastException;

public class Test9 {

	public static void main(String[] args) {
		Class<Test9> c=null;
		Test9 t=null;
		try {
			c = (Class<Test9>) Class.forName("com.test.Test8");
			System.out.println(c.getComponentType());
			t = c.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(ClassCastException e) {
			throw new  TCastException(Test9.class.getTypeName());
		}

//		if(c.ty instanceof Test9) {
//			
//		}

		t.show();
	}

	public void show() {
		System.out.println("xxx");
	}

}
