package com.tool.behavior;

public class BehaviorController {
	
	public static void sendBehavior(IBehavior behavior) {
		behavior.run();
	}
}
