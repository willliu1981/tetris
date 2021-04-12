package com.main.control.manager;

import java.awt.Dimension;

import com.main.control.exception.FileErrorException;
import com.main.control.file.FileManager;
import com.tool.direction.Direction;

public class AppManager {
	private static AppManager appManager = new AppManager();
	private static GameManager gameManage = GameManager.getManager();

	private AppManager() {
		try {
			FileManager.loadSignDate();
		} catch (FileErrorException ex) {
			this.initialize();
		}
	}

	public static AppManager getDefaultManager() {
		return appManager;
	}

	private void initialize() {
		SignManager managerMainSign = SignManager.getManager(SignManager.SignType.MAINSIGN);
		managerMainSign.createNewSign(MainSignGetter.GetterMainSginType.SIGNS);
		managerMainSign.createNewSign(MainSignGetter.GetterMainSginType.SIGNZ);
		managerMainSign.createNewSign(MainSignGetter.GetterMainSginType.SIGNT);
		managerMainSign.createNewSign(MainSignGetter.GetterMainSginType.SIGNL);
		managerMainSign.createNewSign(MainSignGetter.GetterMainSginType.SIGNJ);
		managerMainSign.createNewSign(MainSignGetter.GetterMainSginType.SIGNO);
		managerMainSign.createNewSign(MainSignGetter.GetterMainSginType.SIGNI);

		SignManager managerObstacleSign = SignManager.getManager(SignManager.SignType.OBSTACLE);
		managerObstacleSign.createNewSign(ObstacleSignGetter.GetterObstacleSignType.WALL);
	}

	public static Dimension getMainPanelSize() {
		Direction d = GameManager.getBackgroundSize();
		return new Dimension(d.getWidth(), d.getHeight());
	}
	
	/*
	 * 以Cbue數量為單位回傳 w和h 的長度
	 */
	public static Direction getCubesSize() {
		return GameManager.getBackgroundCubeSize();
	}

}
