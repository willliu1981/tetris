package com.main.control.game;

import java.util.HashMap;
import java.util.Map;

import com.main.control.game.Scene.Performance;

public class GameObject implements GameObjectEvent {
	protected Map<String ,Performance> mapPerformance=new HashMap<>();
	protected Map<String ,GameObject> mapGameObject=new HashMap<>();

	@Override
	public void start() {
		this.mapPerformance.values().forEach(t -> {
			try {
				t.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void update() {
		this.mapPerformance.values().forEach(t -> {
			try {
				t.update();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void attachScript(String name,Performance Performance) {
		this.mapPerformance.put(name, Performance);
	}

	@Override
	public void attachGameObject(String name,GameObject gb) {
		this.mapGameObject.put(name, gb);
		
	}

}
