package com.main.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.main.control.exception.LogicErrorException;
import com.main.control.exception.TNullException;
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
	private volatile static Map<Integer, Map<Direction, Sign>> mapBackground = new HashMap<>();
	private static Map<Direction, Sign> defaultUnitObstacleMap;// 單位牆壁,可用於修補牆壁

	static {
		for (int i = 0; i < AppManager.getCubesSize().getHeight(); i++) {
			mapBackground.put(i, new HashMap<>());
		}
		initialize();
	}

	public static void initialize() {
		Sign wall = AppManager.getSign(AppManager.signType.OBSTACLE, AppManager.getterObstacleSignType.WALL);
		SignMapToBackGround(wall);
		defaultUnitObstacleMap = mapBackground.get(0);
		/*
		 * 預製最頂層的障礙物,即最上層牆壁
		 */
		mapBackground.get(0).forEach((k, v) -> {
			try {
				defaultUnitObstacleMap.put(k.clone(), v.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		});
	}

	public static void SignMapToBackGround(Sign sign) {
		for (Direction sd : sign.getCubeMap().keySet()) {
			ObstacleSign obst = (ObstacleSign) AppManager.getDefaultSign(AppManager.signType.OBSTACLE);
			Direction od = Directions.getCubeWorldPoint(sign, sd);
			obst.setPoint(od.getX(), od.getY());
			mapBackground.get(od.getY()).put(od, obst);
		}
	}

	public static int bingo() {
		int count = 0;
		out: for (int v = mapBackground.size() - 2, p = v; v >= 0; v--) {
			try {
				if (p < 0) {
					break out;
				}
				while (mapBackground.get(p).size() >= AppManager.getCubesSize().getWidth()) {
					p--;
					count++;
				}
			} catch (NullPointerException e) {
				throw new TNullException(e.getMessage() + " : " + p);
			}

			fallOffset(p, v);

			p--;
		}

		for (int i = count - 1; i >= 0; i--) {
			mapBackground.put(0, getDefaultUnitObstacleMap());
			fallOffset(0, i);
		}

		return count;

	}
	
	public static void clear() {
		for(int i=mapBackground.size()-2;i>=0;i--) {
			mapBackground.put(0, getDefaultUnitObstacleMap());
			fallOffset(0, i);
		}
	}

	public static boolean checkExist(int line) {
		return mapBackground.get(line).size() > defaultUnitObstacleMap.size();
	}

	private static void fallOffset(int ori, int des) {
		Map<Direction, Sign> signMap = new HashMap<>();
		mapBackground.get(ori).keySet().stream().forEach(x -> {
			Sign sign = null;
			sign = mapBackground.get(ori).get(x);
			sign.setPoint(sign.getX(), des);
			signMap.put(new Direction(x.getX(), des), sign);
		});
		mapBackground.put(des, signMap);
	}

	public static boolean isCollide(Sign sign) {
		boolean r = sign.getCubeMap().keySet().stream().anyMatch(cd -> {
			Map<Direction, Sign> mapSign = getBackgroundMap().get(Directions.getCubeWorldPoint(sign, cd).getY());
			if (mapSign == null) {
				return false;
			}
			boolean r2 = mapSign.keySet().stream().anyMatch(od -> {
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
		Map<Direction, Sign> map = new HashMap<>();
		try {
			list = getBackgroundMap().values().stream().reduce(map, (x1, x2) -> {
				x1.putAll(x2);
				return x1;
			}).values().stream().collect(Collectors.toList());
		} catch (Exception e) {
			throw new LogicErrorException(e.getMessage());
		}

		return list;
	}

	private static Map<Direction, Sign> getDefaultUnitObstacleMap() {
		return defaultUnitObstacleMap.entrySet().stream().collect(Collectors.toMap(entry -> {
			try {
				return entry.getKey().clone();
			} catch (CloneNotSupportedException ex) {
				ex.printStackTrace();
			}
			return null;
		}, entry -> {
			try {
				return entry.getValue().clone();
			} catch (CloneNotSupportedException ex) {
				ex.printStackTrace();
			}
			return null;
		}));
	}
}
