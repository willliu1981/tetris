package com.tool;

import java.awt.Insets;

public class Directions {
	static public Direction getDirection(Insets inset) {
		int w = inset.right - inset.left;
		int h = inset.bottom - inset.top;
		return new Direction(0, 0, w, h);
	}
}
