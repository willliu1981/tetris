package com.controller.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.controller.manager.SignManager;
import com.controller.manager.SignManager.SignType;
import com.controller.manager.keyvalue.SignGetter;
import com.model.Sign;

public class FileManager {
	private static final String Basepath = "data";
	private static final String MainSignFileName = "mainsign.data";

	public static void loadSignDate() {
		Map<SignType, SignGetter<? extends Sign>> map = new HashMap<>();
		try (FileInputStream fis = new FileInputStream(Basepath + File.separator + MainSignFileName);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			map = (Map<SignType, SignGetter<? extends Sign>>) ois.readObject();
		} catch (IOException | ClassNotFoundException ex) {

		}
		
		SignManager.setSignKVMap(map);

	}
	
	public static void writeSignDate() {
		Map<SignType, SignGetter<? extends Sign>> map = SignManager.getSignKVMap();
		try (FileOutputStream fos = new FileOutputStream(Basepath + File.separator + MainSignFileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(map);
		} catch (IOException ex) {
			
		}
		
		
	}
}