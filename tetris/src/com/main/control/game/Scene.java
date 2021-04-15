package com.main.control.game;

import java.util.HashMap;
import java.util.Map;

public class Scene {
	/*
	 * script
	 */
	static public interface Performance {
		public void start()throws Exception;

		public void update() throws Exception;
	}

	Map<String, Performance> performances = new HashMap<>();
	private boolean isInitialize = false;

	private void initialize() throws Exception {
		for (Performance p : performances.values()) {
			p.start();
		}
	}

	public void run() throws Exception{
		if (!this.isInitialize) {
			this.initialize();
			this.isInitialize = true;
		}

		for (Performance p : performances.values()) {
			p.update();
		}
	}

	/*
	 * get and set
	 */

	public void addPerformance(String name, Performance performances) {
		this.performances.put(name, performances);
	}

	public Performance getPerformance(String name) {
		return this.performances.get(name);
	}

}
