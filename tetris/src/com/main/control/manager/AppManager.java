package com.main.control.manager;

import java.awt.Dimension;
import java.util.List;

import com.main.control.exception.FileErrorException;
import com.main.control.file.FileManager;
import com.main.control.game.Scene;
import com.main.control.game.input.Input;
import com.main.control.game.input.Input.KeyType;
import com.main.control.manager.SignManager.SignType;
import com.main.control.signgetter.MainSignGetter;
import com.main.control.signgetter.ObstacleSignGetter;
import com.main.model.Sign;
import com.tool.direction.Direction;

public class AppManager {
	public static SignManager.SignType signType;
	public static MainSignGetter.GetterMainSginType getterMainSignType;
	public static ObstacleSignGetter.GetterObstacleSignType getterObstacleSignType;

	private static AppManager appManager = new AppManager();
	private static GameManager gameManage = GameManager.getSingletonManager();

	private AppManager() {
		try {
			FileManager.loadSignDate();
		} catch (FileErrorException ex) {
			System.out.println(ex.getMessage());
			AppManager.initializeSign();
		}
	}

	/*
	 * 用於啟動 建構式初始化
	 */
	public static AppManager getStart() {

		return appManager;
	}

	public static void initializeSign() {
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

	public static void initialize() {

		/*
		 * Input initialize
		 */
		Input.addKeyCode(37, KeyType.LEFT);
		Input.addKeyCode(38, KeyType.UP);
		Input.addKeyCode(39, KeyType.RIGHT);
		Input.addKeyCode(40, KeyType.DOWN);

	}

	public static void start() {
		initialize();
		getNewCurrentSign();
		GameManager.getSingletonManager().initialize();

	}

	public static boolean isSignHasNewData() {
		return SignManager.isSignHasNewData();
	}

	public static void updateSignData() {
		SignManager.updateSignData();

	}

	public static boolean isCurrentSignCollide() {
		return GameManager.isCurrentSignCollide();
	}

	/*
	 * get and set
	 */
	
	public static void gameOver() {
		gameManage.gameOver();
	}

	public static Dimension getMainPanelSize() {
		Direction d = GameManager.getBackgroundSize();
		return new Dimension(d.getWidth(), d.getHeight());
	}

	public static void removeSignGetter(SignType signType) {
		SignManager.getManager(signType).removeSignGetter();
	}

	public static void removeSign(SignType signType, Enum<?> type) {
		SignManager.getManager(signType).removeSign(type);
	}

	/*
	 * 以Cbue數量為單位回傳 w和h 的長度
	 */
	public static Direction getCubesSize() {
		return GameManager.getBackgroundCubeSize();
	}

	public static Sign getSign(SignManager.SignType signType, Enum<?> getterSignType) {
		return SignManager.getManager(signType).getSign(getterSignType);
	}

	public static Sign getDefaultSign(SignManager.SignType signType) {
		return SignManager.getManager(signType).getDefaultSign();
	}

	public static Sign getCurrentSign() {
		return gameManage.getCurrentSign();
	}

	public static List<Sign> getBackgroundSignList() {
		return GameManager.getBackgroundSignList();
	}

	public static Sign getNewCurrentSign() {
		gameManage.setCurrentSign();
		return gameManage.getCurrentSign();
	}

	public static Sign getNextSign() {
		return gameManage.getNextSign();
	}

	public static boolean isGameOver() {
		return gameManage.isGameOver();
	}
}
