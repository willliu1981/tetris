package com.main.control.manager;

import java.util.List;

import com.main.control.SignControl;
import com.main.model.Sign;
import com.tool.direction.Direction;

/*
 * 遊戲管理
 */
public class GameManager {

	/*
	 * 以Obstacle 的 WALL 大小做為遊戲活動空間大小,於getBackgroundSize 回傳給AppManager 或 MainView
	 */
	private static Direction backgroundCubeSize = AppManager
			.getSign(AppManager.signType.OBSTACLE, AppManager.getterObstacleSignType.WALL).getDirection();

	/*
	 * 建議尺寸,實際會因為Panel 元件的設計需求計算後產生些微差距
	 */
	private final static int CUBEUNITSIZE = 30;

	private GameManager() {

	}

	/*
	 * get and set
	 */

	public static GameManager getManager() {
		return new GameManager();
	}

	/*
	 * 以Cube數量做為單位
	 */
	public static Direction getBackgroundCubeSize() {
		return backgroundCubeSize;
	}

	/*
	 * 以像素做為單位
	 */
	public static Direction getBackgroundSize() {
		Direction siez = getBackgroundCubeSize();

		return new Direction(0, 0, siez.getWidth() * CUBEUNITSIZE, siez.getHeight() * CUBEUNITSIZE);
	}

	public Sign getCurrentSign() {
		return null;
	}
	
	public List<Sign> getBackgroundSign(){
		SignControl.getControl().getMapBackground();
		
		return null;
	}

}
