package com.tool.behavior;

import java.awt.Component;

import com.tool.direction.Direction;

public class BorderFixers {

	/*
	 * get sub x 數量 ,sub y 數量 ,super w 長度 ,super h 長度 and return 需要修整的長度 Direction
	 * ,僅有作用的 width、height
	 */
	public static Direction getFixedAdvice(int num_x, int num_y, int len_w, int len_h) {
		int piece_w = len_w / num_x;
		int piece_h = len_h / num_y;
		int fix_w = 0;
		int fix_h = 0;
		if (piece_w > piece_h) {
			fix_w = len_w - (piece_h * num_x);
		} else {
			fix_h = len_h - (piece_w * num_y);
		}

		return new Direction(0, 0, fix_w, fix_h);
	}

	/*
	 * 將center元件置中調整
	 */
	public static <T extends Component> void fixAsCenter(BorderFixer<T> fixer, Direction fixSize) {
		fixer.setWestSize(fixSize.getWidth() / 2 + fixer.getWestSize());
		fixer.setEastSize(fixSize.getWidth() / 2 + fixer.getEastSize());
		fixer.setNorthSize(fixSize.getHeight() / 2 + fixer.getNorthSize());
		fixer.setSouthSize(fixSize.getHeight() / 2 + fixer.getSouthSize());
	}

	/*
	 * 計算後取得中間元件的大小
	 */
	public static <T extends Component> Direction calcCenterSize(BorderFixer<T> fixer, int parentW, int parentH) {
		int w = parentW - (fixer.getWestSize() + fixer.getEastSize());
		int h = parentH - (fixer.getNorthSize() + fixer.getSouthSize());
		return new Direction(0, 0, w, h);
	}
}
