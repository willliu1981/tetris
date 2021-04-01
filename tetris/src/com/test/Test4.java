package com.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Test4 {
	final static String defaultName = "default";
	static Map<String, Car> mapCar = new HashMap<>();
	static {
		mapCar.put(defaultName, new Car("c1"));
	}

	public static void main(String[] args) {
		System.out.println(run());

	}

	static Car run() {
		if (!getCar().isPresent()) {
			return null;
		}
		Car car=getCar().get();
		car.setAge(10);

		return car;
	}

	static Optional<Car> getCar() {
		Optional<Car> op = Optional.empty();
		if (mapCar.containsKey(defaultName)) {
			op = Optional.of(mapCar.get(defaultName));
		}
		return op;
	}

	static class Car {
		String name;
		int age;

		public Car(String name) {
			this.name = name;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String toString() {
			return this.name + " :age=" + this.age;
		}
	}

}
