package com.main.view;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.main.control.exception.LogicErrorException;
import com.main.control.manager.AppManager;
import com.main.model.Sign;
import com.tool.direction.Direction;
import com.tool.draw.SignDrawer;
import com.tool.draw.XSignDrawer;

public class NextPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public NextPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		try {

			/*
			 * 繪製sign
			 */
			SignDrawer drawer = new SignDrawer(this, new Direction(0,0,5,5));
			Sign sign = AppManager.getNextSign();
			drawer.setSign(sign);
			if (sign != null) {
				for (Direction d : sign.getCubeMap().keySet()) {
					g.fill3DRect(drawer.getX(d), drawer.getY(d), drawer.getLenW(), drawer.getLenH(), true);
				}
			}
		} catch (LogicErrorException e) {
			System.out.println(e.getMessage());
		}

	}

}
