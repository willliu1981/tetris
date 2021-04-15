package com.main.control.game;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import com.tool.behavior.Behavior;
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

					if (!(keyEvent == null || keyState == null)) {
						boolean r = false;
						switch (keyEvent.getKeyCode()) {
						case 37:// left
							setSingleKeyType.add(KeyType.LEFT);
							r = (keyState == KeyState.keyPressed)
									? mapPeriodKeyType.add(KeyType.LEFT) || mapPeriodKeyType.remove(KeyType.RIGHT)
									: mapPeriodKeyType.remove(KeyType.LEFT);
							break;
						case 38:// up
							setSingleKeyType.add(KeyType.UP);
							break;
						case 39:// right
							setSingleKeyType.add(KeyType.RIGHT);
							r = (keyState == KeyState.keyPressed)
									? mapPeriodKeyType.add(KeyType.RIGHT) || mapPeriodKeyType.remove(KeyType.LEFT)
									: mapPeriodKeyType.remove(KeyType.RIGHT);
							break;
						case 40:// down
							setSingleKeyType.add(KeyType.DOWN);
							r = (keyState == KeyState.keyPressed) ? mapPeriodKeyType.add(KeyType.DOWN)
									: mapPeriodKeyType.remove(KeyType.DOWN);
							break;

						default:
							break;
						}
					}
					System.out.println("input single ** ");
					setSingleKeyType.forEach(x-> System.out.print(x+" "));
					System.out.println();
					System.out.println("input period ** ");
					mapPeriodKeyType.forEach(x-> System.out.print(x+" "));
					System.out.println();

				}
			};
		}
		return behavior;
	}

	public void setParameter(String name, Object obj) {
		this.getBehavior().setParameter(name, obj);
	}

	public Object getParameter(String name) {
		return this.getBehavior().getParameter(name);
	}

}
