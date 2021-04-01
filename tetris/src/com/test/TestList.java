package com.test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class TestList {
	public static void main(String s[]) {
		Car c1 = new Car("C1");
		Car c2 = new Car("C2");
		Car c3 = new Car("C3");
		Car c4 = new Car("C4");

		/*
		LinkedList<Car> q = new LinkedList<>();
		q.add(c1);
		q.add(c2);
		q.add(c3);
		q.add(c4);
		
		q.add(q.remove());
		q.add(q.remove());
		q.add(q.remove());
		q.add(q.remove());
		q.add(q.remove());
		
		System.out.println(q);
		System.out.println(q.peek());
		System.out.println("-------------");
		//*/
		
		//*
		ArrayDeque<Car> q2 = new ArrayDeque<>();
		q2.add(c1);
		q2.add(c2);
		q2.add(c3);
		q2.add(c4);
		
		q2.add(q2.poll());
		q2.add(q2.poll());
		q2.add(q2.poll());
		q2.add(q2.poll());
		q2.add(q2.poll());
		
		System.out.println(q2);
		System.out.println(q2.peek());
		//*/
		
	}

	static class Car {
		String name;

		Car(String name) {
			this.name = name;
		}
		
		public String toString() {
			return this.name;
		}
	}
}
