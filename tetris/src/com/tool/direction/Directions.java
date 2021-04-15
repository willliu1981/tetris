package com.tool.direction;

import java.awt.Insets;
import java.awt.Point;

import com.main.model.Sign;

public class Directions {
	static public Direction getDirection(Insets inset) {
		int w = inset.right - inset.left;
		int h = inset.bottom - inset.top;
		return new Direction(0, 0, w, h);
	}

	static public boolean equal(Point p1, Point p2) {
		return p1.x == p2.x && p1.y == p2.y;
	}

	static public Direction add(Direction d1, Direction d2) {
		return new Direction(d1.getX() + d2.getX(), d1.getY() + d2.getY());
	}

	static public Direction getCubeWorldPoint(Sign sign, Direction cubeDirc) {
		return add(new Direction(sign.getLTX(), sign.getLTY()), cubeDirc);
	}
}
