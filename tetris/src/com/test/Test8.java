package com.test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.main.model.MainSign;
import com.main.model.Sign;
import com.tool.direction.Direction;

public class Test8 {

	public static void main(String[] args) throws CloneNotSupportedException {

		Set<String > s1=new HashSet<>();
		s1.add("a");
		s1.add("b");
		
		Set<String > s2=new HashSet<>();
		s2.add("a2");
		s2.add("b2");
		
		s1.addAll(s2);
		System.out.println(s1);
		
		

	}

	static class Task extends TimerTask {

		@Override
		public void run() {
			System.out.println("ttt");
		}

	}

}
