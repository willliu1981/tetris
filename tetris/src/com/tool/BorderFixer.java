package com.tool;

import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.main.control.exception.TNullException;
import com.tool.direction.Direction;

public class BorderFixer<T extends Component> {
	public static final String NORTH = "north";
	public static final String SOUTH = "south";
	public static final String WEST = "west";
	public static final String EAST = "east";

	private Map<String, Optional<T>> mapBorder = new HashMap<>();

	private BorderFixer() {

	}

	public static <T extends Component> BorderFixer<T> getFixer() {
		return new BorderFixer<T>();
	}

	/*
	 * 初始化BorderLayout內元件(除了center)的大小
	 */
	public void reset() {
		this.mapBorder.values().stream().forEach(x -> x.orElseThrow(() -> new TNullException("element is null"))
				.setPreferredSize(new Dimension(10, 10)));
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

	public void setNorthSize(int size) {
		Dimension ori = this.get(BorderFixer.NORTH).getPreferredSize();
		this.get(BorderFixer.NORTH).setPreferredSize(new Dimension((int) ori.getWidth(), size));
	}

	public void setSouthSize(int size) {
		Dimension ori = this.get(BorderFixer.SOUTH).getPreferredSize();
		this.get(BorderFixer.SOUTH).setPreferredSize(new Dimension((int) ori.getWidth(), size));
	}

	public void setWestSize(int size) {
		Dimension ori = this.get(BorderFixer.WEST).getPreferredSize();
		this.get(BorderFixer.WEST).setPreferredSize(new Dimension(size, (int) ori.getHeight()));
	}

	public void setEastSize(int size) {
		Dimension ori = this.get(BorderFixer.EAST).getPreferredSize();
		this.get(BorderFixer.EAST).setPreferredSize(new Dimension(size, (int) ori.getHeight()));
	}

	public int getNorthSize() {
		return this.get(BorderFixer.NORTH).getPreferredSize().height;
	}

	public int getSouthSize() {
		return this.get(BorderFixer.SOUTH).getPreferredSize().height;
	}

	public int getWestSize() {
		return this.get(BorderFixer.WEST).getPreferredSize().width;
	}

	public int getEastSize() {
		return this.get(BorderFixer.EAST).getPreferredSize().width;
	}

}
