package com.editor.sign.control;

import java.awt.GridLayout;

import javax.swing.JList;
import javax.swing.JPanel;

import com.control.manager.SignManager;
import com.control.manager.SignManager.SignType;
import com.control.manager.keyvalue.MainSignGetter.GetterType;
import com.model.Sign;
import com.sun.glass.ui.Size;
import com.tool.BorderFixer;
import com.view.EditorSign;

public class ListSelectSignTypeBehavior extends Behavior {

	@Override
	public void run() {
		JList<?> list_signilk = (JList<?>) this.getRequest("list_signilk");
		JList<?> list_signtype = (JList<?>) this.getRequest("list_signtype");
		JPanel panel_grid = (JPanel) this.getRequest("panel_grid");
		BorderFixer center_grid_fixer = (BorderFixer) this.getRequest("center_grid_fixer");

		SignManager manager = SignManager.getManager((SignType) list_signilk.getSelectedValue());
		GetterType type = (GetterType) list_signtype.getSelectedValue();
		Sign sign = manager.getSign(type);
		GridLayout layout = (GridLayout) (panel_grid.getLayout());
		layout.setColumns(sign.getWidth());
		layout.setRows(sign.getHeight());

		center_grid_fixer.init();
		panel_grid.removeAll();
		for (int h = 0; h < sign.getHeight(); h++) {
			for (int w = 0; w < sign.getWidth(); w++) {
				panel_grid.add(new EditorSign(String.format("%d , %d", w, h)));
			}
		}
		panel_grid.revalidate();
		
		center_grid_fixer.setTopSize(20);

	}

}
