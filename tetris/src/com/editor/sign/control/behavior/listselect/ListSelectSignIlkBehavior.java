package com.editor.sign.control.behavior.listselect;

import java.awt.GridLayout;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.editor.sign.view.EditorSignPanel;
import com.main.control.manager.SignManager;
import com.main.control.manager.SignManager.SignType;
import com.main.model.Sign;
import com.main.model.MainSignGetter.GetterType;
import com.sun.glass.ui.Size;
import com.tool.behavior.Behavior;

public class ListSelectSignIlkBehavior extends Behavior {

	@Override
	public void run() {
		JList<?> list_signilk = (JList<?>) this.getParameter("list_signilk");
		JList<?> list_signtype = (JList<?>) this.getParameter("list_signtype");

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
