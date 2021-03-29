package com.editor.sign.control;

import java.awt.GridLayout;

import javax.swing.JList;
import javax.swing.JPanel;

import com.control.manager.SignManager;
import com.control.manager.SignManager.SignType;
import com.control.manager.keyvalue.MainSignGetter.GetterType;
import com.model.Sign;
import com.sun.glass.ui.Size;
import com.view.EditorSign;

public class ListSelectSignTypeBehavior extends Behavior {

	@Override
	public void run() {
		JList<?> list_signilk=(JList<?>)this.getSession("list_signilk");
		JList<?> list_signtype=(JList<?>)this.getSession("list_signtype");
		JPanel panel_grid=(JPanel)this.getSession("panel_grid");
		
		SignManager manager = SignManager.getManager((SignType) list_signilk.getSelectedValue());
		GetterType type = (GetterType) list_signtype.getSelectedValue();
		Sign sign = manager.getSign(type);
		Size size = sign.getSize();
		GridLayout layout = (GridLayout) (panel_grid.getLayout());
		layout.setColumns(size.width);
		layout.setRows(size.height);

		panel_grid.removeAll();
		for (int h = 0; h < size.height; h++) {
			for (int w = 0; w < size.width; w++) {
				panel_grid.add(new EditorSign(String.format("%d , %d", w, h)));
			}
		}
		panel_grid.revalidate();
		
	}

}
