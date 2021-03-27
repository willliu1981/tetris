package com.test;

import java.util.Optional;

import com.tool.TPoint;

public class Test {

	public static void main(String[] args) {
		T t=new T();
		T t2=new T();
		System.out.println(t.opt);
		System.out.println(t.opt==t2.opt);
		System.out.println(t.opt==null);
		
		System.out.println("equal...");
		TPoint p1=new TPoint(1,1);
		TPoint p2=new TPoint(1,2);
		TPoint p3=p1;
		System.out.println(p1.equals(p2));
		
		
	}
	
	public static class T{
		Optional<String> opt;
	}

}
