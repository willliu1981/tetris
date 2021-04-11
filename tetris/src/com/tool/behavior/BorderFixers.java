package com.tool.behavior;

import java.awt.Component;

import com.tool.direction.Direction;

public class BorderFixers {

	/*
	 * 傳入x、y方向的單位數量,和x、y方向的總長度(w、h),回傳合適的即每個單位長度相等的w、h,回傳為有效 width和height 的Direction
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

	public static Direction getFixedAdvice(int num_x, int num_y, Direction d) {
		return getFixedAdvice(num_x, num_y, d.getWidth(), d.getHeight());
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
	 * 多載fixAsCenter
	 * ,直接以傳入BorderLayout內中間元件內的x、y方向單位數量,和BorderLayou來源元件即BorderLayout內各元件的父元件
	 * 的w、y長度; 該方法是綜合getCenterSize和getFixedAdvice方法,直接調節BorderLayout的中間元件大小
	 */
	public static <T extends Component> void fixAsCenter(BorderFixer<T> fixer, int num_x, int num_y, int parentW,
			int parentH) {
		Direction d = getCenterSize(fixer, parentW, parentH);
		fixAsCenter(fixer, getFixedAdvice(num_x, num_y, d));
	}

	/*
	 * 計算後取得中間元件的大小,由於BorderLayout的中間元件是由其它四個方位的元件決定大小後才能得知的;
	 * 在同一程序區塊中調整各個元件大小後,中間元件並不會馬上反應結果,所以這裡使用預先取得元件的fixer和元件的父元件即BorderLayout來源元件,
	 * 然後計算並取得Direct(有效變量為w和h)
	 */
	public static <T extends Component> Direction getCenterSize(BorderFixer<T> fixer, int parentW, int parentH) {
		int w = parentW - (fixer.getWestSize() + fixer.getEastSize());
		int h = parentH - (fixer.getNorthSize() + fixer.getSouthSize());
		return new Direction(0, 0, w, h);
	}
}
