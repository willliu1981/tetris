package com.editor.sign.control.behavior.listselect;

import java.awt.GridLayout;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.main.control.manager.Behavior;
import com.main.control.manager.SignManager;
import com.main.control.manager.SignManager.SignType;
import com.main.control.manager.getter.MainSignGetter.GetterType;
import com.main.view.EditorSign;
import com.model.Sign;
import com.sun.glass.ui.Size;

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
