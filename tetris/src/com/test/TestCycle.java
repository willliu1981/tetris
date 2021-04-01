package com.test;

import com.tool.Cycle;

public class TestCycle {

	public static void main(String[] args) {

			Cycle<String> cycle=new Cycle<>();
			cycle.add("A");
			cycle.add("B");
			cycle.add("C");
			cycle.add("D");
			cycle.add("E");
			cycle.add("F");
			cycle.add("G");

			System.out.println(cycle);
			
			System.out.println(cycle.get(3));
			System.out.println(cycle);
			
			cycle.rotateTo(3);
			System.out.println(cycle);
			System.out.println(cycle.get());
	}

}
