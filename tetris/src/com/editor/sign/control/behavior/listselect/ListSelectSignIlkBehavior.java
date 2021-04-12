package com.editor.sign.control.behavior.listselect;

import java.awt.GridLayout;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.editor.sign.view.EditorSignPanel;
import com.main.control.manager.SignManager;
import com.main.control.manager.MainSignGetter.GetterMainSginType;
import com.main.control.manager.SignManager.SignType;
import com.main.model.Sign;
import com.sun.glass.ui.Size;
import com.tool.behavior.Behavior;

public class ListSelectSignIlkBehavior extends Behavior {

	@Override
	public void run() {
		JList<?> list_signilk = (JList<?>) this.getParameter("list_signilk");
		JList<?> list_signtype = (JList<?>) this.getParameter("list_signtype");
		JPanel panel_grid_main = (JPanel) this.getParameter("panel_grid_main");
		
		panel_grid_main.removeAll();

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
