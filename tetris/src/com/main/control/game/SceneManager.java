package com.main.control.game;

import java.io.File;

public class SceneManager {
	private static String scenePath = "com/main/control/game/scene";

	
	public static void main(String [] s) {
		SceneManager.AddPerformances();
	}
	
	public static void AddPerformances() {
		File file = new File(scenePath);
		String[] paths = file.list();

		System.out.println("rrr "+paths);
	}
}