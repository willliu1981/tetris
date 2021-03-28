package com.test;

import java.util.Optional;

import com.tool.Direction;

public class Test {

	public static void main(String[] args) {
		T t=new T();
		T t2=new T();
		System.out.println(t.opt);
		System.out.println(t.opt==t2.opt);
		System.out.println(t.opt==null);
		
		System.out.println("equal...");
		Direction p1=new Direction(1,1);
		Direction p2=new Direction(1,2);
		Direction p3=p1;
		System.out.println(p1.equals(p2));
		
		
		
	}
	
	public static class T{
		Optional<String> opt;
	}

}
