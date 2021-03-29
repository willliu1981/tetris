package com.editor.sign.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.control.manager.SignManager;
import com.control.manager.SignManager.SignType;
import com.control.manager.keyvalue.MainSignGetter;
import com.control.manager.keyvalue.MainSignGetter.GetterType;
import com.editor.sign.control.Behavior;
import com.editor.sign.control.BehaviorController;
import com.editor.sign.control.ListSelectSignIlkBehavior;
import com.editor.sign.control.ListSelectSignTypeBehavior;
import com.model.Sign;
import com.sun.glass.ui.Size;
import com.tool.BorderFixer;
import com.tool.Direction;
import com.tool.Directions;
import com.view.EditorSign;
import java.awt.Dimension;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class SignEditor extends JFrame {

	private JPanel contentPane;
	private JList<?> list_signtype;
	private JPanel panel_grid_main;
	private BorderFixer<JPanel> center_grid_fixer = BorderFixer.<JPanel>getFixer();
	private volatile JPanel panel_c1_main;
	private JList<?> list_signilk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignEditor frame = new SignEditor();
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
	public SignEditor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 648);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel_top = new JPanel();
		contentPane.add(panel_top, BorderLayout.NORTH);

		// test init
		SignManager manager = SignManager.getManager(SignManager.SignType.MainSign);
		manager.addSign(MainSignGetter.GetterType.SignS, 2, 2);
		manager.addSign(MainSignGetter.GetterType.SignZ, 3, 5);
		manager.addSign(MainSignGetter.GetterType.SignT, 5, 2);

		Sign signS = manager.getSign(MainSignGetter.GetterType.SignS);
		signS.setSize(4, 4);
		Sign signZ = manager.getSign(MainSignGetter.GetterType.SignZ);
		signZ.setSize(3, 7);
		Sign signT = manager.getSign(MainSignGetter.GetterType.SignT);
		signT.setSize(7, 3);

		/*
		 * init
		 */
		createCenter();
	}

	private void createCenter() {
		JPanel panel_center = new JPanel();
		contentPane.add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));

		JPanel panel_c1 = new JPanel();
		panel_center.add(panel_c1, BorderLayout.CENTER);
		panel_c1.setLayout(new BorderLayout(0, 0));

		panel_c1_main = new JPanel();
		panel_c1_main.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_c1.add(panel_c1_main);
		panel_c1_main.setLayout(new BorderLayout(0, 0));

		panel_grid_main = new JPanel();
		panel_c1_main.add(panel_grid_main, BorderLayout.CENTER);
		panel_grid_main.setLayout(new GridLayout(2, 2, 0, 0));

		JPanel panel_grid_fix_left = new JPanel();
		panel_c1_main.add(panel_grid_fix_left, BorderLayout.WEST);
		center_grid_fixer.add(BorderFixer.Left, panel_grid_fix_left);

		JPanel panel_grid_fix_right = new JPanel();
		panel_c1_main.add(panel_grid_fix_right, BorderLayout.EAST);
		center_grid_fixer.add(BorderFixer.Right, panel_grid_fix_right);

		JPanel panel_grid_fix_top = new JPanel();
		panel_c1_main.add(panel_grid_fix_top, BorderLayout.NORTH);
		center_grid_fixer.add(BorderFixer.Top, panel_grid_fix_top);

		JPanel panel_grid_fix_bottom = new JPanel();
		panel_c1_main.add(panel_grid_fix_bottom, BorderLayout.SOUTH);
		center_grid_fixer.add(BorderFixer.Bottom, panel_grid_fix_bottom);

		JPanel panel_c1_west = new JPanel();
		panel_c1_west.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_c1.add(panel_c1_west, BorderLayout.WEST);
		panel_c1_west.setLayout(new BoxLayout(panel_c1_west, BoxLayout.Y_AXIS));

		JPanel panel_lbar_top = new JPanel();
		panel_lbar_top.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_c1_west.add(panel_lbar_top);

		list_signilk = new JList();
		list_signilk.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list_signilk.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Behavior behavior = new ListSelectSignIlkBehavior();
				behavior.setRequest("list_signilk", list_signilk);
				behavior.setRequest("list_signtype", list_signtype);

				BehaviorController.sendBehavior(behavior);
			}
		});
		list_signilk.setFont(new Font("新細明體", Font.PLAIN, 18));
		list_signilk.setModel(new AbstractListModel() {
			/**
			 * 
			 */
			Enum[] signs;
			{
				signs = new Enum[SignManager.getSignGetterMap().size()];
				SignManager.getSignGetterMap().keySet().toArray(signs);
			}

			public int getSize() {
				return signs.length;
			}

			public Enum getElementAt(int index) {
				return signs[index];
			}
		});
		if (list_signilk.getModel().getSize() > 0) {
			list_signilk.setSelectedIndex(0);
		} else {
			list_signilk.setSelectedIndex(-1);
		}
		panel_lbar_top.add(list_signilk);

		JPanel panel_lbar_center = new JPanel();
		panel_lbar_center.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_c1_west.add(panel_lbar_center);

		list_signtype = new JList();
		list_signtype.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list_signtype.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Behavior behavior = new ListSelectSignTypeBehavior();
				behavior.setRequest("list_signilk", list_signilk);
				behavior.setRequest("list_signtype", list_signtype);
				behavior.setRequest("panel_grid_main", panel_grid_main);
				behavior.setRequest("center_grid_fixer", center_grid_fixer);

				center_grid_fixer.reset();
				new Thread() {
					@Override
					public void run() {
						BehaviorController.sendBehavior(behavior);
					}
				}.start();
			}
		});
		list_signtype.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_lbar_center.add(list_signtype);

		JPanel panel_lbar_bottom = new JPanel();
		panel_lbar_bottom.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_c1_west.add(panel_lbar_bottom);
		panel_lbar_bottom.setLayout(new BoxLayout(panel_lbar_bottom, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				center_grid_fixer.reset2();

				panel_c1_main.revalidate();

				panel_c1_main.setSize(panel_c1_main.getSize().width + 1, panel_c1_main.getSize().height + 1);
				System.out.println("reset...");
			}
		});
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_lbar_bottom.add(panel);
	}

}
