package com.main.control.game.input;

import java.util.Set;

import com.main.control.game.input.Input.KeyState;
import com.main.control.game.input.Input.KeyType;
import com.tool.behavior.Behavior;

public class ProcessUpBehavior extends Behavior {

	@Override
	public void run() {
		KeyState keyState = (KeyState) this.getParameter("keyState");
		Set<KeyType> setSingleKeyType = (Set<KeyType>) this.getParameter("setSingleKeyType");
		Set<KeyType> mapPeriodKeyType = (Set<KeyType>) this.getParameter("mapPeriodKeyType");

		setSingleKeyType.add(KeyType.UP);

	}

}
