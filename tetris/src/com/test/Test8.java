package com.test;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import com.main.model.MainSign;
import com.main.model.Sign;
import com.tool.direction.Direction;

public class Test8 {

	public static void main(String[] args) throws CloneNotSupportedException {

		Task t = new Task();

		Timer tr = new Timer();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.SECOND, c.get(Calendar.SECOND) + 3);

		tr.schedule(t, c.getTime(), 300);

	}

	static class Task extends TimerTask {

		@Override
		public void run() {
			System.out.println("ttt");
		}

	}

}
