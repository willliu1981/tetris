package com.test.mainview;

import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.main.control.manager.SignManager;
import com.main.model.MainSignGetter;
import com.main.model.Sign;
import com.test.mainview.behavior.InitViewBehavior;
import com.tool.behavior.BehaviorController;
import com.tool.direction.Direction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestPanel extends JPanel {
	private TestPanel thisPanel;

	/**
	 * Create the panel.
	 */
	public TestPanel() {
		this.thisPanel = this;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Sign sign = SignManager.getManager(SignManager.SignType.MAINSIGN)
						.getSign(MainSignGetter.GetterType.SIGNJ);
				sign.rotateRight();
				thisPanel.repaint();
			}
		});
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		
		
		Insets inset = this.getInsets();

		int w = this.getWidth() - (inset.left + inset.right);
		int h = this.getHeight() - (inset.top + inset.bottom);

		int len = w/15;

		int arrw = w / len;
		int arrh = h / len;

		int midx = arrw / 2;
		int midy = arrh / 2;

		g.draw3DRect(10, 10, w - 20, h - 20, true);

		Sign sign = SignManager.getManager(SignManager.SignType.MAINSIGN).getSign(MainSignGetter.GetterType.SIGNJ);

		if (sign != null) {
			sign.setPoint(midx, midy);

			for (Direction d : sign.getCubeMap().keySet()) {
				g.fill3DRect((d.getX() + sign.getLTX()) * len, (d.getY() + sign.getLTY()) * len, len, len, true);
			}
		}

	}

}
