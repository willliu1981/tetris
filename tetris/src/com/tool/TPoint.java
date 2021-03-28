package com.tool;

import java.awt.Point;

/*
 * tetris 專用 point
 */
public class TPoint implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point local;// 於主座標上的位置, 通常是父座標系統上的位置
	private Point pivot;// this座標系統的軸心點, 於this座標系統上的位置

	public TPoint() {
		this(0, 0);
	}

	public TPoint(int x, int y) {
		this.local = new Point(x, y);
		this.pivot = new Point();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		TPoint o = null;
		if (obj instanceof TPoint) {
			o = (TPoint) obj;
		} else {
			return false;
		}

		if (this.local != null && o.local != null) {
			return equal(this.local, o.local);
		} else if (this.local == null && o.local == null) {
			return true;
		}

		return false;
	}

	protected static boolean equal(Point p1, Point p2) {
		return p1.x == p2.x && p1.y == p2.y;
	}

	@Override
	public int hashCode() {

		return this.getX() * 3 + this.getY() * 5;
	}

	/*
	 * get and set
	 */

	public int getLeftTop_X() {// get this 座標系統 left-top point 於父座標系統上的位置
		return this.local.x - this.local.x;
	}

	public int getLeftTop_Y() {// get this 座標系統 left-top point 於父座標系統上的位置
		return this.local.y - this.local.y;
	}

	public void setLocal(int x, int y) {
		this.local.x = x;
		this.local.y = y;
	}

	public void setPivot(int x, int y) {
		this.pivot.x = x;
		this.pivot.y = y;
	}

	public int getX() {
		return this.local.x;
	}

	public int getY() {
		return this.local.y;
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
