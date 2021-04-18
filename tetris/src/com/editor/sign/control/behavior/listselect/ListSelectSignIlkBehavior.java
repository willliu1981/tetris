package com.editor.sign.control.behavior.listselect;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.editor.sign.view.EditorSignPanel;
import com.main.control.manager.SignManager;
import com.main.control.manager.SignManager.SignType;
import com.main.control.signgetter.MainSignGetter.GetterMainSginType;
import com.main.model.Sign;
import com.sun.glass.ui.Size;
import com.tool.behavior.Behavior;

public class ListSelectSignIlkBehavior extends Behavior {
	private  class MyListCellRenderer extends  JLabel implements  ListCellRenderer<Enum<?>>{
		@Override
		public Component getListCellRendererComponent(JList<? extends Enum<?>> list, Enum<?> value, int index,
				boolean isSelected, boolean cellHasFocus) {
			this.setFont(new Font(Font.SERIF,Font.PLAIN,16));
			this.setText(value.name());
			return this;
		}
	}
	

	@Override
	public void run() {
		JList<SignType> list_signilk = (JList<SignType>) this.getParameter("list_signilk");
		JList<Enum<?>> list_signtype = (JList<Enum<?>>) this.getParameter("list_signtype");
		JPanel panel_grid_main = (JPanel) this.getParameter("panel_grid_main");
		
		panel_grid_main.removeAll();

		list_signtype.setCellRenderer(new MyListCellRenderer());
		list_signtype.setModel(new AbstractListModel() {
			Enum[] signs;
			{
				SignManager manager = SignManager.getManager((SignType) list_signilk.getSelectedValue());
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

	}

}
