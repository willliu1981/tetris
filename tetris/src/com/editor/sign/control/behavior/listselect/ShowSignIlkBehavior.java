package com.editor.sign.control.behavior.listselect;

import java.awt.Component;
import java.awt.Font;

import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.main.control.manager.SignManager;
import com.main.control.manager.SignManager.SignType;
import com.tool.behavior.Behavior;

public class ShowSignIlkBehavior extends Behavior {
	private static class MyListCellRenderer extends  JLabel implements  ListCellRenderer<SignType> {
		@Override
		public Component getListCellRendererComponent(JList<? extends SignType> list, SignType value, int index,
				boolean isSelected, boolean cellHasFocus) {
			this.setFont(new Font(Font.SERIF,Font.PLAIN,16));
			this.setText(value.name());
			return this;
		}
	}
	

	@Override
	public void run() {
		
		
		JList<SignType> list_signilk=(JList<SignType>) this.getParameter("list_signilk");
		
		list_signilk.setCellRenderer(new MyListCellRenderer());
		
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
