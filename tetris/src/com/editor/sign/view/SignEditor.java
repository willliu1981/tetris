package com.editor.sign.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.editor.sign.control.behavior.listselect.ListSelectSignIlkBehavior;
import com.editor.sign.control.behavior.listselect.ListSelectSignTypeBehavior;
import com.editor.sign.control.behavior.listselect.ShowSignIlkBehavior;
import com.main.control.exception.FileErrorException;
import com.main.control.file.FileManager;
import com.main.control.manager.AppManager;
import com.main.control.manager.SignManager;
import com.main.control.manager.SignManager.SignType;
import com.main.model.Sign;
import com.tool.Session;
import com.tool.behavior.Behavior;
import com.tool.behavior.BehaviorController;
import com.tool.borderlayout.BorderFixer;

public class SignEditor extends JFrame {

	private AppManager appManager = AppManager.getSingletonManager();
	private JPanel contentPane;
	private JList<?> list_signtype;// 放置Sign Cube 的 BorderLayout的父元件
	private JPanel panel_grid_main;// 放置Sign Cube 的 BorderLayout中間的元件
	private BorderFixer<JPanel> center_grid_fixer = BorderFixer.<JPanel>getFixer();
	private volatile JPanel panel_c1_main;
	private JList<?> list_signilk;
	private static Session session;
	private Behavior.Request request = new Behavior.Request();

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

		createComponent();

		/*
		 * init: this init area after components created, e.g.,createCenter()
		 */
		Session session = getSession();
		session.addAttribute("panel_c1_main", panel_c1_main);
		session.addAttribute("panel_grid_main", panel_grid_main);
		session.addAttribute("center_grid_fixer", center_grid_fixer);

	}

	private void createComponent() {
		JPanel panel_top = new JPanel();
		contentPane.add(panel_top, BorderLayout.NORTH);

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
		center_grid_fixer.add(BorderFixer.WEST, panel_grid_fix_left);

		JPanel panel_grid_fix_right = new JPanel();
		panel_c1_main.add(panel_grid_fix_right, BorderLayout.EAST);
		center_grid_fixer.add(BorderFixer.EAST, panel_grid_fix_right);

		JPanel panel_grid_fix_top = new JPanel();
		panel_c1_main.add(panel_grid_fix_top, BorderLayout.NORTH);
		center_grid_fixer.add(BorderFixer.NORTH, panel_grid_fix_top);

		JPanel panel_grid_fix_bottom = new JPanel();
		panel_c1_main.add(panel_grid_fix_bottom, BorderLayout.SOUTH);
		center_grid_fixer.add(BorderFixer.SOUTH, panel_grid_fix_bottom);

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
				/*
				 * 於Behavior do setModel
				 */
				Behavior behavior = new ListSelectSignIlkBehavior();
				behavior.setParameter("list_signilk", list_signilk);
				behavior.setParameter("list_signtype", list_signtype);
				behavior.setParameter("panel_grid_main", panel_grid_main);

				BehaviorController.sendBehavior(behavior);
			}
		});
		list_signilk.setFont(new Font("新細明體", Font.PLAIN, 18));
		Behavior behaviorSignilk = new ShowSignIlkBehavior();
		behaviorSignilk.setParameter("list_signilk", list_signilk);
		BehaviorController.sendBehavior(behaviorSignilk);
		
		if (list_signilk.getModel().getSize() > 0) {
			list_signilk.setSelectedIndex(0);
		} else {
			list_signilk.setSelectedIndex(-1);
		}
		panel_lbar_top.add(list_signilk);

		/*
		 * When the sign dates need to be update
		 */
		if (AppManager.isSignHasNewData()) {
			JButton btnNewButton_update = new JButton("Update");
			btnNewButton_update.setBackground(SystemColor.controlHighlight);
			panel_lbar_top.add(btnNewButton_update);
			btnNewButton_update.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					AppManager.updateSignData();

					Behavior behavior = new ShowSignIlkBehavior();
					behavior.setParameter("list_signilk", list_signilk);
					BehaviorController.sendBehavior(behavior);
					
					btnNewButton_update.setVisible(false);
					list_signtype.setModel(new AbstractListModel() {
						@Override
						public Object getElementAt(int arg0) {
							return null;
						}

						@Override
						public int getSize() {
							return 0;
						}});
					panel_grid_main.removeAll();
				}
			});
		}

		JPanel panel_lbar_center1 = new JPanel();
		panel_lbar_center1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_c1_west.add(panel_lbar_center1);
		panel_lbar_center1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_list_signtype = new JPanel();
		panel_lbar_center1.add(panel_list_signtype);

		list_signtype = new JList();
		panel_list_signtype.add(list_signtype);
		list_signtype.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list_signtype.addMouseListener(new SelectSignTypeMouseAdapter(this.request) {
			@Override
			public Sign getSign() {
				return getCurrentSign();
			}

		});

		list_signtype.setFont(new Font("新細明體", Font.PLAIN, 18));

		JPanel panel_lbar_center2 = new JPanel();
		panel_c1_west.add(panel_lbar_center2);
		panel_lbar_center2.setLayout(new GridLayout(3, 1, 0, 0));

		JPanel panel_list_signtype_index = new JPanel();
		panel_list_signtype_index.setPreferredSize(new Dimension(20, 20));
		panel_lbar_center2.add(panel_list_signtype_index);

		JButton btnNewButton_signtype_rotate_backword = new JButton("◄");
		btnNewButton_signtype_rotate_backword
				.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_signtype_rotate_backword.addMouseListener(new SelectSignTypeMouseAdapter(this.request) {
			@Override
			public Sign getSign() {
				Sign sign = null;
				if ((sign = getCurrentSign()) == null) {
					return null;
				}

				sign.rotateLeft();
				return sign;
			}

		});
		panel_list_signtype_index.setLayout(new GridLayout(0, 2, 0, 0));
		btnNewButton_signtype_rotate_backword.setBackground(SystemColor.controlHighlight);
		panel_list_signtype_index.add(btnNewButton_signtype_rotate_backword);

		JButton btnNewButton_signtype_rotate_forword = new JButton("►");
		btnNewButton_signtype_rotate_forword.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_signtype_rotate_forword.addMouseListener(new SelectSignTypeMouseAdapter(this.request) {
			@Override
			public Sign getSign() {
				Sign sign = null;
				if ((sign = getCurrentSign()) == null) {
					return null;
				}

				sign.rotateRight();
				return sign;
			}

		});
		btnNewButton_signtype_rotate_forword.setBackground(SystemColor.controlHighlight);
		panel_list_signtype_index.add(btnNewButton_signtype_rotate_forword);
		
		JPanel panel = new JPanel();
		panel_lbar_center2.add(panel);
		
		JPanel panel_add_and_subtract_cycle = new JPanel();
		panel_lbar_center2.add(panel_add_and_subtract_cycle);
		panel_add_and_subtract_cycle.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton_cycle_subtract = new JButton("-");
		btnNewButton_cycle_subtract.setFont(new Font("新細明體", Font.BOLD, 18));
		btnNewButton_cycle_subtract.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_cycle_subtract.setBackground(SystemColor.controlHighlight);
		panel_add_and_subtract_cycle.add(btnNewButton_cycle_subtract);
		
		JButton btnNewButton_cycle_add = new JButton("+");
		btnNewButton_cycle_add.setFont(new Font("新細明體", Font.BOLD, 18));
		btnNewButton_cycle_add.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
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
		btnNewButton_col_subtract.addMouseListener(new SelectSignTypeMouseAdapter(this.request) {
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
		btnNewButton_col_add.addMouseListener(new SelectSignTypeMouseAdapter(this.request) {
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
		panel_btnpanel2.setPreferredSize(new Dimension(10, 50));
		panel_col_and_row.add(panel_btnpanel2);
		panel_btnpanel2.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnNewButton_row_subtract = new JButton("▲-");
		btnNewButton_row_subtract.addMouseListener(new SelectSignTypeMouseAdapter(this.request) {
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

		JPanel panel_spacepanel2 = new JPanel();
		panel_col_and_row.add(panel_spacepanel2);
		panel_spacepanel2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton_row_add = new JButton("▼+");
		panel_spacepanel2.add(btnNewButton_row_add);
		btnNewButton_row_add.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_row_add.setBackground(SystemColor.controlHighlight);
		
		JPanel panel_1 = new JPanel();
		panel_col_and_row.add(panel_1);

		JPanel panel_c1_east = new JPanel();
		panel_c1.add(panel_c1_east, BorderLayout.EAST);
		panel_c1_east.setLayout(new BoxLayout(panel_c1_east, BoxLayout.Y_AXIS));

		JPanel panel_rbar_top = new JPanel();
		panel_c1_east.add(panel_rbar_top);

		JLabel lblNewLabel_pointer = new JLabel("0 / 0");
		panel_rbar_top.add(lblNewLabel_pointer);
		request.addParameter("lblNewLabel_pointer", lblNewLabel_pointer);

		JPanel panel_rbar_center = new JPanel();
		panel_c1_east.add(panel_rbar_center);
		panel_rbar_center.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnNewButton_reset_pointer = new JButton("Reset ID");
		btnNewButton_reset_pointer.addMouseListener(new SelectSignTypeMouseAdapter(this.request) {
			@Override
			public Sign getSign() {
				Sign sign = null;
				if ((sign = getCurrentSign()) == null) {
					return null;
				}

				sign.resetPointerToCurrentCycleElement();
				return sign;
			}

		});
		btnNewButton_reset_pointer.setFont(new Font("新細明體", Font.PLAIN, 14));
		btnNewButton_reset_pointer.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_reset_pointer.setBackground(SystemColor.controlHighlight);
		panel_rbar_center.add(btnNewButton_reset_pointer);

		JPanel panel_rbar_bottom = new JPanel();
		panel_rbar_bottom.setPreferredSize(new Dimension(10, 250));
		panel_c1_east.add(panel_rbar_bottom);

		JLabel lblNewLabel_szie = new JLabel("0 ╳ 0");
		panel_rbar_bottom.add(lblNewLabel_szie);
		request.addParameter("lblNewLabel_szie", lblNewLabel_szie);
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
				.getSign((Enum<?>) list_signtype.getSelectedValue());
	}

	public JFrame getThisFrame() {
		return this;
	}

}

abstract class SelectSignTypeMouseAdapter extends MouseAdapter {

	private Behavior.Request request;

	public SelectSignTypeMouseAdapter(Behavior.Request request) {
		this.request = request;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Behavior behavior = new ListSelectSignTypeBehavior();
		behavior.setParameter("sign", getSign());
		behavior.setRequest(getRequest());

		BehaviorController.sendBehavior(behavior);

	}

	/*
	 * get and set
	 */

	public abstract Sign getSign();

	private Behavior.Request getRequest() {
		if (this.request == null) {
			this.request = new Behavior.Request();
		}
		return this.request;
	}

}
