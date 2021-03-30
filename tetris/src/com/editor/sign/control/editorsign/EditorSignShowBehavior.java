package com.editor.sign.control.editorsign;

import java.awt.Color;

import com.editor.sign.control.Behavior;
import com.model.Sign;
import com.tool.Direction;
import com.tool.TColor;
import com.view.EditorSign;

public class EditorSignShowBehavior extends Behavior {

	@Override
	public void run() {
		EditorSign editorsign = (EditorSign) this.getRequest("editorsign");
		Sign sign = (Sign) this.getRequest("sign");
		Direction d = editorsign.getDirection();
		if (sign.containCube(d.getX(), d.getY())) {
			editorsign.getMainPanel().setBackground(Color.red);
		} else {
			editorsign.setBackground(TColor.baseColor);
		}
		
		if(sign.isPivot(d.getX(), d.getY())) {
			editorsign.setBackground(Color.cyan);
		}else {
			editorsign.setBackground(TColor.baseColor);
		}

	}

}
