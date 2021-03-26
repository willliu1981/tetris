package com.test;

import java.util.Optional;

public class Test {

	public static void main(String[] args) {
		T t=new T();
		T t2=new T();
		System.out.println(t.opt);
		System.out.println(t.opt==t2.opt);
		System.out.println(t.opt==null);

	}
	
	public static class T{
		Optional<String> opt;
	}

}
