package com.tool;

import java.awt.Point;

/*
 * tetris 專用 point
 */
public class TPoint {
	private Point point;// 於主座標上的位置, 通常是父座標系統上的位置
	private Point pivot;// this座標系統的軸心點, 於this座標系統上的位置

	public TPoint() {
		this(0, 0);
	}

	public TPoint(int x, int y) {
		this.point = new Point(x, y);
		this.pivot = new Point();
	}

	/*
	 * get and set
	 */

	public int getLeftTop_X() {// get this 座標系統 left-top point 於父座標系統上的位置
		return this.point.x - this.point.x;
	}

	public int getLeftTop_Y() {// get this 座標系統 left-top point 於父座標系統上的位置
		return this.point.y - this.point.y;
	}

	public void setPoint(int x, int y) {
		this.point.x = x;
		this.point.y = y;
	}

	public void setPivot(int x, int y) {
		this.pivot.x = x;
		this.pivot.y = y;
	}

	public int getX() {
		return this.point.x;
	}

	public int getY() {
		return this.point.y;
	}

	public int getPivotX() {
		return this.pivot.x;
	}

	public int getPivotY() {
		return this.pivot.y;
	}

	public String toString() {
		return String.format("[p_x=%d,p_y=%d,pi_x=%d,pi_y=%d]", this.getX(), this.getY(), this.getPivotX(),
				this.getPivotY());
	}
}
