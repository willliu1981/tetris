package com.main.control.manager;

import java.util.HashMap;
import java.util.Map;

import com.main.model.Sign;
import com.tool.direction.Direction;

/*
 * 遊戲管理
 */
public class GameManager {

	private Map<Direction,Sign> mapBackground=new HashMap<>();
	private Direction backgroundSize=new Direction();
	
	private GameManager() {
		
	}
	
	
	
	
	/*
	 * get and set
	 */
	
	public static GameManager getManager() {
		return new GameManager();
	}
	
}
