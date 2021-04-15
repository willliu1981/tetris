package com.main.control.game;

import com.main.control.game.Scene.Performance;

public interface GameObjectEvent extends  Performance {
	
	public void attachScript(String name,Performance Performance);
	public void attachGameObject(String name,GameObject gb);
}
