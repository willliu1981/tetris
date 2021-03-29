package com.tool;

import java.awt.Point;

import com.sun.glass.ui.Size;

/*
 * tetris 專用 座標
 */
public class Direction implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point point;// 於主座標上的位置, 通常是父座標系統上的位置
	private Point pivot;// this座標系統的軸心點, 於this座標系統上的位置
	private Size size;// this座標系統的size ,x and y 的個數

	public Direction() {
		this(0, 0, 0, 0);
	}

	public Direction(int x, int y) {
		this(x, y, 0, 0);
	}

	public Direction(int x, int y, int w, int h) {
		this.point = new Point(x, y);
		this.pivot = new Point();
		this.size = new Size(w, h);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		Direction o = null;
		if (obj instanceof Direction) {
			o = (Direction) obj;
		} else {
			return false;
		}

		if (this.point != null && o.point != null) {
			return equal(this.point, o.point);
		} else if (this.point == null && o.point == null) {
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
	
	public void setSize(int w,int h) {
		this.size.width=w;
		this.size.height=h;
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

	public int getWidth() {
		return this.size.width;
	}

	public int getHeight() {
		return this.size.height;
	}

	public String toString() {
		return String.format("[p_x=%d,p_y=%d,pi_x=%d,pi_y=%d,w=%d,h=%d]", this.getX(), this.getY(), this.getPivotX(),
				this.getPivotY(),this.getWidth(),this.getHeight());
	}
}
