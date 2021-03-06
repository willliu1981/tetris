package com.editor.sign.control.behavior.listselect;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.editor.sign.control.behavior.editorsign.EditorSignShowBehavior;
import com.editor.sign.view.EditorSignPanel;
import com.editor.sign.view.SignEditor;
import com.main.model.Sign;
import com.tool.Session;
import com.tool.behavior.Behavior;
import com.tool.behavior.BehaviorController;
import com.tool.borderlayout.BorderFixer;
import com.tool.borderlayout.BorderFixers;
import com.tool.direction.Direction;

public class ListSelectSignTypeBehavior extends Behavior {

	@Override
	public void run() {
		Session session = SignEditor.getSession();
		JPanel panel_c1_main = (JPanel) session.getAttribute("panel_c1_main");
		JPanel panel_grid_main = (JPanel) session.getAttribute("panel_grid_main");
		BorderFixer<?> center_grid_fixer = (BorderFixer<?>) session.getAttribute("center_grid_fixer");

		JLabel lblNewLabel_pointer = (JLabel) this.getParameter("lblNewLabel_pointer");
		JLabel lblNewLabel_szie = (JLabel) this.getParameter("lblNewLabel_szie");
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

		Direction d = BorderFixers.getCenterSize(center_grid_fixer, panel_c1_main.getWidth(),
				panel_c1_main.getHeight());
		Direction fixSize = BorderFixers.getFixedAdvice(w, h, d.getWidth(), d.getHeight());
		BorderFixers.fixAsCenter(center_grid_fixer, fixSize);

		panel_c1_main.revalidate();

		if (lblNewLabel_pointer != null) {
			lblNewLabel_pointer
					.setText(String.format("%d / %d", sign.getCycleCurrentPointer() + 1, sign.getCycleSize()));
		}

		if (lblNewLabel_szie != null) {
			lblNewLabel_szie.setText(String.format("%d ??? %d", w, h));
		}
	}

}
