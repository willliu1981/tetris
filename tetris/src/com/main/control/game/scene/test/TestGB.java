package com.main.control.game.scene.test;

import com.main.control.game.Scene.Performance;

public class TestGB implements Performance {

	@Override
	public void start() {
		System.out.println("start: TestGB");
	}

	@Override
	public void update() {
		System.out.println("update: TestGB");
	}

}
