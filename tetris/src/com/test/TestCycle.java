package com.test;

import com.tool.Cycle;

public class TestCycle {

	public static void main(String[] args) {

			Cycle<String> cycle=new Cycle<>();
			cycle.add("A");
			cycle.add("B");
			cycle.add("C");
			cycle.add("D");
			
			System.out.println(cycle);
			System.out.println(cycle.get());
			
			cycle.rotateLeft();
			System.out.println(cycle);
			System.out.println(cycle.get());
			
			cycle.offerFirst("rrr");
			System.out.println(cycle);
			System.out.println(cycle.get());
		
			cycle.rotateLeft();
			System.out.println(cycle);
			System.out.println(cycle.get());
	}

}
