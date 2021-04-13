package com.main.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.main.control.manager.AppManager;
import com.main.model.ObstacleSign;
import com.main.model.Sign;
import com.tool.direction.Direction;

public class SignControl {
	private static SignControl control = new SignControl();

	/*
	 * Map<行數,Map<列數,Sign>>
	 */
	private static Map<Integer, Map<Direction, Sign>> mapBackground = new HashMap<>();

	static {
		for (int i = 0; i < AppManager.getCubesSize().getHeight(); i++) {
			mapBackground.put(i, new HashMap<>());
		}
		initialize();
	}

	public static void initialize() {
		Sign wall = AppManager.getSign(AppManager.signType.OBSTACLE, AppManager.getterObstacleSignType.WALL);
		for (Direction d : wall.getCubeMap().keySet()) {
			ObstacleSign sign = new ObstacleSign(d.getX(), d.getY());
			sign.addCube(0, 0);
			mapBackground.get(d.getY()).put(new Direction(d.getX(), d.getY()), sign);
		}

	}

	public boolean isCollide(Sign sign) {

		return false;
	}

	/*
	 * get and set
	 */

	public static SignControl getControl() {
		return control;
	}

	public static Map<Integer, Map<Direction, Sign>> getBackgroundMap() {
		return mapBackground;
	}

	public static List<Sign> getBackgroundList() {
		return getBackgroundMap().values().stream().reduce((x1, x2) -> {
			x1.putAll(x2);
			return x1;
		}).get().values().stream().collect(Collectors.toList());
	}
}
