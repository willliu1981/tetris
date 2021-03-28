package com.model;

/*
 * 由小方塊組成的形狀
 * 例如:S、Z、T、L、J、O、I *可能需要一點想像力
 */
public class MainSign extends Sign {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public MainSign() {
		this(0, 0);
	}

	public MainSign(String name) {
		super(name);
	}

	public MainSign(int x, int y) {
		super(x, y);
	}

	public MainSign(String name, int x, int y) {
		super(name, x, y);
	}



	/*
	 * get and set
	 */

	public String toString() {
		return String.format("%s point=%s", this.getName(), this.getSignPoint());
	}

}
