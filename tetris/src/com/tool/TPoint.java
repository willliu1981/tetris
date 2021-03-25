package com.tool;

import java.awt.Point;

/*
 * tetris 專用 point
 */
public class TPoint {
	private Point mainPoint;// 於主座標上的位置, 通常是父座標系統上的位置
	private Point pivotPoint;// 相對於mainPoint 的位置,如 this座標系統的left-top point 在父座標系統的位置

	public TPoint(int x, int y) {
		this.mainPoint = new Point(x, y);
	}

	public int getX() {
		return this.mainPoint.x;
	}

	public int getY() {
		return this.mainPoint.y;
	}
	
	public String toString() {
		return String.format("[%d,%d]", this.getX(),this.getY());
	}
}
