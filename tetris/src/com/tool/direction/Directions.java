package com.tool.direction;

import java.awt.Insets;
import java.awt.Point;

public class Directions {
	static public Direction getDirection(Insets inset) {
		int w = inset.right - inset.left;
		int h = inset.bottom - inset.top;
		return new Direction(0, 0, w, h);
	}

	static public boolean equal(Point p1, Point p2) {
		return p1.x == p2.x && p1.y == p2.y;
	}
}
