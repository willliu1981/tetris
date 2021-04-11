package com.main.view;

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
						.getSign(MainSignGetter.GetterType.SIGNJ);
				sign.rotateRight();
				MainPanel.this.repaint();

			}
		});

	

		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Insets inset = this.getInsets();

		// 實際內容尺寸
		int w = this.getWidth() - (inset.left + inset.right);
		int h = this.getHeight() - (inset.top + inset.bottom);

		// cube 的寬度
		int len = w / 12;

		// cube 在 x、y 各方向的數量
		int arrw = w / len;
		int arrh = h / len;

		// test:放置在中間位置
		int midx = arrw / 2;
		int midy = arrh / 2;

		Sign sign = SignManager.getManager(SignManager.SignType.MAINSIGN).getSign(MainSignGetter.GetterType.SIGNJ);

		if (sign != null) {
			//sign.setPoint(midx, midy);

			for (Direction d : sign.getCubeMap().keySet()) {

				g.fill3DRect((d.getX() + sign.getLTX()) * len, (d.getY() + sign.getLTY()) * len, len, len, true);
			}
		}

	}

}
