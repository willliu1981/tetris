package com.model;

import java.awt.Point;

import com.tool.TPoint;

public class MainSign extends Sign {

	public MainSign() {

	}

	public MainSign(String name) {
		super(name);

	}
	

	public MainSign(int x, int y) {
		super(new TPoint(x, y));
	}

	public String toString() {
		return String.format("%s point=%s",this.getName(), this.getPoint());
	}

}
