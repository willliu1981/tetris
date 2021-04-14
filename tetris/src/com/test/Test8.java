package com.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test8 {

	public static void main(String[] args) throws CloneNotSupportedException {

		Set<String> setstr=new HashSet<>();
		setstr.add("1");
		setstr.add("2");
		setstr.add("3");
		
		List<Integer> lstis=setstr.stream().map(Integer::valueOf).collect(Collectors.toList());
		lstis.stream().map(x->x*2).forEach(System.out::println);

	}

	static class Task extends TimerTask {

		@Override
		public void run() {
			System.out.println("ttt");
		}

	}

}
