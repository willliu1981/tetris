package com.main.control.file;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.main.control.exception.FileErrorException;
import com.main.control.file.gson.SignGetterMapWrapper;
import com.main.control.manager.SignManager;
import com.main.control.manager.SignManager.SignType;
import com.main.control.manager.getter.MainSignGetter;
import com.main.control.manager.getter.SignGetter;
import com.main.model.Sign;

public class FileManager {
	private static final String Basepath = "data";
	private static final String MainSignFileName = "mainsign.txt";
	private static String fname = Basepath + File.separator + MainSignFileName;

	public static void loadSignDate() {
		File f = new File(fname);
		if (!f.exists()) {
			throw new FileErrorException("can't find file " + fname);
		}
		Map<SignType, SignGetter<? extends Sign>> map = new HashMap<>();
		try (FileReader reader = new FileReader(f); BufferedReader br = new BufferedReader(reader)) {
			StringBuilder sb = new StringBuilder();
			while (br.ready()) {
				sb.append(br.readLine());
			}

			


		} catch (JsonSyntaxException e) {
			System.out.println(e.getMessage());
		} catch (EOFException e) {
			System.out.println(e.getMessage());
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		SignManager.setSignGetterMap(map);
	}

	public static void writeSignDate() {
		Map<SignType, SignGetter<? extends Sign>> map = SignManager.getSignGetterMap();
		try (FileWriter writer = new FileWriter(fname);) {
			String jsonStr=new SignGetterMapWrapper(map).toJson();

			writer.write(jsonStr);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	

}
