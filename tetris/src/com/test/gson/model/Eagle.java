package com.test.gson.model;


public class Eagle extends Birds {
	private int flyHeight;
	private int huntingAtk;

	/*
	 * get and set
	 */
	public void setFlyHeight(int height) {
		this.flyHeight = height;
	}

	public int getFlyHeight() {
		return this.flyHeight;
	}

	public int getHuntingAtk() {
		return huntingAtk;
	}

	public void setHuntingAtk(int huntingAtk) {
		this.huntingAtk = huntingAtk;
	}

	public String toString() {
		return super.toString() + String.format(",flyHeight->%d,huntingAtk->%d", this.flyHeight, this.huntingAtk);
	}
}
