package com.editor.sign.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.editor.sign.control.behavior.listselect.ListSelectSignIlkBehavior;
import com.editor.sign.control.behavior.listselect.ListSelectSignTypeBehavior;
import com.main.control.exception.FileErrorException;
import com.main.control.file.FileManager;
import com.main.control.manager.SignManager;
import com.main.control.manager.SignManager.SignType;
import com.main.model.MainSignGetter;
import com.main.model.Sign;
import com.main.model.MainSignGetter.GetterType;
import com.sun.glass.ui.Size;
import com.tool.Session;
import com.tool.behavior.Behavior;
import com.tool.behavior.BehaviorController;
import com.tool.behavior.BorderFixer;
import com.tool.direction.Direction;
import com.tool.direction.Directions;

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
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignEditor extends JFrame {

	private JPanel contentPane;
	private JList<?> list_signtype;//放置Sign Cube 的 BorderLayout的父元件
	private JPanel panel_grid_main;//放置Sign Cube 的 BorderLayout中間的元件
	private BorderFixer<JPanel> center_grid_fixer = BorderFixer.<JPanel>getFixer();
	private volatile JPanel panel_c1_main;
	private JList<?> list_signilk;
	private static Session session;
	private JLabel lblNewLabel_pointer;

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

		try {
			FileManager.loadSignDate();
		} catch (FileErrorException ex) {
			SignManager.initialize();
		}

		JPanel panel_top = new JPanel();
		contentPane.add(panel_top, BorderLayout.NORTH);


		createCenter();

		/*
		 * init: this init area after components created, e.g.,createCenter()
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

			@Override
			public Component getPointerComponent() {
				return lblNewLabel_pointer;
			}

		});

		list_signtype.setFont(new Font("新細明體", Font.PLAIN, 18));

		JPanel panel_lbar_center2 = new JPanel();
		panel_c1_west.add(panel_lbar_center2);
		panel_lbar_center2.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panel_list_signtype_index = new JPanel();
		panel_list_signtype_index.setPreferredSize(new Dimension(20, 20));
		panel_lbar_center2.add(panel_list_signtype_index);

		JButton btnNewButton_signtype_rotate_backword = new JButton("◄");
		btnNewButton_signtype_rotate_backword
				.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_signtype_rotate_backword.addMouseListener(new SelectSignTypeMouseAdapter() {
			@Override
			public Sign getSign() {
				Sign sign = null;
				if ((sign = getCurrentSign()) == null) {
					return null;
				}

				sign.rotateLeft();
				return sign;
			}

			@Override
			public Component getPointerComponent() {
				return lblNewLabel_pointer;
			}

		});
		panel_list_signtype_index.setLayout(new GridLayout(0, 2, 0, 0));
		btnNewButton_signtype_rotate_backword.setBackground(SystemColor.controlHighlight);
		panel_list_signtype_index.add(btnNewButton_signtype_rotate_backword);

		JButton btnNewButton_signtype_rotate_forword = new JButton("►");
		btnNewButton_signtype_rotate_forword.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_signtype_rotate_forword.addMouseListener(new SelectSignTypeMouseAdapter() {
			@Override
			public Sign getSign() {
				Sign sign = null;
				if ((sign = getCurrentSign()) == null) {
					return null;
				}

				sign.rotateRight();
				return sign;
			}

			@Override
			public Component getPointerComponent() {
				return lblNewLabel_pointer;
			}

		});
		btnNewButton_signtype_rotate_forword.setBackground(SystemColor.controlHighlight);
		panel_list_signtype_index.add(btnNewButton_signtype_rotate_forword);

		JPanel panel_add_and_subtract_cycle = new JPanel();
		panel_add_and_subtract_cycle.setPreferredSize(new Dimension(20, 20));
		panel_lbar_center2.add(panel_add_and_subtract_cycle);

		JButton btnNewButton_cycle_add = new JButton("+");
		btnNewButton_cycle_add.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
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

			@Override
			public Component getPointerComponent() {
				return lblNewLabel_pointer;
			}

		});
		panel_add_and_subtract_cycle.setLayout(new BoxLayout(panel_add_and_subtract_cycle, BoxLayout.X_AXIS));

		JButton btnNewButton_cycle_subtract = new JButton("-");
		btnNewButton_cycle_subtract.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
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

			@Override
			public Component getPointerComponent() {
				return lblNewLabel_pointer;
			}

		});
		btnNewButton_cycle_subtract.setPreferredSize(new Dimension(60, 23));
		btnNewButton_cycle_subtract.setFont(new Font("新細明體", Font.BOLD, 18));
		btnNewButton_cycle_subtract.setBackground(SystemColor.controlHighlight);
		panel_add_and_subtract_cycle.add(btnNewButton_cycle_subtract);
		btnNewButton_cycle_add.setPreferredSize(new Dimension(60, 23));
		btnNewButton_cycle_add.setFont(new Font("新細明體", Font.BOLD, 18));
		btnNewButton_cycle_add.setBackground(SystemColor.controlHighlight);
		panel_add_and_subtract_cycle.add(btnNewButton_cycle_add);

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

		JPanel panel_c1_east = new JPanel();
		panel_c1.add(panel_c1_east, BorderLayout.EAST);
		panel_c1_east.setLayout(new BoxLayout(panel_c1_east, BoxLayout.Y_AXIS));

		JPanel panel_rbar_top = new JPanel();
		panel_c1_east.add(panel_rbar_top);

		lblNewLabel_pointer = new JLabel("0 / 0");
		panel_rbar_top.add(lblNewLabel_pointer);

		JPanel panel_rbar_center = new JPanel();
		panel_c1_east.add(panel_rbar_center);
		panel_rbar_center.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnNewButton_reset_pointer = new JButton("Reset ID");
		btnNewButton_reset_pointer.addMouseListener(new SelectSignTypeMouseAdapter() {
			@Override
			public Sign getSign() {
				Sign sign = null;
				if ((sign = getCurrentSign()) == null) {
					return null;
				}

				sign.resetPointerToCurrentCycleElement();
				return sign;
			}

			@Override
			public Component getPointerComponent() {
				return lblNewLabel_pointer;
			}

		});
		btnNewButton_reset_pointer.setFont(new Font("新細明體", Font.PLAIN, 14));
		btnNewButton_reset_pointer.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_reset_pointer.setBackground(SystemColor.controlHighlight);
		panel_rbar_center.add(btnNewButton_reset_pointer);

		JPanel panel_rbar_bottom = new JPanel();
		panel_rbar_bottom.setPreferredSize(new Dimension(10, 250));
		panel_c1_east.add(panel_rbar_bottom);
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
		behavior.setParameter("lblNewLabel_pointer", getPointerComponent());

		BehaviorController.sendBehavior(behavior);

	}

	public Component getPointerComponent() {
		return null;
	}

}

interface SignSupplier {
	public Sign getSign();
}
