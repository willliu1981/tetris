package com.editor.sign;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import com.controller.manager.SignManager;
import com.controller.manager.keyvalue.MainSignGetter;
import com.model.Sign;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignEditor extends JFrame {

	private JPanel contentPane;
	private JList list_signtype;

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

		SignManager manager = SignManager.getManager(SignManager.SignType.MainSign);
		manager.addSign(MainSignGetter.GetterType.SignS, 1, 2);
		manager.addSign(MainSignGetter.GetterType.SignZ, 3, 5);

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
		panel_c1_main.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_c1_west = new JPanel();
		panel_c1.add(panel_c1_west, BorderLayout.WEST);
		panel_c1_west.setLayout(new BoxLayout(panel_c1_west, BoxLayout.Y_AXIS));

		JPanel panel_lbar_top = new JPanel();
		panel_c1_west.add(panel_lbar_top);

		JList<?> list_signilk = new JList();
		list_signilk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				list_signtype.setModel(new AbstractListModel() {
					Enum[] signs;
					{
						SignManager manager = SignManager.getManager(SignManager.SignType.MainSign);
						signs = new Enum[manager.getSignGetter().getSignMap().size()];
						manager.getSignGetter().getSignMap().keySet().toArray(signs);
					}

					@Override
					public Enum getElementAt(int index) {
						return signs[index];
					}

					@Override
					public int getSize() {
						return signs.length;
					}

				});
				if(list_signtype.getModel().getSize()>0) {
					list_signtype.setSelectedIndex(0);
				}else {
					list_signtype.setSelectedIndex(-1);
				}
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
		if(list_signilk.getModel().getSize()>0) {
			list_signilk.setSelectedIndex(0);
		}else {
			list_signilk.setSelectedIndex(-1);
		}
		panel_lbar_top.add(list_signilk);

		JPanel panel_lbar_center = new JPanel();
		panel_c1_west.add(panel_lbar_center);

		list_signtype = new JList();
		list_signtype.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_lbar_center.add(list_signtype);

		JPanel panel_lbar_bottom = new JPanel();
		panel_c1_west.add(panel_lbar_bottom);
	}

}
