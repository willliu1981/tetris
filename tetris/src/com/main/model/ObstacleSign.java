package com.main.model;

/*
 * 由小方塊組成的形狀
 * 例如:S、Z、T、L、J、O、I *可能需要一點想像力
 */
public class ObstacleSign extends Sign {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObstacleSign() {
		this(0, 0);
	}

	public ObstacleSign(String name) {
		super(name);
	}

	public ObstacleSign(int x, int y) {
		super(x, y);
	}

	public ObstacleSign(String name, int x, int y) {
		super(name, x, y);
	}

	/*
	 * get and set
	 */

	public String toString() {
		return String.format("%s point=%s", this.getName(), this.getDirection());
	}

}
