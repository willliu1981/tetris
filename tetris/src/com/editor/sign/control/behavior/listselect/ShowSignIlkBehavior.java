package com.editor.sign.control.behavior.listselect;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import com.main.control.manager.SignManager;
import com.tool.behavior.Behavior;

public class ShowSignIlkBehavior extends Behavior {

	@Override
	public void run() {
		JList list_signilk=(JList) this.getParameter("list_signilk");
		
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
	}

}
