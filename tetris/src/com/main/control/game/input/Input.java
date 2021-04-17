package com.main.control.game.input;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.tool.behavior.Behavior;
import com.tool.behavior.BehaviorController;
import com.tool.behavior.IBehavior;

/*
 * 遊戲進行所輸入的指令的控制器
 */
public class Input implements IBehavior {
	static public enum KeyType {
		UP, LEFT, RIGHT, DOWN
	}

	static public enum KeyState {
		keyPressed, keyReleased
	}

	private Behavior behavior;
	private static Set<KeyType> setSingleKeyType = new HashSet<>();
	private static Set<KeyType> mapPeriodKeyType = new HashSet<>();
	private static Map<KeyType, Behavior> mapProcess = new HashMap<>();// <keyCode,process behavior>
	private static Map<Integer, KeyType> mapKeyType = new HashMap<>();// <keyCode,process behavior>

	static {
		addProcess(KeyType.LEFT, new ProcessLeftBehavior());
		addProcess(KeyType.UP, new ProcessUpBehavior());
		addProcess(KeyType.RIGHT, new ProcessRightBehavior());
		addProcess(KeyType.DOWN, new ProcessDownBehavior());
	}

	@Override
	public void run() {
		this.getBehavior().run();

	}

	/*
	 * get and set
	 */

	public Behavior getBehavior() {
		if (this.behavior == null) {
			this.behavior = new Behavior() {

				@Override
				public void run() {
					KeyEvent keyEvent = (KeyEvent) this.getParameter("event");
					KeyState keyState = (KeyState) this.getParameter("state");

					try {
						Behavior behavior = getProcess(getKeyType(keyEvent.getKeyCode()));
						behavior.setParameter("keyState", keyState);
						behavior.setParameter("setSingleKeyType", setSingleKeyType);
						behavior.setParameter("mapPeriodKeyType", mapPeriodKeyType);
						BehaviorController.sendBehavior(behavior);
					} catch (NullPointerException e) {
						System.out.println(e.getMessage()+" : KeyEvent or KeyState is null");
					}
				}
			};
		}
		return behavior;
	}

	private static void addProcess(KeyType keyType, Behavior behavior) {
		mapProcess.put(keyType, behavior);
	}

	private static Behavior getProcess(KeyType keyType) {
		return mapProcess.get(keyType);
	}

	public static void addKeyCode(int keyCode, KeyType keyType) {
		mapKeyType.put(keyCode, keyType);
	}

	private static KeyType getKeyType(int keyCode) {
		return mapKeyType.get(keyCode);
	}

	public void setParameter(String name, Object obj) {
		this.getBehavior().setParameter(name, obj);
	}

	public Object getParameter(String name) {
		return this.getBehavior().getParameter(name);
	}

	public static int getHorizontal() {
		if (mapPeriodKeyType.contains(KeyType.LEFT)) {
			return -1;
		} else if (mapPeriodKeyType.contains(KeyType.RIGHT)) {
			return 1;
		}
		return 0;
	}

	public static int getVertical() {
		if (mapPeriodKeyType.contains(KeyType.DOWN)) {
			return 1;
		}
		return 0;
	}

	public static boolean getRotate() {
		if (mapPeriodKeyType.contains(KeyType.UP)) {
			return true;
		}
		return false;
	}

}
