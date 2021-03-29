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
import com.view.EditorSign;

public class SignEditor extends JFrame {

	private JPanel contentPane;
	private JList list_signtype;
	private JPanel panel_grid;

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
		manager.addSign(MainSignGetter.GetterType.SignS, 1, 2);
		manager.addSign(MainSignGetter.GetterType.SignZ, 3, 5);

		Sign signS = manager.getSign(MainSignGetter.GetterType.SignS);
		signS.setSize(8, 8);
		Sign signZ = manager.getSign(MainSignGetter.GetterType.SignZ);
		signZ.setSize(2, 4);

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

		JPanel panel_c1_main = new JPanel();
		panel_c1.add(panel_c1_main);
		panel_c1_main.setLayout(new BorderLayout(0, 0));

		panel_grid = new JPanel();
		panel_c1_main.add(panel_grid, BorderLayout.CENTER);
		panel_grid.setLayout(new GridLayout(2, 2, 0, 0));

		JPanel panel_c1_west = new JPanel();
		panel_c1.add(panel_c1_west, BorderLayout.WEST);
		panel_c1_west.setLayout(new BoxLayout(panel_c1_west, BoxLayout.Y_AXIS));

		JPanel panel_lbar_top = new JPanel();
		panel_c1_west.add(panel_lbar_top);

		JList<?> list_signilk = new JList();
		list_signilk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Behavior behavior = new ListSelectSignIlkBehavior();
				behavior.setSession("list_signilk", list_signilk);
				behavior.setSession("list_signtype", list_signtype);

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
		panel_c1_west.add(panel_lbar_center);

		list_signtype = new JList();
		list_signtype.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Behavior behavior = new ListSelectSignTypeBehavior();
				behavior.setSession("list_signilk", list_signilk);
				behavior.setSession("list_signtype", list_signtype);
				behavior.setSession("panel_grid", panel_grid);

				BehaviorController.sendBehavior(behavior);
			}
		});
		list_signtype.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_lbar_center.add(list_signtype);

		JPanel panel_lbar_bottom = new JPanel();
		panel_c1_west.add(panel_lbar_bottom);
	}

}
