package com.main.control.game;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import com.tool.TFiles;

public class SceneManager {
	private static String scenePath = "data/scene/";

	
	public static void main(String [] s) throws IOException {
		SceneManager.AddPerformances();
	}
	
	public static void AddPerformances() throws IOException {
		Set<File> files=TFiles.getFiles(scenePath);
		
		files.forEach(System.out::println);
		
		
	}
}