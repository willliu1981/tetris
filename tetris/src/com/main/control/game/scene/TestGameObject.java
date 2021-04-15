package com.main.control.game.scene;

import java.util.Date;

import javax.swing.JPanel;

import com.main.control.game.Scene.Performance;
import com.main.control.game.input.Input;
import com.main.control.manager.AppManager;
import com.main.model.Sign;
import com.main.view.MainView;

public class TestGameObject implements Performance {

	private static int intervalH = 50;
	private long timeH;
	private boolean activeH;// 使用於 H軸 按一次的 delay 效果
	private static int intervalV = 50;
	private long timeV;
	private boolean activeV;// 使用於 V軸 按一次的 delay 效果
	private boolean activeRot;// 使用於 旋轉

	@Override
	public void start() {
		System.out.println("start: TestGameObject");
	}

	@Override
	public void update() {
		int h = 0;
		int v = 0;
		/*
		 * 左、右
		 */
		if ((h = Input.getHorizontal()) != 0) {
			if (new Date().getTime() > timeH) {
				timeH = new Date().getTime() + intervalH + (activeH ? 0 : intervalH * 1);
				moveH(h);
				activeH = true;
			}
		} else {
			timeH = 0;
			activeH = false;
		}

		/*
		 * 下
		 */
		if ((v = Input.getVertical()) != 0) {
			if (new Date().getTime() > timeV) {
				timeV = new Date().getTime() + intervalV + (activeV ? 0 : intervalV * 1);
				moveV(v);
				activeV = true;
			}
		} else {
			timeV = 0;
			activeV = false;
		}

		/*
		 * 旋轉
		 */
		if (Input.getRotate()) {
			if (!activeRot) {
				AppManager.getCurrentSign().rotateRight();
				activeRot=true;
			}
		} else {
			activeRot=false;
		}

		JPanel main_panel = (JPanel) MainView.getSession().getAttribute("main_panel");
		main_panel.repaint();
	}

	private void moveH(int x) {
		Sign sign = AppManager.getCurrentSign();
		sign.setPoint(sign.getX() + x, sign.getY());

	}

	private void moveV(int y) {
		Sign sign = AppManager.getCurrentSign();
		sign.setPoint(sign.getX(), sign.getY() + y);

	}

}
