package com.main.control.game.scene;

import java.util.Date;

import com.main.control.game.Scene.Performance;
import com.main.control.manager.AppManager;
import com.main.control.manager.GameManager;
import com.main.model.Sign;

public class SignControlScript implements Performance {
	private static int interval =360;
	private long time;

	@Override
	public void start() throws Exception {
		System.out.println("start: SignControlScript");
	}

	@Override
	public void update() throws Exception {
		if (new Date().getTime() > time) {
			Sign sign = AppManager.getCurrentSign();
			Sign pioneer = sign.clone();
			pioneer.setPoint(pioneer.getX(), pioneer.getY()+1);
			if (!GameManager.isCollide(pioneer)) {
				sign.setPoint(sign.getX(), sign.getY()+1);
			}
			time = new Date().getTime() + interval;
		}
	}

}
