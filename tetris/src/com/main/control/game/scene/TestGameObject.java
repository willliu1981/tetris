package com.main.control.game.scene;

import com.main.control.game.Scene.Performance;
import com.main.control.game.input.Input;
import com.main.control.manager.AppManager;
import com.main.model.Sign;
import com.main.view.MainView;
import com.tool.Session;

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
			Sign sign=AppManager.getCurrentSign();
			System.out.println(sign);
			sign.setPoint(sign.getX()+h, sign.getHeight());
			//Session s=MainView.getSession().getAttribute("")
		}
		
	}

}
