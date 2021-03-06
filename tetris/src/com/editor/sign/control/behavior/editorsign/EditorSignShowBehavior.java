package com.editor.sign.control.behavior.editorsign;

import java.awt.Color;

import com.editor.sign.view.EditorSignPanel;
import com.main.model.Sign;
import com.tool.TColor;
import com.tool.behavior.Behavior;
import com.tool.direction.Direction;

public class EditorSignShowBehavior extends Behavior {

	@Override
	public void run() {
		EditorSignPanel editorsign = (EditorSignPanel) this.getParameter("editorsign");
		Sign sign = (Sign) this.getParameter("sign");
		Direction d = editorsign.getDirection();
		if (sign.containCube(d.getX(), d.getY())) {
			editorsign.getMainPanel().setBackground(Color.orange);
		} else {
			editorsign.getMainPanel().setBackground(TColor.baseColor);
		}

		if (sign.isPivot(d.getX(), d.getY())) {
			editorsign.setBackground(Color.blue);
		} else {
			editorsign.setBackground(TColor.baseColor);
		}

	}

}
