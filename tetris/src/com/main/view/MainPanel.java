package com.main.view;

import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.main.control.manager.AppManager;
import com.main.control.manager.SignManager;
import com.main.control.signgetter.MainSignGetter;
import com.main.control.signgetter.ObstacleSignGetter;
import com.main.model.Sign;
import com.test.mainview.behavior.InitViewBehavior;
import com.tool.behavior.BehaviorController;
import com.tool.direction.Direction;
import com.tool.draw.SignDrawer;
import com.tool.draw.XSignDrawer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainPanel() {

		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		XSignDrawer xdrawer = new XSignDrawer(this, AppManager.getCubesSize());
		xdrawer.setBackgroundSign(AppManager.getBackgroundSignList());
		while (xdrawer.hashNext()) {
			xdrawer.next();
			g.fill3DRect(xdrawer.getX(), xdrawer.getY(), xdrawer.getLenW(), xdrawer.getLenH(), true);
		}

		SignDrawer drawer = new SignDrawer(this, AppManager.getCubesSize());
		Sign mainSign = AppManager.getSign(AppManager.signType.MAINSIGN, AppManager.getterMainSignType.SIGNJ);
		drawer.setSign(mainSign);
		if (mainSign != null) {
			for (Direction d : mainSign.getCubeMap().keySet()) {
				g.fill3DRect(drawer.getX(d), drawer.getY(d), drawer.getLenW(), drawer.getLenH(), true);
			}
		}

	}

}
