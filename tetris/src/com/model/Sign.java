package com.model;

import java.util.Optional;

import com.tool.TPoint;

/*
 * 由小方塊組成的形狀
 * 例如:S、Z、T、L、J、O、I *可能需要一點想像力
 */
public abstract class Sign {
	/*
	 * 最小單位方塊
	 */
	protected static class Cube {
		protected TPoint point;// 座標資訊

	}

	protected Optional<String> name = Optional.empty();
	protected TPoint point;// 座標資訊

	protected Sign() {
		this.point = new TPoint(0, 0);
	}

	protected Sign(TPoint point) {
		this();
		this.point = point;
	}

	protected Sign(String name) {
		this.name = Optional.ofNullable(name);
	}

	public String getName() {
		return this.name.orElse("Sign");
	}
	
	protected TPoint getPoint() {
		return this.point;
	}

	public String toString() {
		return String.format("%s", this.getName());
	}

}
