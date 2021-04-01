package com.control.file;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.control.exception.FileErrorException;
import com.control.manager.SignManager;
import com.control.manager.SignManager.SignType;
import com.control.manager.getter.SignGetter;
import com.model.Sign;

public class FileManager {
	private static final String Basepath = "data";
	private static final String MainSignFileName = "mainsign.data";

	public static void loadSignDate() {
		String fname = Basepath + File.separator + MainSignFileName;
		File f = new File(fname);
		if (!f.exists()) {
			throw new FileErrorException("can't find file " + fname);
		}
		Map<SignType, SignGetter<? extends Sign>> map = new HashMap<>();
		try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis)) {
			map = (Map<SignType, SignGetter<? extends Sign>>) ois.readObject();
		} catch (EOFException e) {
			System.out.println(e.getMessage());
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		SignManager.setSignGetterMap(map);
	}

	public static void writeSignDate() {
		Map<SignType, SignGetter<? extends Sign>> map = SignManager.getSignGetterMap();
		try (FileOutputStream fos = new FileOutputStream(Basepath + File.separator + MainSignFileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(map);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
