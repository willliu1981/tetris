package com.tool;

import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

public class BorderFixer<T extends Component> {
	public static final String Top = "top";
	public static final String Bottom = "bottom";
	public static final String Left = "left";
	public static final String Right = "right";

	private Map<String, T> mapBorder = new HashMap<>();

	private BorderFixer() {

	}

	public static <T extends Component> BorderFixer<T> getFixer() {
		return new BorderFixer<T>();
	}

	public void add(String name, T comp) {
		this.mapBorder.put(name, comp);
	}

	public T get(String name) {
		return this.mapBorder.get(name);
	}

	public void init() {
		this.reset();
	}

	private void reset() {
		this.mapBorder.values().stream().forEach(x -> x.setPreferredSize(new Dimension(10, 10)));
	}

	public void setTopSize(int size) {
		Dimension ori = this.mapBorder.get(BorderFixer.Top).getPreferredSize();
		this.mapBorder.get(BorderFixer.Top).setPreferredSize(new Dimension((int) ori.getWidth(), size));
	}

	public void setBottomSize(int size) {
		Dimension ori = this.mapBorder.get(BorderFixer.Bottom).getPreferredSize();
		this.mapBorder.get(BorderFixer.Bottom).setPreferredSize(new Dimension((int) ori.getWidth(), size));
	}

	public void setLeftSize(int size) {
		Dimension ori = this.mapBorder.get(BorderFixer.Left).getPreferredSize();
		this.mapBorder.get(BorderFixer.Left).setPreferredSize(new Dimension(size, (int) ori.getHeight()));
	}

	public void setRightSize(int size) {
		Dimension ori = this.mapBorder.get(BorderFixer.Right).getPreferredSize();
		this.mapBorder.get(BorderFixer.Right).setPreferredSize(new Dimension(size, (int) ori.getHeight()));
	}
}
