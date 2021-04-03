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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.main.control.exception.FileErrorException;
import com.main.control.manager.SignManager;
import com.main.control.manager.SignManager.SignType;
import com.main.control.manager.getter.SignGetter;
import com.model.Sign;

public class FileManager {
	private static final String Basepath = "data";
	private static final String MainSignFileName = "mainsign.txt";
	private static String fname = Basepath + File.separator + MainSignFileName;
	private static Gson g = new Gson();

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
			Type type = new TypeToken<Map<SignType, SignGetter<? extends Sign>>>() {
			}.getType();
			// Gson gb=new GsonBuilder().registerTypeAdapter(type, new HashMap<SignType,
			// SignGetter<? extends Sign>>().getClass()).create();

			map = g.fromJson(sb.toString(), type);

			System.out.println("-------------------");
			System.out.println(map);
			System.out.println(map.get(SignType.MainSign));
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
			System.out.println(map);
			writer.write( g.toJson(map));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	static class MyEnumAdapterFactory implements TypeAdapterFactory {

		@Override
		public <T> TypeAdapter<T> create(Gson arg0, TypeToken<T> arg1) {

			return null;
		}

		public class MyEnumTypeAdapter<T> extends TypeAdapter<T> {

			@Override
			public T read(JsonReader arg0) throws IOException {

				return null;
			}

			@Override
			public void write(JsonWriter arg0, T arg1) throws IOException {

			}

		}

	}

}
