package com.editor.sign.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Supplier;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.control.file.FileManager;
import com.control.manager.Behavior;
import com.control.manager.BehaviorController;
import com.control.manager.Session;
import com.control.manager.SignManager;
import com.control.manager.SignManager.SignType;
import com.control.manager.getter.MainSignGetter;
import com.control.manager.getter.MainSignGetter.GetterType;
import com.editor.sign.control.listselect.ListSelectSignIlkBehavior;
import com.editor.sign.control.listselect.ListSelectSignTypeBehavior;
import com.model.Sign;
import com.sun.glass.ui.Size;
import com.tool.BorderFixer;
import com.tool.Direction;
import com.tool.Directions;
import com.view.EditorSign;
import java.awt.Dimension;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.FlowLayout;

public class SignEditor extends JFrame {

	private JPanel contentPane;
	private JList<?> list_signtype;
	private JPanel panel_grid_main;
	private BorderFixer<JPanel> center_grid_fixer = BorderFixer.<JPanel>getFixer();
	private volatile JPanel panel_c1_main;
	private JList<?> list_signilk;
	private static Session session;

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
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				FileManager.writeSignDate();
				super.windowClosing(arg0);
			}
		});
		FileManager.loadSignDate();

		JPanel panel_top = new JPanel();
		contentPane.add(panel_top, BorderLayout.NORTH);

		/*
		 *test init 
		 * SignManager manager =
		 * SignManager.getManager(SignManager.SignType.MainSign);
		 * manager.addSign(MainSignGetter.GetterType.SignS, 2, 2);
		 * manager.addSign(MainSignGetter.GetterType.SignZ, 3, 5);
		 * manager.addSign(MainSignGetter.GetterType.SignT, 5, 2);
		 * 
		 * Sign signS = manager.getSign(MainSignGetter.GetterType.SignS);
		 * signS.setSize(4, 4); Sign signZ =
		 * manager.getSign(MainSignGetter.GetterType.SignZ); signZ.setSize(3, 7); Sign
		 * signT = manager.getSign(MainSignGetter.GetterType.SignT); signT.setSize(7,
		 * 3); 
		 */

		/*
		 * create component
		 */
		createCenter();

		/*
		 * init after components created
		 */
		Session session = getSession();
		session.addAttribute("panel_c1_main", panel_c1_main);
		session.addAttribute("panel_grid_main", panel_grid_main);
		session.addAttribute("center_grid_fixer", center_grid_fixer);

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
				behavior.setParameter("list_signilk", list_signilk);
				behavior.setParameter("list_signtype", list_signtype);

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

		JPanel panel_lbar_center1 = new JPanel();
		panel_lbar_center1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_c1_west.add(panel_lbar_center1);
		panel_lbar_center1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_list_signtype = new JPanel();
		panel_lbar_center1.add(panel_list_signtype);

		list_signtype = new JList();
		panel_list_signtype.add(list_signtype);
		list_signtype.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list_signtype.addMouseListener(new SelectSignTypeMouseAdapter() {
			@Override
			public Sign getSign() {
				return getCurrentSign();
			}
		});

		list_signtype.setFont(new Font("新細明體", Font.PLAIN, 18));
		
		JPanel panel_lbar_center2 = new JPanel();
		panel_c1_west.add(panel_lbar_center2);
														panel_lbar_center2.setLayout(new GridLayout(2, 1, 0, 0));
														
														JPanel panel_addcycle = new JPanel();
														panel_lbar_center2.add(panel_addcycle);
														panel_addcycle.setPreferredSize(new Dimension(20, 10));
														
														JButton btnNewButton_cycle_add = new JButton("+");
														btnNewButton_cycle_add.addMouseListener(new SelectSignTypeMouseAdapter() {
															@Override
															public Sign getSign() {
																Sign sign = null;
																if ((sign = getCurrentSign()) == null) {
																	return null;
																}
																
																sign.insertSignMapAtTheBack();
																return sign;
															}
														});
														panel_addcycle.setLayout(new BoxLayout(panel_addcycle, BoxLayout.X_AXIS));
														
														JButton btnNewButton_cycle_subtract = new JButton("-");
														btnNewButton_cycle_subtract.addMouseListener(new SelectSignTypeMouseAdapter() {
															@Override
															public Sign getSign() {
																Sign sign = null;
																if ((sign = getCurrentSign()) == null) {
																	return null;
																}
																
																sign.removeCurrentSignMap();
																return sign;
															}
														});
														btnNewButton_cycle_subtract.setPreferredSize(new Dimension(60, 23));
														btnNewButton_cycle_subtract.setFont(new Font("新細明體", Font.BOLD, 18));
														btnNewButton_cycle_subtract.setBackground(SystemColor.controlHighlight);
														panel_addcycle.add(btnNewButton_cycle_subtract);
														btnNewButton_cycle_add.setPreferredSize(new Dimension(60, 23));
														btnNewButton_cycle_add.setFont(new Font("新細明體", Font.BOLD, 18));
														btnNewButton_cycle_add.setBackground(SystemColor.controlHighlight);
														panel_addcycle.add(btnNewButton_cycle_add);
														
																JPanel panel_list_signtype_index = new JPanel();
																panel_lbar_center2.add(panel_list_signtype_index);
																panel_list_signtype_index.setLayout(new BoxLayout(panel_list_signtype_index, BoxLayout.X_AXIS));
																
																		JButton btnNewButton_signtype_rotate_backword = new JButton("◄");
																		btnNewButton_signtype_rotate_backword.addMouseListener(new SelectSignTypeMouseAdapter() {
																			@Override
																			public Sign getSign() {
																				Sign sign = null;
																				if ((sign = getCurrentSign()) == null) {
																					return null;
																				}

																				sign.rotateForward();
																				return sign;
																			}
																		});
																		btnNewButton_signtype_rotate_backword.setBackground(SystemColor.controlHighlight);
																		panel_list_signtype_index.add(btnNewButton_signtype_rotate_backword);
																		
																				JButton btnNewButton_signtype_rotate_forword = new JButton("►");
																				btnNewButton_signtype_rotate_forword.addMouseListener(new SelectSignTypeMouseAdapter() {
																					@Override
																					public Sign getSign() {
																						Sign sign = null;
																						if ((sign = getCurrentSign()) == null) {
																							return null;
																						}

																						sign.rotateBackward();
																						return sign;
																					}
																				});
																				btnNewButton_signtype_rotate_forword.setBackground(SystemColor.controlHighlight);
																				panel_list_signtype_index.add(btnNewButton_signtype_rotate_forword);

		JPanel panel_lbar_bottom = new JPanel();
		panel_lbar_bottom.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_c1_west.add(panel_lbar_bottom);
		panel_lbar_bottom.setLayout(new BoxLayout(panel_lbar_bottom, BoxLayout.X_AXIS));

		JPanel panel_col_and_row = new JPanel();
		panel_col_and_row.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_lbar_bottom.add(panel_col_and_row);
		panel_col_and_row.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_spacepanel0 = new JPanel();
		panel_col_and_row.add(panel_spacepanel0);

		JPanel panel_btnpanel1 = new JPanel();
		panel_col_and_row.add(panel_btnpanel1);
		panel_btnpanel1.setLayout(new GridLayout(0, 2, 0, 0));

		JButton btnNewButton_col_subtract = new JButton("-◄");
		btnNewButton_col_subtract.addMouseListener(new SelectSignTypeMouseAdapter() {
			@Override
			public Sign getSign() {
				Sign sign = null;
				if ((sign = getCurrentSign()) == null) {
					return null;
				}

				sign.setSize(sign.getWidth() - 1, sign.getHeight());
				return sign;
			}
		});
		btnNewButton_col_subtract.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_col_subtract.setBackground(SystemColor.controlHighlight);
		panel_btnpanel1.add(btnNewButton_col_subtract);

		JButton btnNewButton_col_add = new JButton("►+");
		btnNewButton_col_add.addMouseListener(new SelectSignTypeMouseAdapter() {
			@Override
			public Sign getSign() {
				Sign sign = null;
				if ((sign = getCurrentSign()) == null) {
					return null;
				}

				sign.setSize(sign.getWidth() + 1, sign.getHeight());
				return sign;
			}
		});
		btnNewButton_col_add.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_col_add.setBackground(SystemColor.controlHighlight);
		panel_btnpanel1.add(btnNewButton_col_add);

		JPanel panel_spacepanel1 = new JPanel();
		panel_col_and_row.add(panel_spacepanel1);

		JPanel panel_btnpanel2 = new JPanel();
		panel_col_and_row.add(panel_btnpanel2);
		panel_btnpanel2.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnNewButton_row_subtract = new JButton("▲-");
		btnNewButton_row_subtract.addMouseListener(new SelectSignTypeMouseAdapter() {
			@Override
			public Sign getSign() {
				Sign sign = null;
				if ((sign = getCurrentSign()) == null) {
					return null;
				}

				sign.setSize(sign.getWidth(), sign.getHeight() - 1);
				return sign;
			}
		});
		btnNewButton_row_subtract.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_row_subtract.setBackground(SystemColor.controlHighlight);
		panel_btnpanel2.add(btnNewButton_row_subtract);

		JButton btnNewButton_row_add = new JButton("▼+");
		btnNewButton_row_add.addMouseListener(new SelectSignTypeMouseAdapter() {
			@Override
			public Sign getSign() {
				Sign sign = null;
				if ((sign = getCurrentSign()) == null) {
					return null;
				}

				sign.setSize(sign.getWidth(), sign.getHeight() + 1);
				return sign;
			}
		});
		btnNewButton_row_add.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_row_add.setBackground(SystemColor.controlHighlight);
		panel_btnpanel2.add(btnNewButton_row_add);

		JPanel panel_spacepanel2 = new JPanel();
		panel_col_and_row.add(panel_spacepanel2);
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

	public Sign getCurrentSign() {
		return SignManager.getManager((SignType) list_signilk.getSelectedValue())
				.getSign((GetterType) list_signtype.getSelectedValue());
	}

	public JFrame getThisFrame() {
		return this;
	}

}

abstract class SelectSignTypeMouseAdapter extends MouseAdapter implements SignSupplier {

	@Override
	public void mousePressed(MouseEvent e) {
		Behavior behavior = new ListSelectSignTypeBehavior();
		behavior.setParameter("sign", getSign());
		BehaviorController.sendBehavior(behavior);

	}
}

interface SignSupplier {
	public Sign getSign();
}
