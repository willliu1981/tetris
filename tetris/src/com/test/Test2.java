package com.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.editor.sign.SignEditor;

public class Test2 {
	public static void main(String s[]) {

		Map<String, Book> map = new HashMap<>();

		map.put("a", new Book("AA","1" ,"John","2","Helen"));
		map.put("b", new Book("BB","1", "David","2","Peter"));

		System.out.println("get1:" + map.get("b"));

		// write(map,"data\\testA.data");

		Map<String, Book> map2 = new HashMap<>();
		map2 = read("data\\testA.data");

		System.out.println("get2:" + map.get("a"));

	}

	public static void write(Map<String, Book> map, String filename) {
		try (FileOutputStream fos = new FileOutputStream(filename);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(map);
		} catch (IOException ex) {

		}
	}

	public static Map<String, Book> read(String filename) {
		Map<String, Book> map = new HashMap<>();
		;
		try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis)) {
			map = (Map<String, Book>) ois.readObject();
		} catch (IOException | ClassNotFoundException ex) {

		}

		return map;
	}

	static public class Book implements java.io.Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public String name;
		public Map<String, Author> author = new HashMap<>();

		public Book(String name, String index, String author, String index2, String author2) {
			this.name = name;
			this.author.put(index, new Author(author));
			this.author.put(index2, new Author(author2));
		}

		public String toString() {
			return String.format("name=%s author1=%s author2=%s", this.name, this.author.get("1"), this.author.get("2"));
		}
	}

	static public class Author {
		public String name;

		public Author(String name) {
			this.name = name;
		}
		
		public String toString() {
			return this.name;
		}
	}
}
