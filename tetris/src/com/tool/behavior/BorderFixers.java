package com.tool.behavior;

import java.awt.Component;

import com.tool.direction.Direction;

public class BorderFixers {

	/*
	 * get sub x 數量 ,sub y 數量 ,super w 長度 ,super h 長度 and return 需要修整的長度 Direction
	 * ,僅有作用的 width、height
	 */
	public static Direction getFixedAdvice(int x, int y, int w, int h) {
		int piece_w = w / x;
		int piece_h = h / y;
		int fix_w = 0;
		int fix_h = 0;
		if (piece_w > piece_h) {
			fix_w = w - (piece_h * x);
		} else {
			fix_h = h - (piece_w * y);
		}

		return new Direction(0, 0, fix_w, fix_h);
	}

	/*
	 * 將center元件置中調整
	 */
	public static <T extends Component> void fixAsCenter(Direction fixSize, BorderFixer<T> fixer) {
		fixer.setLeftSize(fixSize.getWidth() / 2 + fixer.getLeftSize());
		fixer.setRightSize(fixSize.getWidth() / 2 + fixer.getRightSize());
		fixer.setTopSize(fixSize.getHeight() / 2 + fixer.getTopSize());
		fixer.setBottomSize(fixSize.getHeight() / 2 + fixer.getBottomSize());
	}

	/*
	 * 計算後取得中間元件的大小
	 */
	public static <T extends Component> Direction calcCenterSize(int parentW, int parentH, BorderFixer<T> fixer) {
		int w = parentW - (fixer.getLeftSize() + fixer.getRightSize());
		int h = parentH - (fixer.getTopSize() + fixer.getBottomSize());
		return new Direction(0, 0, w, h);
	}
}
