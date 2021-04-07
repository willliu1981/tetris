package com.editor.sign.control.behavior.listselect;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.MediaTracker;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.editor.sign.control.behavior.editorsign.EditorSignShowBehavior;
import com.editor.sign.view.EditorSignPanel;
import com.editor.sign.view.SignEditor;
import com.main.control.manager.Behavior;
import com.main.control.manager.BehaviorController;
import com.main.control.manager.Session;
import com.main.control.manager.SignManager;
import com.main.control.manager.SignManager.SignType;
import com.main.model.Sign;
import com.main.model.MainSignGetter.GetterType;
import com.sun.glass.ui.Size;
import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import com.tool.BorderFixer;
import com.tool.Direction;
import com.tool.TColor;
import com.tool.BorderFixers;

public class ListSelectSignTypeBehavior extends Behavior {

	@Override
	public void run() {
		Session session = SignEditor.getSession();
		JPanel panel_c1_main = (JPanel) session.getAttribute("panel_c1_main");
		JPanel panel_grid_main = (JPanel) session.getAttribute("panel_grid_main");
		BorderFixer<?> center_grid_fixer = (BorderFixer<?>) session.getAttribute("center_grid_fixer");

		JLabel lblNewLabel_pointer = (JLabel) this.getParameter("lblNewLabel_pointer");
		Sign sign = (Sign) this.getParameter("sign");
		if (sign == null) {
			return;
		}

		GridLayout layout = (GridLayout) (panel_grid_main.getLayout());
		layout.setColumns(sign.getWidth());
		layout.setRows(sign.getHeight());

		center_grid_fixer.reset();
		panel_grid_main.removeAll();
		int w = sign.getWidth();
		int h = sign.getHeight();
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				EditorSignPanel editorsign = new EditorSignPanel(sign);
				editorsign.setDirection(x, y);

				Behavior behavior = new EditorSignShowBehavior();
				behavior.setParameter("editorsign", editorsign);
				behavior.setParameter("sign", sign);
				BehaviorController.sendBehavior(behavior);

				panel_grid_main.add(editorsign);

			}
		}

		Direction d = center_grid_fixer.inferCenterSize(panel_c1_main.getWidth(), panel_c1_main.getHeight());
		Direction fixSize = BorderFixers.getFixingAdvice(w, h, d.getWidth(), d.getHeight());
		center_grid_fixer.fixAsCenter(fixSize);

		panel_grid_main.revalidate();

		if (lblNewLabel_pointer != null) {
			lblNewLabel_pointer.setText(String.format("%d / %d", sign.getCycleCurrentPointer()+1, sign.getCycleSize()));
		}
	}

}
