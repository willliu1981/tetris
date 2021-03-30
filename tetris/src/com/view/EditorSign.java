package com.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Optional;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;

import com.control.exception.TNullException;
import com.editor.sign.control.Behavior;
import com.editor.sign.control.BehaviorController;
import com.editor.sign.control.editorsign.EditorSignShowBehavior;
import com.model.Sign;
import com.tool.Direction;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditorSign extends JPanel {
	private JPanel panel;
	private Optional<Sign> sign = Optional.empty();
	private Optional<Direction> direction = Optional.empty();

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
			public void mousePressed(MouseEvent arg0) {
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
				behavior.setRequest("editorsign", getThis());
				behavior.setRequest("sign", getSign());
				BehaviorController.sendBehavior(behavior);
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
