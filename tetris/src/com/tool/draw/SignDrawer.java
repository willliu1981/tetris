package com.tool.draw;

import java.awt.Component;
import java.awt.Insets;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import javax.swing.JComponent;

import com.main.control.exception.FileErrorException;
import com.main.model.Sign;
import com.tool.direction.Direction;

public class SignDrawer {
	private int width;
	private int height;
	private int lenW;
	private int lenH;
	private Optional<Sign> sign = Optional.empty();
	private Optional<Map<Integer, Map<Integer, Sign>>> backgroundSign = Optional.empty();
	private Iterator currentIt;
	private Direction currentDirectoin;

	/*
	 * 修正後的 X和Y
	 */
	private int fixedX;
	private int fixedY;

	public SignDrawer(JComponent comp, Direction cubesSize) {
		this(comp, comp.getInsets(), cubesSize);
	}

	public SignDrawer(JComponent comp, Insets inset, Direction cubesSize) {
		int gapW = 0, gapH = 0;
		if (inset != null) {
			gapW = inset.left + inset.right;
			gapH = inset.top + inset.bottom;
		}
		this.width = comp.getWidth() - gapW;
		this.height = comp.getHeight() - gapH;
		this.lenW = this.width / cubesSize.getWidth();
		this.lenH = this.height / cubesSize.getHeight();
		this.fixedX = (this.width - cubesSize.getWidth() * lenW) / 2;
		this.fixedY = (this.height - cubesSize.getHeight() * lenH) / 2;
	}

	public boolean hashNext() {
		if (this.currentIt == null) {
			this.currentIt = this.getSign().getCubeMap().keySet().iterator();
		}
		return currentIt.hasNext();
	}

	public Direction next() {
		return currentDirectoin = (Direction) currentIt.next();
	}

	/*
	 * get and set
	 */

	/*
	 * 用於取得 Sign 的 left-top-point
	 */
	public void setSign(Sign s) {
		this.sign = Optional.of(s);
	}

	private Sign getSign() {
		return this.sign.orElseThrow(() -> new FileErrorException("Sign is null"));
	}

	public void setBackgroundSign(Map<Integer, Map<Integer, Sign>> map) {

	}

	public Map<Integer, Map<Integer, Sign>> getBakcgroundSign() {
		return this.backgroundSign.orElseThrow(() -> new FileErrorException("Sign is null"));
	}

	/*
	 * 回傳修正後的置中位置的欲繪製Cube的座標
	 */
	public int getX(Direction d) {
		return this.getFixedX() + (d.getX() + getSign().getLTX()) * this.getLenW();
	}

	public int getY(Direction d) {
		return this.getFixedY() + (d.getY() + getSign().getLTY()) * this.getLenH();
	}

	public int getX() {
		return this.getX(this.currentDirectoin);
	}

	public int getY() {
		return this.getY(this.currentDirectoin);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getLenW() {
		return lenW;
	}

	public int getLenH() {
		return lenH;
	}

	public int getFixedX() {
		return fixedX;
	}

	public int getFixedY() {
		return fixedY;
	}

}
