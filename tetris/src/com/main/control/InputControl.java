package com.main.control;

import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import com.main.control.manager.MainSignGetter;
import com.main.control.manager.SignManager;
import com.main.model.Sign;
import com.main.view.MainView;
import com.tool.behavior.Behavior;
import com.tool.behavior.IBehavior;

/*
 * 遊戲進行所輸入的指令的控制器
 */
public class InputControl implements IBehavior {
	private Behavior behavior;

	@Override
	public void run() {
		this.getBehavior().run();

	}

	/*
	 * get and set
	 */

	public Behavior getBehavior() {
		if (this.behavior == null) {
			this.behavior = new Behavior() {

				@Override
				public void run() {
					Sign sign = SignManager.getManager(SignManager.SignType.MAINSIGN)
							.getSign(MainSignGetter.GetterMainSginType.SIGNJ);
					KeyEvent e = (KeyEvent) this.getParameter("event");

					if (!(e == null)) {

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
			};
		}
		return behavior;
	}

	public void setParameter(String name, Object obj) {
		this.getBehavior().setParameter(name, obj);
	}

	public Object getParameter(String name) {
		return this.getBehavior().getParameter(name);
	}

}
