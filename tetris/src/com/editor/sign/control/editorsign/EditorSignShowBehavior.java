package com.editor.sign.control.editorsign;

import java.awt.Color;

import com.control.manager.Behavior;
import com.model.Sign;
import com.tool.Direction;
import com.tool.TColor;
import com.view.EditorSign;

public class EditorSignShowBehavior extends Behavior {

	@Override
	public void run() {
		EditorSign editorsign = (EditorSign) this.getParameter("editorsign");
		Sign sign = (Sign) this.getParameter("sign");
		Direction d = editorsign.getDirection();
		if (sign.containCube(d.getX(), d.getY())) {
			editorsign.getMainPanel().setBackground(Color.orange);
		} else {
			editorsign.getMainPanel().setBackground(TColor.baseColor);
		}

		if (sign.isPivot(d.getX(), d.getY())) {
			editorsign.setBackground(Color.cyan);
		} else {
			editorsign.setBackground(TColor.baseColor);
		}

	}

}
