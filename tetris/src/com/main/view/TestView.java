package com.main.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.main.control.file.FileManager;
import com.tool.Session;
import javax.swing.JButton;

public class TestView extends JFrame {

	private JPanel contentPane;
	private static Session session;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestView frame = new TestView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestView() {
		/*
		 * main panel
		 */

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		FileManager.loadSignDate();

		createComponent();

		/*
		 * init: this init area after components created, e.g.,createCenter()
		 */
		Session session = getSession();
		session.addAttribute("panel_c1_main", null);
		session.addAttribute("panel_grid_main", null);
		session.addAttribute("center_grid_fixer", null);

	}

	private void createComponent() {
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		TestPanel testPanel = new TestPanel();
		panel.add(testPanel, BorderLayout.CENTER);

	}

	public static Session getSession() {
		if (session == null) {
			session = new Session();
		}
		return session;
	}

}
