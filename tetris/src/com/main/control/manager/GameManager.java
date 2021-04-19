package com.main.control.manager;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Stream;

import javax.swing.JPanel;

import com.main.control.SignControl;
import com.main.control.exception.TNullException;
import com.main.control.file.FileManager;
import com.main.control.game.GameObject;
import com.main.control.game.Scene;
import com.main.control.game.SceneManager;
import com.main.control.game.scene.InputControlScript;
import com.main.control.game.scene.SignControlScript;
import com.main.control.signgetter.MainSignGetter.GetterMainSginType;
import com.main.model.Sign;
import com.main.view.MainView;
import com.tool.direction.Direction;

/*
 * 遊戲管理
 */
public class GameManager {
	static class MainTask extends TimerTask {

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
	private static final Direction currentSignStartPoint = new Direction(6, 1);
	private static final Direction nextSignStartPoint = new Direction(2, 2);
	private static int interval = 20;
	private static Sign currentSign;
	private static Sign nextSign;
	private static int score;
	private static int highScore;
	private static boolean gameOver = false;
	private static int stackMaxLineNumber = 2;// 方塊堆到最高點位置

	/*
	 * 以Obstacle 的 WALL 大小做為遊戲活動空間大小,於getBackgroundSize 回傳給AppManager 或 MainView
	 */
	private static Direction backgroundCubeSize = AppManager
			.getSign(AppManager.signType.OBSTACLE, AppManager.getterObstacleSignType.WALL).getDirection();

	/*
	 * 建議尺寸,實際會因為Panel 元件的設計需求計算後產生些微差距
	 */
	private final static int CUBEUNITSIZE = 30;
	private Timer timer;
	private static boolean pause = false;

	private GameManager() {

	}

	public void initialize() {
		GameObject mainGameObject = new GameObject();
		mainGameObject.attachScript("InputControl", new InputControlScript());
		mainGameObject.attachScript("SignControlScript", new SignControlScript());

		SceneManager.getScene().addPerformance("mainGameObject", mainGameObject);
		FileManager.loadRecord();

	}

	public void start() {
		timer = new Timer();
		timer.schedule(new MainTask(), 1000, interval);
	}

	public void pause() {
		stop();
		pause = true;
	}

	public void stop() {
		timer.cancel();
	}

	public static void reset() {
		SignControl.clear();
		gameOver(false);
		setScore(0);
	}

	public static void SignMapToBackGround(Sign sign) {
		SignControl.SignMapToBackGround(sign);
	}

	public static int bingo() {
		return SignControl.bingo();
	}

	/*
	 * get and set
	 */

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		GameManager.score = score;
	}

	public static int getHighScore() {
		return highScore;
	}

	public static void setHighScore(int score) {
		highScore = score;
	}

	public static boolean isPause() {
		return pause;
	}

	public static boolean isCurrentSignCollide() {
		return SignControl.isCollide(getCurrentSign());
	}

	public static boolean isCollide(Sign sign) {
		return SignControl.isCollide(sign);
	}

	public static boolean isStackMaxLimit() {
		return SignControl.checkExist(stackMaxLineNumber);
	}

	public static void gameOver() {
		gameOver(true);
	}

	public static void gameOver(boolean b) {
		gameOver = b;
	}

	public static boolean isGameOver() {
		return gameOver;
	}

	public static GameManager getSingletonManager() {
		return manager;
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

	public static Sign setCurrentSign() {
		currentSign = getNextSign();
		try {
			if (currentSign == null) {
				setNextSign();
				currentSign = getNextSign();
			}

		} catch (NullPointerException e) {
			throw new TNullException(e.getMessage());
		}
		currentSign.setPoint(currentSignStartPoint.getX(), currentSignStartPoint.getY());
		setNextSign();
		return currentSign;
	}

	public static Sign getCurrentSign() {
		return currentSign;
	}

	private static void setNextSign() {
		GetterMainSginType[] types = GetterMainSginType.values();
		GetterMainSginType type = new Random().ints(1, 0, types.length).mapToObj(x -> types[x]).findFirst().get();
		try {
			nextSign = AppManager.getSign(AppManager.signType.MAINSIGN, type).clone();
			nextSign.setPoint(nextSignStartPoint.getX(), nextSignStartPoint.getY());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	public static Sign getNextSign() {
		return nextSign;
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
