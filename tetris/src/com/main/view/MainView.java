package com.main.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.main.control.file.FileManager;
import com.main.control.manager.SignManager;
import com.main.model.MainSignGetter;
import com.main.model.Sign;
import com.test.mainview.behavior.InitViewBehavior;
import com.tool.Session;
import com.tool.behavior.BehaviorController;
import com.tool.behavior.BorderFixer;
import java.awt.Dimension;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

public class MainView extends JFrame {

	private JPanel contentPane;
	private static Session session;
	private static Dimension main_panel_size = new Dimension(400, 600);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
					frame.pack();

					Sign sign = SignManager.getManager(SignManager.SignType.MAINSIGN)
							.getSign(MainSignGetter.GetterType.SIGNJ);

					/*
					 * test point
					 */
					sign.setPoint(6, 9);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		/*
		 * main panel
		 */

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				Sign sign = SignManager.getManager(SignManager.SignType.MAINSIGN)
						.getSign(MainSignGetter.GetterType.SIGNJ);

				switch (e.getKeyCode()) {
				case 37:// left
					sign.setPoint(sign.getX() - 1, sign.getY());
					break;
				case 38:// up
					sign.rotateRight();
					break;
				case 39:// right
					sign.setPoint(sign.getX() + 1, sign.getY());
					break;
				case 40:// down
					sign.setPoint(sign.getX(), sign.getY() + 1);
					break;

				default:
					break;
				}
				// System.out.println("keypress ** "+sign);

				((JPanel) getSession().getAttribute("main_panel")).repaint();
			}
		});

		FileManager.loadSignDate();

		createComponent();

	}

	private void createComponent() {
		JPanel panel_lbar = new JPanel();
		panel_lbar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_lbar.setPreferredSize(new Dimension(200, 10));
		contentPane.add(panel_lbar, BorderLayout.WEST);

		JPanel panel_rbar = new JPanel();
		panel_rbar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_rbar.setPreferredSize(new Dimension(200, 10));
		contentPane.add(panel_rbar, BorderLayout.EAST);

		JPanel panel_center = new JPanel();
		panel_center.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));

		MainPanel main_panel = new MainPanel();
		panel_center.add(main_panel, BorderLayout.CENTER);
		main_panel.setPreferredSize(main_panel_size);

		getSession().addAttribute("main_panel", main_panel);
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
