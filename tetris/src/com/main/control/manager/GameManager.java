package com.main.control.manager;

import java.util.HashMap;
import java.util.Map;

import com.main.model.Sign;
import com.tool.direction.Direction;

/*
 * 遊戲管理
 */
public class GameManager {

	private Map<Direction, Sign> mapBackground = new HashMap<>();
	
	/*
	 * 以Obstacle 的 WALL 大小做為遊戲活動空間大小,於getBackgroundSize 回傳給AppManager 或 MainView
	 */
	private static Direction backgroundCubeSize = SignManager.getManager(SignManager.SignType.OBSTACLE)
			.getSign(ObstacleSignGetter.GetterObstacleSignType.WALL).getDirection();

	private final static int CUBEUNITSIZE = 30;

//	SignManager managerObstacleSign = SignManager.getManager(SignManager.SignType.OBSTACLE);
//	managerObstacleSign.createNewSign(ObstacleSignGetter.GetterObstacleSignType.WALL);

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
	public static  Direction getBackgroundCubeSize() {
		return backgroundCubeSize;
	}

	/*
	 * 以像素做為單位
	 */
	public static  Direction getBackgroundSize() {
		Direction bcSize = getBackgroundCubeSize();

		return new Direction(0, 0, bcSize.getWidth() * CUBEUNITSIZE, bcSize.getHeight() * CUBEUNITSIZE);
	}

//	public void test() {
//		System.out.println("game manager ** "+this.backgroundCubeSize);
//	}

}
