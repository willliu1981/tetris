package com.test;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

import com.model.MainSign;
import com.model.Sign;

public class Test3 {

	public static void main(String[] args) {

		Map<String, Sign> map = new HashMap<>();
		map.put("A", new MainSign(2, 2));
		map.put("A", new MainSign(4, 4));

		System.out.println(map);

		Sign sign = null;
		Object o = sign;

		Sign s = (Sign) o;
		System.out.println(s);

	}

	Timer timer;
	boolean wasDoubleClick;

	public void mouseClicked(MouseEvent e) {
		System.out.println("Click at (" + e.getX() + ":" + e.getY() + ")");

		if (e.getClickCount() == 2) {
			System.out.println(" and it's a double click!");

			wasDoubleClick = true;

		} else {
			Integer timerinterval = (Integer)

			Toolkit.getDefaultToolkit().getDesktopProperty(

					"awt.multiClickInterval");

			timer = new Timer(timerinterval.intValue(), new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					if (wasDoubleClick) {
						wasDoubleClick = false; // reset flag

					} else {
						System.out.println(" and it's a simple click!");

					}

				}

			});

			timer.setRepeats(false);

			timer.start();

		}

		// 版权声明：本文为CSDN博主「小芋头君」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
		// 原文链接：https://blog.csdn.net/weixin_33277215/article/details/114219602
	}

}
