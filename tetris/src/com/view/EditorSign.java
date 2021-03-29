package com.view;


import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class EditorSign extends JPanel {
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public EditorSign() {
		this("Label");
	}

	public EditorSign(String name) {
		setLayout(new BorderLayout(0, 0));

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

}
