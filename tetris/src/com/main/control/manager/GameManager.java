package com.main.control.manager;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.main.control.SignControl;
import com.main.control.game.GameObject;
import com.main.control.game.Scene;
import com.main.control.game.SceneManager;
import com.main.control.game.scene.TestGameObject;
import com.main.model.Sign;
import com.tool.direction.Direction;

/*
 * 遊戲管理
 */
public class GameManager {
	static class Task extends TimerTask {

		@Override
		public void run() {
			try {
				SceneManager.getScene().run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private static GameManager manager = new GameManager();
	private static int interval = 20;

	/*
	 * 以Obstacle 的 WALL 大小做為遊戲活動空間大小,於getBackgroundSize 回傳給AppManager 或 MainView
	 */
	private static Direction backgroundCubeSize = AppManager
			.getSign(AppManager.signType.OBSTACLE, AppManager.getterObstacleSignType.WALL).getDirection();

	/*
	 * 建議尺寸,實際會因為Panel 元件的設計需求計算後產生些微差距
	 */
	private final static int CUBEUNITSIZE = 30;

	private final Timer timer = new Timer();

	private GameManager() {

	}

	public void initialize() {
		GameObject mainGB = new GameObject();
		mainGB.attachScript("test", new TestGameObject());

		SceneManager.getScene().addPerformance("gameObject", mainGB);

		timer.schedule(new Task(), 1000, interval);
	}

	/*
	 * get and set
	 */

	public static GameManager getSingletonManager() {
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

	public static boolean isCurrentSignCollide() {	
		return SignControl.isCollide(getCurrentSign());
	}
	
	 public static boolean isCurrentSignCollide(Sign pioneer) {
		 return SignControl.isCollide(pioneer);
	 }
	
	

	public static Sign getCurrentSign() {
		/*
		 * test
		 */
		return AppManager.getSign(AppManager.signType.MAINSIGN, AppManager.getterMainSignType.SIGNJ);
	}

	public static List<Sign> getBackgroundSignList() {
		return SignControl.getBackgroundList();
	}

	public static int getInterval() {
		return interval;
	}

	public static void setInterval(int interval) {
		GameManager.interval = interval;
	}

}
