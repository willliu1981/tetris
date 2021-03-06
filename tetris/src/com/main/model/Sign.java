package com.main.model;

import java.util.HashMap;
import java.util.Map;

import com.tool.Cycle;
import com.tool.direction.Direction;

public abstract class Sign implements java.io.Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 最小單位方塊
	 */
	public static class Cube implements java.io.Serializable {
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

	protected Cycle<Map<Direction, Cube>> cycleCubeMap = new Cycle<>();
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

	public boolean containCube(int x, int y) {
		return this.getCubeMap().containsKey(new Direction(x, y));
	}

	public boolean isPivot(int x, int y) {
		return this.direction.getPivotX() == x && this.direction.getPivotY() == y;
	}

	public void rotateLeft() {
		this.cycleCubeMap.rotateLeft();
		resize();
	}

	public void rotateRight() {
		this.cycleCubeMap.rotateRight();
		resize();
	}

	/*
	 * 重新調整尺寸以附合顯示所有Cube的大小
	 */
	public void resize() {
		int w = this.getWidth();
		int h = this.getHeight();
		int x, y;
		if ((x = this.getCubeMap().keySet().stream().max((x1, x2) -> x1.getX() - x2.getX()).orElse(new Direction(0, 0))
				.getX() + 1) > this.getWidth()) {
			w = x;
		}
		if ((y = this.getCubeMap().keySet().stream().max((y1, y2) -> y1.getY() - y2.getY()).orElse(new Direction(0, 0))
				.getY() + 1) > this.getHeight()) {
			h = y;
		}

		this.setSize(w, h);
	}

	public void resetPointerToCurrentCycleElement() {
		this.cycleCubeMap.resetPointerToCurrentElement();
	}

	/*
	 * get and set
	 */

	@Override
	public Sign clone() throws CloneNotSupportedException {
		Sign sign = (Sign) super.clone();
		sign.direction = (Direction) this.direction.clone();
		sign.cycleCubeMap=this.cycleCubeMap.clone();

		return sign;
	}

	public void addCube(int x, int y) {
		this.getCubeMap().put(new Direction(x, y), new Cube());
	}

	public void removeCube(int x, int y) {
		this.getCubeMap().remove(new Direction(x, y));
	}

	public void insertSignMapAtTheBack() {
		this.cycleCubeMap.insertAtTheBack(new HashMap<>());
	}

	public void removeCurrentSignMap() {
		this.cycleCubeMap.pollFirst();
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

	public int getX() {
		return this.getDirection().getX();
	}

	public int getY() {
		return this.getDirection().getY();
	}

	public int getLTX() {
		return this.getX() - this.getDirection().getPivotX();
	}

	public int getLTY() {
		return this.getY() - this.getDirection().getPivotY();
	}

	public int getWidth() {
		if (this.getDirection().getWidth() <= 0) {
			this.getDirection().setSize(1, 1);
		}
		return this.getDirection().getWidth();
	}

	public int getHeight() {
		if (this.getDirection().getHeight() <= 0) {
			this.getDirection().setSize(1, 1);
		}
		return this.getDirection().getHeight();
	}

	public Map<Direction, Cube> getCubeMap() {
		if (this.cycleCubeMap.get() == null) {
			this.cycleCubeMap.add(new HashMap<>());
		}
		return this.cycleCubeMap.get();
	}

	public void setSize(int w, int h) {
		Direction d;
		d = this.getCubeMap().keySet().stream().max((d1, d2) -> d1.getX() - d2.getX()).orElseGet(Direction::new);

		if (w <= d.getX()) {
			w = d.getX() + 1;
		}

		d = this.getCubeMap().keySet().stream().max((d1, d2) -> d1.getY() - d2.getY()).orElseGet(Direction::new);
		if (h <= d.getY()) {
			h = d.getY() + 1;
		}

		if (w < 1) {
			w = 0;
		}

		if (h < 1) {
			h = 0;
		}
		this.getDirection().setSize(w, h);
	}

	public int getCycleCurrentPointer() {
		return this.cycleCubeMap.getCurrentPointer();
	}

	public int getCycleSize() {
		return this.cycleCubeMap.size();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return String.format("%s", this.getName());
	}

}
