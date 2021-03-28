package com.controller.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.controller.manager.keyvalue.MainSignGetter;
import com.controller.manager.keyvalue.MainSignGetter.GetterType;

public class FileManager {
	private static final String Basepath = "data";
	private static final String MainSignFileName = "mainsign.data";

	public static void loadSignDate() {
		try (FileInputStream fis = new FileInputStream(Basepath + File.separator + MainSignFileName);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			Object read;
			GetterType[] types=MainSignGetter.GetterType.values();

			
			while ((read = ois.readObject()) != null) {
				ois.readObject();
				
			}
		} catch (IOException | ClassNotFoundException ex) {

		}

	}
}
