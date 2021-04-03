package com.main.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Optional;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;

import com.editor.sign.control.behavior.editorsign.EditorSignShowBehavior;
import com.editor.sign.control.behavior.listselect.ListSelectSignTypeBehavior;
import com.editor.sign.view.SignEditor;
import com.main.control.exception.TNullException;
import com.main.control.manager.Behavior;
import com.main.control.manager.BehaviorController;
import com.main.control.manager.Session;
import com.model.Sign;
import com.tool.BorderFixer;
import com.tool.Direction;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditorSign extends JPanel {
	private JPanel panel;
	private Optional<Sign> sign = Optional.empty();
	private Optional<Direction> direction = Optional.empty();
	private volatile boolean isDBClick = false;// 用於雙擊滑鼠忽略單擊事件

	/**
	 * Create the panel.
	 */
	public EditorSign(Sign sign) {
		this();
		this.sign = Optional.of(sign);
	}

	private EditorSign() {
		setLayout(new BorderLayout(0, 0));
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg) {
				if (arg.getButton() == arg.BUTTON1) {
					if (arg.getClickCount() == 1) {
						new Thread() {
							@Override
							public void run() {
								try {
									Thread.sleep((Integer) Toolkit.getDefaultToolkit()
											.getDesktopProperty("awt.multiClickInterval") / 2);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								if (!isDBClick) {
									try {
										if (getSign().containCube(getDirection().getX(), getDirection().getY())) {
											getSign().removeCube(getDirection().getX(), getDirection().getY());
										} else {
											getSign().addCube(getDirection().getX(), getDirection().getY());
										}
									} catch (TNullException ex) {
										ex.printStackTrace();
									}

									Behavior behavior = new EditorSignShowBehavior();
									behavior.setParameter("editorsign", getThis());
									behavior.setParameter("sign", getSign());
									BehaviorController.sendBehavior(behavior);
								} else {
									isDBClick = false;
								}
							}
						}.start();
					} else {
						isDBClick = true;
						Behavior behavior = new ListSelectSignTypeBehavior();
						behavior.setParameter("sign", getSign());
						getSign().setPivot(getDirection().getX(), getDirection().getY());

						BehaviorController.sendBehavior(behavior);
					}
				}

			}
		});
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

	}

	/*
	 * get and set
	 */

	public void setDirection(int x, int y) {
		this.direction = Optional.of(new Direction(x, y));
	}

	public Direction getDirection() {
		return this.direction.orElseThrow(() -> new TNullException("direction is null"));
	}

	public Sign getSign() {
		return this.sign.orElseThrow(() -> new TNullException("sign is null"));
	}

	public JPanel getMainPanel() {
		return this.panel;
	}

	public EditorSign getThis() {
		return this;
	}

}
