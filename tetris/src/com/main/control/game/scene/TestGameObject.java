package com.main.control.game.scene;

import com.main.control.game.Input;
import com.main.control.game.Scene.Performance;

public class TestGameObject implements Performance{

	@Override
	public void start() {
		//System.out.println("start: TestGameObject");
	}

	@Override
	public void update() {
		//System.out.println("update: TestGameObject");
		int h=0;
		if((h=Input.getHorizontal())!=0) {
			System.out.println(h);
		}
		
	}

}
