package com.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Optional;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;

import com.tool.Direction;

import javax.swing.border.BevelBorder;
import java.awt.Color;

public class EditorSign extends JPanel {
	private JPanel panel;
	private Optional<Direction> direction = Optional.empty();

	/**
	 * Create the panel.
	 */
	public EditorSign() {
		this("Label");
	}

	public EditorSign(Direction direction) {
		this();
		this.direction = Optional.of(direction);
	}

	public EditorSign(String name) {
		setLayout(new BorderLayout(0, 0));
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.CENTER);

		((JLabel) panel.getComponent(0)).setText(name);

	}

	/*
	 * get and set
	 */

	public void setDirection(int x, int y) {
		this.direction = Optional.of(new Direction(x, y));
	}

	public Direction getDirection() {
		this.direction = Optional.of(this.direction.orElseGet(Direction::new));
		return this.direction.get();
	}
	
	public JPanel getMainPanel() {
		return this.panel;
	}

}
