package com.main.control.game.scene;

import com.main.control.game.Scene.Performance;
import com.main.control.game.input.Input;

public class TestGameObject implements Performance{

	@Override
	public void start() {
		System.out.println("start: TestGameObject");
	}

	@Override
	public void update() {
		int h=0;
		if((h=Input.getHorizontal())!=0) {
			System.out.println(h);
		}
		
	}

}
