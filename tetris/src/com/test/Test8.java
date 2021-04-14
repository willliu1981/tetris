package com.test;

import com.main.model.MainSign;
import com.main.model.Sign;
import com.tool.direction.Direction;

public class Test8 {

	public static void main(String[] args) throws CloneNotSupportedException {

		Sign s1 = new MainSign("s111", 2, 2);

		Sign s2 = s1.clone();

		System.out.println(s2);
		s1.setPoint(1, 1);
		System.out.println(s2);

		Direction d1 = new Direction(1, 9);			
		Direction d2 = d1.clone();
		System.out.println(d2);
		d1.setPoint(11, 19);
		System.out.println(d2);

	}

}
