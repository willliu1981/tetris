package com.editor.sign.control;

import java.awt.GridLayout;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.control.manager.SignManager;
import com.control.manager.SignManager.SignType;
import com.control.manager.keyvalue.MainSignGetter.GetterType;
import com.model.Sign;
import com.sun.glass.ui.Size;
import com.view.EditorSign;

public class ListSelectSignIlkBehavior extends Behavior {

	@Override
	public void run() {
		JList<?> list_signilk=(JList<?>)this.getSession("list_signilk");
		JList<?> list_signtype=(JList<?>)this.getSession("list_signtype");
		
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
		if (list_signtype.getModel().getSize() > 0) {
			list_signtype.setSelectedIndex(0);
		} else {
			list_signtype.setSelectedIndex(-1);
		}
	}

}
