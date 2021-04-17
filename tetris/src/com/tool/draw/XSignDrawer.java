package com.tool.draw;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.swing.JComponent;

import com.main.control.exception.FileErrorException;
import com.main.model.Sign;
import com.tool.direction.Direction;

/*
 * 用於 輸入 Sign list 時,繪製 Cube 的工具類別
 */
public class XSignDrawer {
	private SignDrawer drawer;
	private Optional<List<Sign>> backgroundSignList = Optional.empty();
	private Iterator<Sign> currentSignIt;
	private Sign currentSign;

	public XSignDrawer(JComponent comp, Direction cubesSize) {
		drawer = new SignDrawer(comp, cubesSize);
	}

	public void setBackgroundSign(List<Sign> list) {
		this.backgroundSignList = Optional.of(list);
	}

	public List<Sign> getBakcgroundSignList() {
		return this.backgroundSignList.orElseThrow(() -> new FileErrorException("Sign is null"));
	}

	public boolean hashNext() {
		if (this.currentSignIt == null) {
			this.currentSignIt = this.getBakcgroundSignList().iterator();
		}
		return currentSignIt.hasNext();
	}

	public Direction next() {
		currentSign = (Sign) currentSignIt.next();
		drawer.setSign(currentSign);
		if (drawer.hashNext()) {
			drawer.next();
		}
		return currentSign.getDirection();
	}

	public int getX() {
		return drawer.getX();
	}

	public int getY() {
		return drawer.getY();
	}

	public int getLenW() {
		return drawer.getLenW();
	}

	public int getLenH() {
		return drawer.getLenH();
	}
	
	public int getWidth() {
		return drawer.getWidth();
	}
	
	public int getHeight() {
		return drawer.getHeight();
	}

}
