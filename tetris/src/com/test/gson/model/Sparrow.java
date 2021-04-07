package com.test.gson.model;

public class Sparrow extends Birds {
	private int noise;

	/*
	 * get and set
	 */
	public void setNoise(int noise) {
		this.noise = noise;
	}

	public int getNoise() {
		return this.noise;
	}

	public String toString() {
		return super.toString() + String.format(",swimmingSpeed->%d", this.noise);
	}
}
