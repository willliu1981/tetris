package com.main.control.game.scene;

import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.main.control.SignControl;
import com.main.control.game.Scene.Performance;
import com.main.control.manager.AppManager;
import com.main.control.manager.GameManager;
import com.main.model.Sign;
import com.main.view.MainView;

public class SignControlScript implements Performance {
	private static int interval = 360;
	private long time;
	private int addScore = 100;

	@Override
	public void start() throws Exception {
		System.out.println("start: SignControlScript");
	}

	@Override
	public void update() throws Exception {
		if (new Date().getTime() > time) {
			if (!AppManager.isGameOver()) {
				Sign sign = AppManager.getCurrentSign();
				Sign pioneer = sign.clone();
				pioneer.setPoint(pioneer.getX(), pioneer.getY() + 1);
				if (!GameManager.isCollide(pioneer)) {
					sign.setPoint(sign.getX(), sign.getY() + 1);
				} else {
					AppManager.getNewCurrentSign();
					GameManager.SignMapToBackGround(sign);
					int count = GameManager.bingo();
					/*
					 * 計算分數
					 */
					if (count > 0) {
						GameManager.setScore(GameManager.getScore() + count * addScore);
						if (GameManager.getScore() > GameManager.getHighScore()) {
							GameManager.setHighScore(GameManager.getScore());
						}
					}

					if (GameManager.isStackMaxLimit()) {
						GameManager.gameOver();
					}

					JPanel nextPanel = (JPanel) MainView.getSession().getAttribute("nextPanel");
					nextPanel.repaint();
				}
				time = new Date().getTime() + interval;
			}
		}
		JLabel lblNewLabel_score = (JLabel) MainView.getSession().getAttribute("lblNewLabel_score");
		lblNewLabel_score.setText("" + GameManager.getScore());

		JLabel lblNewLabel_highscore = (JLabel) MainView.getSession().getAttribute("lblNewLabel_highscore");
		lblNewLabel_highscore.setText("" + GameManager.getHighScore());

		JPanel main_panel = (JPanel) MainView.getSession().getAttribute("main_panel");
		main_panel.repaint();
	}

}
