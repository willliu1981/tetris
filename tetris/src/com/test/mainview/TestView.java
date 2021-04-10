package com.test.mainview;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.main.control.file.FileManager;
import com.test.mainview.behavior.InitViewBehavior;
import com.tool.Session;
import com.tool.behavior.BehaviorController;
import com.tool.behavior.BorderFixer;

import javax.swing.JButton;

public class TestView extends JFrame {

	private JPanel contentPane;
	private static Session session;
	private BorderFixer<JPanel> center_canvas_fixer = BorderFixer.<JPanel>getFixer();
	private JPanel panel;
	private TestPanel testPanel;

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
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				BehaviorController.sendBehavior(new InitViewBehavior());
			}
		});

		FileManager.loadSignDate();

		createComponent();

		/*
		 * init: this init area after components created, e.g.,createCenter()
		 */
		Session session = getSession();
		session.addAttribute("canvas_box_panel", panel);
		session.addAttribute("canvas_main_panel", testPanel);
		session.addAttribute("center_canvas_fixer", center_canvas_fixer);

	}

	private void createComponent() {
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		testPanel = new TestPanel();
		panel.add(testPanel, BorderLayout.CENTER);

		JPanel panel_n = new JPanel();
		panel.add(panel_n, BorderLayout.NORTH);
		center_canvas_fixer.add(BorderFixer.NORTH, panel_n);

		JPanel panel_s = new JPanel();
		panel.add(panel_s, BorderLayout.SOUTH);
		center_canvas_fixer.add(BorderFixer.SOUTH, panel_s);

		JPanel panel_w = new JPanel();
		panel.add(panel_w, BorderLayout.WEST);
		center_canvas_fixer.add(BorderFixer.WEST, panel_w);

		JPanel panel_e = new JPanel();
		panel.add(panel_e, BorderLayout.EAST);
		center_canvas_fixer.add(BorderFixer.EAST, panel_e);

	}

	/*
	 * get and set
	 */

	public static Session getSession() {
		if (session == null) {
			session = new Session();
		}
		return session;
	}

}
