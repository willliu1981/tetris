package com.tool.behavior;

import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.main.control.exception.TNullException;
import com.tool.direction.Direction;


public class BorderFixer<T extends Component> {
	public static final String Top = "top";
	public static final String Bottom = "bottom";
	public static final String Left = "left";
	public static final String Right = "right";

	private Map<String, Optional<T>> mapBorder = new HashMap<>();

	private BorderFixer() {

	}

	public static <T extends Component> BorderFixer<T> getFixer() {
		return new BorderFixer<T>();
	}

	public void reset() {
		this.mapBorder.values().stream().forEach(x -> x.orElseThrow(() -> new TNullException("element is null"))
				.setPreferredSize(new Dimension(10, 10)));
	}

	/*
	 * 將center元件置中調整
	 */
	public void fixAsCenter(Direction fixSize) {
		this.setLeftSize(fixSize.getWidth() / 2 + this.getLeftSize());
		this.setRightSize(fixSize.getWidth() / 2 + this.getRightSize());
		this.setTopSize(fixSize.getHeight() / 2 + this.getTopSize());
		this.setBottomSize(fixSize.getHeight() / 2 + this.getBottomSize());
	}

	/*
	 * 計算後取得中間元件的大小
	 */
	public Direction calcCenterSize(int parentW, int parentH) {
		int w = parentW - (this.getLeftSize() + this.getRightSize());
		int h = parentH - (this.getTopSize() + this.getBottomSize());
		return new Direction(0, 0, w, h);
	}

	/*
	 * get and set
	 */

	public void add(String name, T comp) {
		this.mapBorder.put(name, Optional.of(comp));
	}

	public T get(String name) {
		return this.mapBorder.get(name).orElseThrow(() -> new TNullException(name));
	}

	public void setTopSize(int size) {
		Dimension ori = this.get(BorderFixer.Top).getPreferredSize();
		this.get(BorderFixer.Top).setPreferredSize(new Dimension((int) ori.getWidth(), size));
	}

	public void setBottomSize(int size) {
		Dimension ori = this.get(BorderFixer.Bottom).getPreferredSize();
		this.get(BorderFixer.Bottom).setPreferredSize(new Dimension((int) ori.getWidth(), size));
	}

	public void setLeftSize(int size) {
		Dimension ori = this.get(BorderFixer.Left).getPreferredSize();
		this.get(BorderFixer.Left).setPreferredSize(new Dimension(size, (int) ori.getHeight()));
	}

	public void setRightSize(int size) {
		Dimension ori = this.get(BorderFixer.Right).getPreferredSize();
		this.get(BorderFixer.Right).setPreferredSize(new Dimension(size, (int) ori.getHeight()));
	}

	public int getTopSize() {
		return this.get(BorderFixer.Top).getPreferredSize().height;
	}

	public int getBottomSize() {
		return this.get(BorderFixer.Bottom).getPreferredSize().height;
	}

	public int getLeftSize() {
		return this.get(BorderFixer.Left).getPreferredSize().width;
	}

	public int getRightSize() {
		return this.get(BorderFixer.Right).getPreferredSize().width;
	}

}
