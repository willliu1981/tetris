package com.test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.main.control.signgetter.MainSignGetter.GetterMainSginType;

public class Test10 {

	public static void main(String[] args) {
		GetterMainSginType[] types = GetterMainSginType.values();
		Random rand = new Random();
		GetterMainSginType type = rand.ints(1, 0, types.length).mapToObj(x -> types[x]).findFirst().get();

		System.out.println(type);
	}

}
