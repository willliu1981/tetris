package com.main.view;

import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.main.control.manager.AppManager;
import com.main.control.manager.MainSignGetter;
import com.main.control.manager.ObstacleSignGetter;
import com.main.control.manager.SignManager;
import com.main.model.Sign;
import com.test.mainview.behavior.InitViewBehavior;
import com.tool.behavior.BehaviorController;
import com.tool.direction.Direction;
import com.tool.draw.SignDraw;

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
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Sign sign = SignManager.getManager(SignManager.SignType.MAINSIGN)
						.getSign(MainSignGetter.GetterMainSginType.SIGNJ);
				sign.rotateRight();
				MainPanel.this.repaint();

			}
		});

		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		SignDraw draw = new SignDraw(this, AppManager.getCubesSize());

		Sign mainSign = SignManager.getManager(SignManager.SignType.MAINSIGN)
				.getSign(MainSignGetter.GetterMainSginType.SIGNJ);
		draw.setSign(mainSign);
		System.out.println(mainSign);
		if (mainSign != null) {
			for (Direction d : mainSign.getCubeMap().keySet()) {
				g.fill3DRect(draw.getX(d), draw.getY(d), draw.getLenW(), draw.getLenH(), true);
			}
		}

		Sign wallSign = SignManager.getManager(SignManager.SignType.OBSTACLE)
				.getSign(ObstacleSignGetter.GetterObstacleSignType.WALL);
		draw.setSign(wallSign);
		if (wallSign != null) {
			for (Direction d : wallSign.getCubeMap().keySet()) {
				g.fill3DRect(draw.getX(d), draw.getY(d), draw.getLenW(), draw.getLenH(), true);
			}
		}

	}

}
