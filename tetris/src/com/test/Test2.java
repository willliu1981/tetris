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
import java.util.Optional;

import com.editor.sign.view.SignEditor;

public class Test2 {
	public static void main(String s[]) {

		Map<String, Book> map = new HashMap<>();

		map.put("a", new Book("AA", "John", "Helen"));
		map.put("b", new Book("BB", "David", "Peter"));

		System.out.println("get1:" + map.get("b"));

		 write(map,"data\\testA.data");

		Map<String, Book> map2 = new HashMap<>();
		map2 = read("data\\testA.data");

		System.out.println("get2:" + map2.get("a"));

	}

	public static void write(Map<String, Book> map, String filename) {
		try (FileOutputStream fos = new FileOutputStream(filename);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(map);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static Map<String, Book> read(String filename) {
		Map<String, Book> map = new HashMap<>();
		;
		try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis)) {
			map = (Map<String, Book>) ois.readObject();
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return map;
	}

	static public class Book implements java.io.Serializable {
		public enum AuthorIndex {
			Index1, Index2
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Optional< String> name=Optional.empty();
		public Map<Enum, Author> author = new HashMap<>();

		public Book(String name, String author, String author2) {
			this.name = Optional.ofNullable(name);
			this.author.put(AuthorIndex.Index1, new Author(author));
			this.author.put(AuthorIndex.Index2, new Author(author2));
		}
		
		public String getName() {
			return this.name.orElse("no name");
		}

		public String toString() {
			return String.format("name=%s author1=%s author2=%s", this.getName(), this.author.get(AuthorIndex.Index1),
					this.author.get(AuthorIndex.Index2));
		}
	}

	static public class Author implements java.io.Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public String name;

		public Author(String name) {
			this.name = name;
		}

		


		public String toString() {
			return this.name;
		}
	}
}
