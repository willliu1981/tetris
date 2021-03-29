package com.tool;

public class Directions {

	/*
	 * get sub x 數量 ,sub y 數量 ,super w 長度 ,super h 長度 and return 需要修整的長度 Direction
	 * ,且有作用的 width、height
	 */
	public static Direction fixRectangle(int x, int y, int w, int h) {
		int piece_w = w / x;
		int piece_h = h / x;
		int fix_w = 0;
		int fix_h = 0;
		if (piece_w > piece_h) {
			fix_w = w - (piece_h * x);
		} else {
			fix_h = h - (piece_w * y);
		}

		return new Direction(0, 0, fix_w, fix_h);
	}
}
