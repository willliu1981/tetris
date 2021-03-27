package com.model;

import java.util.Map;
import java.util.Optional;

import com.tool.TPoint;

public abstract class Sign {
	/*
	 * 最小單位方塊
	 */
	protected static class Cube {
		protected Optional<String> name;
		protected TPoint point;// 座標資訊

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
			this.name = Optional.ofNullable(name);
			this.point = new TPoint(x, y);
		}

		protected String getName() {
			return this.name.orElse("Cube");
		}

		public String toString() {
			return String.format("[%s x=%d,y=%d", this.getName(), this.point.getX(), this.point.getY());
		}

	}

	protected Map<TPoint, Cube> mapCube;
	protected Optional<String> name = Optional.empty();
	protected TPoint point;// 座標資訊

	protected Sign() {
		this(null, 0, 0);
	}

	protected Sign(int x, int y) {
		this(null, x, y);
	}

	protected Sign(String name) {
		this(name, 0, 0);
	}

	private Sign(String name, int x, int y) {
		this.name = Optional.ofNullable(name);
		this.point = new TPoint(x, y);
	}

	/*
	 * get and set
	 */

	public void addCubeMap(int x, int y) {
		this.mapCube.put(new TPoint(x, y), new Cube());
	}
	
	public Cube getCube(int x,int y) {//test
		return this.mapCube.get(new TPoint(x,y));
	}

	public Map<TPoint, Cube> getCubeMap() {//test
		return this.mapCube;
	}

	public String getName() {
		return this.name.orElse("Sign");
	}

	public TPoint getPoint() {
		return this.point;
	}

	public TPoint getPivot() {
		return this.point;
	}

	public void setPoint(int x, int y) {
		this.point.setPoint(x, y);
	}

	public void setPivot(int x, int y) {
		this.point.setPivot(x, y);
	}

	public String toString() {
		return String.format("%s", this.getName());
	}

}
