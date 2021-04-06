package com.test.gson.model;


public class Penguin extends Birds {
	private int swimmingSpeed;

	/*
	 * get and set
	 */
	public void setSwimmingSpeed(int speed) {
		this.swimmingSpeed = speed;
	}

	public int getSwimmingSpeed() {
		return this.swimmingSpeed;
	}

	public String toString() {
		return super.toString() + String.format(",swimmingSpeed->%d", this.swimmingSpeed);
	}
}
