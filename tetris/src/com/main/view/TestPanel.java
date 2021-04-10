package com.main.view;

import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.main.control.manager.SignManager;
import com.main.model.MainSignGetter;
import com.main.model.Sign;
import com.tool.direction.Direction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestPanel extends JPanel {
	private TestPanel thisPanel;

	/**
	 * Create the panel.
	 */
	public TestPanel() {
		this.thisPanel=this;
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
		System.out.println("xxx");
		Insets inset = this.getInsets();

		int w = this.getWidth() - (inset.left + inset.right);
		int h = this.getHeight() - (inset.top + inset.bottom);

		int len = 50;

		int arrw = w / len;
		int arrh = h / len;

		int midw = arrw / 2;
		int midh = arrh / 2;

		Sign sign = SignManager.getManager(SignManager.SignType.MAINSIGN).getSign(MainSignGetter.GetterType.SIGNJ);
		
		if(sign!=null) {
			sign.setPoint(midw, midh);
			
			for (Direction d : sign.getCubeMap().keySet()) {
				g.fill3DRect((d.getX() + sign.getLTX()) * len, (d.getY() + sign.getLTY()) * len, len, len, true);
			}
		}

	}

}
