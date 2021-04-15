package com.main.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.main.control.exception.LogicErrorException;
import com.main.control.manager.AppManager;
import com.main.model.ObstacleSign;
import com.main.model.Sign;
import com.tool.direction.Direction;
import com.tool.direction.Directions;

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
			ObstacleSign sign = (ObstacleSign) AppManager.getDefaultSign(AppManager.signType.OBSTACLE);
			sign.setPoint(d.getX(), d.getY());
			mapBackground.get(d.getY()).put(new Direction(d.getX(), d.getY()), sign);
		}

	}
	
	

	public static boolean isCollide(Sign sign) {
		boolean r = sign.getCubeMap().keySet().stream().anyMatch(cd -> {
			boolean r2 = getBackgroundMap().get(Directions.getCubeWorldPoint(sign, cd).getY()).keySet().stream()
					.anyMatch(od -> {
						return Directions.getCubeWorldPoint(sign, cd).equals(od);
					});
			return r2;
		});
		return r;
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
		List<Sign> list = null;
		try {
			list = getBackgroundMap().values().stream().reduce((x1, x2) -> {
				x1.putAll(x2);
				return x1;
			}).get().values().stream().collect(Collectors.toList());

		} catch (Exception e) {
			throw new LogicErrorException(e.getMessage());
		}

		return list;
	}
}
