package com.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.controller.exception.TNullException;
import com.sun.glass.ui.Size;
import com.tool.TPoint;

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
		protected Optional<String> name;
		protected Optional<TPoint> point = Optional.empty();// 座標資訊
		protected Optional<Picture> picture;

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
			this.point = Optional.of(new TPoint(x, y));
		}

		/*
		 * get and set
		 */

		public Picture getPicture() {
			return this.picture.orElseThrow(TNullException::new);
		}

		protected String getName() {
			return this.name.orElse("Cube");
		}

		protected TPoint getPoint() {
			this.point = Optional.of(this.point.orElseGet(TPoint::new));
			return this.point.get();
		}

		public String toString() {
			return String.format("[%s x=%d,y=%d", this.getName(), this.getPoint().getX(), this.getPoint().getY());
		}

	}

	/*
	 * 外觀
	 */
	protected static class Picture implements java.io.Serializable  {
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

	protected Map<TPoint, Cube> mapCube;
	protected Optional<String> name = Optional.empty();
	protected Optional<TPoint> signPoint = Optional.empty();
	protected Picture picture;
	protected Sound sound;
	protected Optional<Size> size = Optional.empty();

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
		mapCube=new HashMap<>();
		this.name = Optional.ofNullable(name);
		this.signPoint = Optional.of(new TPoint(x, y));
	}

	/*
	 * get and set
	 */

	public void addCubeMap(int x, int y) {
		this.mapCube.put(new TPoint(x, y), new Cube());
	}

	public Cube getCube(int x, int y) {// test
		return this.mapCube.get(new TPoint(x, y));
	}

	public Map<TPoint, Cube> getCubeMap() {// test
		return this.mapCube;
	}

	public String getName() {
		return this.name.orElse("Sign");
	}

	protected TPoint getSignPoint() {
		this.signPoint = Optional.of(this.signPoint.orElseGet(TPoint::new));
		return this.signPoint.get();
	}

	public void setPoint(int x, int y) {
		this.getSignPoint().setLocal(x, y);
	}

	public void setPivot(int x, int y) {
		this.getSignPoint().setPivot(x, y);
	}

	public Size getSize() {
		this.size = Optional.of(this.size.orElseGet(Size::new));
		return this.size.get();
	}

	public void setSize(int w, int h) {
		this.getSize().width = w;
		this.getSize().height = h;
	}

	public String toString() {
		return String.format("%s", this.getName());
	}

}
