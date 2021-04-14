package com.main.control.game;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import com.main.control.exception.TCastException;
import com.main.control.game.Scene.Performance;
import com.main.control.game.scene.TestGameObject;
import com.main.control.game.scene.test.TestGB;
import com.main.control.manager.AppManager;
import com.tool.TFiles;

public class SceneManager {
	private static String scenePath = "src/com/main/control/game/scene/";

	public static void main(String[] s) throws IOException, ClassNotFoundException {
		SceneManager.AddPerformances();
	}

	public static void AddPerformances() throws IOException, ClassNotFoundException {
		Set<File> files = TFiles.getFiles(scenePath);

		files.stream().forEach(x -> {
			Class<Performance> c = null;
			try {
				c = (Class<Performance>) Class.forName(TFiles.removeExtension(
						TFiles.separatorToDot(Paths.get(TFiles.root).relativize(Paths.get(x.toString())).toString(),
								TFiles.regularSeparator)));

				AppManager.getScene().addPerformance(c.getName(), c.newInstance());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassCastException e) {
				throw new TCastException(
						String.format("%s cast to %s", c.getTypeName(), Performance.class.getTypeName()));
			}
		});
		
	 	AppManager.getScene().getPerformance(TestGameObject.class.getTypeName()).start();
	 	AppManager.getScene().getPerformance(TestGameObject.class.getTypeName()).update();
	 	AppManager.getScene().getPerformance(TestGB.class.getTypeName()).start();
	 	AppManager.getScene().getPerformance(TestGB.class.getTypeName()).update();

	}
}