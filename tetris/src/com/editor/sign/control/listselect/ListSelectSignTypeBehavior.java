package com.editor.sign.control.listselect;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.MediaTracker;

import javax.swing.JList;
import javax.swing.JPanel;

import com.control.manager.SignManager;
import com.control.manager.SignManager.SignType;
import com.control.manager.keyvalue.MainSignGetter.GetterType;
import com.editor.sign.control.Behavior;
import com.editor.sign.control.BehaviorController;
import com.editor.sign.control.editorsign.EditorSignShowBehavior;
import com.model.Sign;
import com.sun.glass.ui.Size;
import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import com.tool.BorderFixer;
import com.tool.Direction;
import com.tool.TColor;
import com.tool.BorderFixers;
import com.view.EditorSign;

public class ListSelectSignTypeBehavior extends Behavior {

	@Override
	public void run() {
		JPanel panel_c1_main = (JPanel) this.getRequest("panel_c1_main");
		JList<?> list_signilk = (JList<?>) this.getRequest("list_signilk");
		JList<?> list_signtype = (JList<?>) this.getRequest("list_signtype");
		JPanel panel_grid_main = (JPanel) this.getRequest("panel_grid_main");
		BorderFixer<?> center_grid_fixer = (BorderFixer<?>) this.getRequest("center_grid_fixer");

		SignManager manager = SignManager.getManager((SignType) list_signilk.getSelectedValue());
		GetterType type = (GetterType) list_signtype.getSelectedValue();
		Sign sign = manager.getSign(type);
		GridLayout layout = (GridLayout) (panel_grid_main.getLayout());
		layout.setColumns(sign.getWidth());
		layout.setRows(sign.getHeight());
		
		center_grid_fixer.reset();
		panel_grid_main.removeAll();
		int w = sign.getWidth();
		int h = sign.getHeight();
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				EditorSign editorsign = new EditorSign(sign);
				editorsign.setDirection(x, y);

				Behavior behavior = new EditorSignShowBehavior();
				behavior.setRequest("editorsign", editorsign);
				behavior.setRequest("sign", sign);
				BehaviorController.sendBehavior(behavior);

				panel_grid_main.add(editorsign);

			}
		}
		panel_grid_main.revalidate();

		Direction d = center_grid_fixer.inferCenterSize(panel_c1_main.getWidth(), panel_c1_main.getHeight());
		Direction fixSize = BorderFixers.getFixingAdvice(w, h, d.getWidth(), d.getHeight());
		center_grid_fixer.fixAsCenter(fixSize);

	}

}
