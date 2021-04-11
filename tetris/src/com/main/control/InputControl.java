package com.main.control;

import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import com.main.control.manager.SignManager;
import com.main.model.MainSignGetter;
import com.main.model.Sign;
import com.main.view.MainView;
import com.tool.behavior.Behavior;

/*
 * 遊戲進行所輸入的控制器
 */
public class InputControl extends Behavior {
	@Override
	public void run() {
		Sign sign = SignManager.getManager(SignManager.SignType.MAINSIGN).getSign(MainSignGetter.GetterType.SIGNJ);
		KeyEvent e = (KeyEvent) this.getParameter("event");

		switch (e.getKeyCode()) {
		case 37:// left
			sign.setPoint(sign.getX() - 1, sign.getY());
			break;
		case 38:// up
			sign.rotateRight();
			break;
		case 39:// right
			sign.setPoint(sign.getX() + 1, sign.getY());
			break;
		case 40:// down
			sign.setPoint(sign.getX(), sign.getY() + 1);
			break;

		default:
			break;
		}

		((JPanel) MainView.getSession().getAttribute("main_panel")).repaint();
	}
}
