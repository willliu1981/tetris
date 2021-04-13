package com.main.control.manager;

import java.awt.Dimension;

import com.main.control.exception.FileErrorException;
import com.main.control.file.FileManager;
import com.main.model.Sign;
import com.tool.direction.Direction;

public class AppManager {
	public static SignManager.SignType signType;
	public static MainSignGetter.GetterMainSginType getterMainSignType;
	public static ObstacleSignGetter.GetterObstacleSignType getterObstacleSignType;

	private static AppManager appManager = new AppManager();
	private static GameManager gameManage = GameManager.getManager();
	

	private AppManager() {
		try {
			FileManager.loadSignDate();
		} catch (FileErrorException ex) {
			System.out.println(ex.getMessage());
			this.initialize();
		}
	}

	public static AppManager getSingletonManager() {
		
		return appManager;
	}

	public static void initialize() {
		SignManager.initialize();
		
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
		managerObstacleSign.createNewSign(ObstacleSignGetter.GetterObstacleSignType.WALLCUBE);
		managerObstacleSign.createNewSign(ObstacleSignGetter.GetterObstacleSignType.REMAINS);

	}
	
	public static boolean isSignHasNewData() {
		return SignManager.isSignHasNewData();
	}
	
	public static void updateSignData() {
		SignManager.updateSignData();
		
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
	
	public static Sign getSign(SignManager.SignType signType,Enum<?> getterSignType) {
		return SignManager.getManager(signType).getSign(getterSignType);
	}
	
	public static Sign getCurrentSign() {
		return gameManage.getCurrentSign();
	}

}
