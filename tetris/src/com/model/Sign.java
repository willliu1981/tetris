package com.model;

import java.util.HashMap;
import java.util.Map;

import com.control.exception.TNullException;
import com.sun.glass.ui.Size;
import com.tool.Direction;

public abstract class Sign implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 最小單位方塊
	 */
	protected static class Cube implements java.io.Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		protected String name;
		protected Direction point;// 座標資訊
		protected Picture picture;

		protected Cube() {
			this(null, 0, 0);
		}

		protected Cube(String name) {
			this(name, 0, 0);
		}

		protected Cube(int x, int y) {
			this(null, x, y);
		}

		private Cube(String name, int x, int y) {
			this.name = name;
			this.point = new Direction(x, y);
		}

		/*
		 * get and set
		 */

		public Picture getPicture() {
			return this.picture;
		}

		protected String getName() {
			return this.name;
		}

		protected Direction getPoint() {

			return this.point;
		}

		public String toString() {
			return String.format("[%s x=%d,y=%d", this.getName(), this.getPoint().getX(), this.getPoint().getY());
		}

	}

	/*
	 * 外觀
	 */
	protected static class Picture implements java.io.Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		protected Integer id;
	}

	/*
	 * 動畫音效
	 */
	protected static class Sound implements java.io.Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		protected Integer id;
	}

	protected Map<Direction, Cube> mapCube = new HashMap<>();
	protected String name;
	protected Direction direction;
	protected Picture picture;
	protected Sound sound;

	protected Sign() {
		this(null, 0, 0);
	}

	protected Sign(int x, int y) {
		this(null, x, y);
	}

	protected Sign(String name) {
		this(name, 0, 0);
	}

	protected Sign(String name, int x, int y) {
		this.name = name;
		this.direction = new Direction(x, y);
	}

	public boolean containLocalPoint(int x, int y) {
		return this.mapCube.containsKey(new Direction(x, y));
	}

	public boolean isPivot(int x, int y) {
		return this.direction.getPivotX() == x && this.direction.getPivotY() == y;
	}

	/*
	 * get and set
	 */

	public void addCubeMap(int x, int y) {
		this.mapCube.put(new Direction(x, y), new Cube());
	}

	public String getName() {
		if (this.name == null) {
			this.name = "Sign";
		}
		return this.name;
	}

	public Direction getDirection() {
		if (this.direction == null) {
			this.direction = new Direction();
		}
		return this.direction;
	}

	public void setPoint(int x, int y) {
		this.getDirection().setPoint(x, y);
	}

	public void setPivot(int x, int y) {
		this.getDirection().setPivot(x, y);
	}

	public int getWidth() {
		return this.getDirection().getWidth();
	}

	public int getHeight() {
		return this.getDirection().getHeight();
	}

	public void setSize(int w, int h) {
		this.getDirection().setSize(w, h);
	}

	public String toString() {
		return String.format("%s", this.getName());
	}

}
