package com.main.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.main.control.game.input.Input;
import com.main.control.manager.AppManager;
import com.main.model.Sign;
import com.tool.Session;
import com.tool.behavior.BehaviorController;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MainView extends JFrame {

	private static AppManager appManager = AppManager.getStart();
	private JPanel contentPane;
	private static Session session;
	private static Dimension main_panel_size = new Dimension(AppManager.getMainPanelSize());

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

					/*
					 * test point
					 */
//					Sign sign = AppManager.getSign(AppManager.signType.MAINSIGN, AppManager.getterMainSignType.SIGNJ);
//
//					sign.setPoint(6, 9);
					
					AppManager.initialize();

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
				Input input = new Input();
				input.setParameter("state",Input.KeyState.keyPressed);
				input.setParameter("event", e);
				BehaviorController.sendBehavior(input);

			}

			@Override
			public void keyReleased(KeyEvent e) {
				Input input = new Input();
				input.setParameter("state", Input.KeyState.keyReleased);
				input.setParameter("event", e);
				BehaviorController.sendBehavior(input);
			}
			
			
		});

		createComponent();

	}

	private void createComponent() {
		JPanel panel_lbar = new JPanel();
		panel_lbar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_lbar.setPreferredSize(new Dimension(200, 10));
		contentPane.add(panel_lbar, BorderLayout.WEST);
		panel_lbar.setLayout(new BoxLayout(panel_lbar, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel_lbar.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Start");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				AppManager.start();
			}
		});
		mntmNewMenuItem.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 20));
		panel.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Restart");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				AppManager.restart();
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 20));
		panel.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Pause");
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				AppManager.pause();
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 20));
		panel.add(mntmNewMenuItem_2);
		
		JPanel panel_7 = new JPanel();
		panel_lbar.add(panel_7);

		JPanel panel_rbar = new JPanel();
		panel_rbar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_rbar.setPreferredSize(new Dimension(200, 10));
		contentPane.add(panel_rbar, BorderLayout.EAST);
		panel_rbar.setLayout(new BoxLayout(panel_rbar, BoxLayout.Y_AXIS));
		
		JPanel panel_next = new JPanel();
		panel_next.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_rbar.add(panel_next);
		panel_next.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_next.add(panel_3, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		panel_next.add(panel_4, BorderLayout.SOUTH);
		
		JPanel panel_5 = new JPanel();
		panel_next.add(panel_5, BorderLayout.WEST);
		
		JPanel panel_6 = new JPanel();
		panel_next.add(panel_6, BorderLayout.EAST);
		
		NextPanel nextPanel = new NextPanel();
		panel_next.add(nextPanel, BorderLayout.CENTER);
		getSession().addAttribute("nextPanel", nextPanel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 200));
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_rbar.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 200));
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_rbar.add(panel_2);

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

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
