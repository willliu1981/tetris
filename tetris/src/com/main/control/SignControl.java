package com.main.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.main.control.manager.AppManager;
import com.main.model.Sign;
import com.tool.direction.Direction;

public class SignControl {
	private SignControl control=new SignControl();
	
	/*
	 * Map<行數,Map<列數,Sign>>
	 */
	private static Map<Integer, Map<Integer, Sign>> mapBackground = new HashMap<>();
	
	static {
		
		for(int i=0;i<AppManager.getCubesSize().getHeight();i++) {
			mapBackground.put(i, new HashMap<>());
		}
	}
	
	public static void initialize() {
		Sign walls=AppManager.getSign(AppManager.signType.OBSTACLE, AppManager.getterObstacleSignType.WALL);
		Sign wallCube=AppManager.getSign(AppManager.signType.OBSTACLE, AppManager.getterObstacleSignType.WALLCUBE);
		for(Direction d :walls.getCubeMap().keySet()) {
			mapBackground.get(d.getHeight()).put(d.getWidth(), wallCube);
		}
		
	}
	
	
	public boolean isCollide(Sign sign) {
		
		return false;
	}
	
	
}
