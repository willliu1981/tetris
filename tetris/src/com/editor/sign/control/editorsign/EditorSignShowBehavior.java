package com.editor.sign.control.editorsign;

import java.awt.Color;

import com.main.control.manager.Behavior;
import com.main.view.EditorSign;
import com.model.Sign;
import com.tool.Direction;
import com.tool.TColor;

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
			editorsign.setBackground(Color.blue);
		} else {
			editorsign.setBackground(TColor.baseColor);
		}

	}

}
