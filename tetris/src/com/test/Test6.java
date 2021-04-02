package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;



public class Test6 {
	static Map<TT, Car> map = new HashMap<>();

	public static void main(String[] args) {
		String fname = "data\\testB.data";
		Car c=new Car();
		//c.name="pp";
		map.put(TT.T1, c);

		load(fname);
		//write(fname);
		 
		c=map.get(TT.T1);
		 System.out.println(c.name);

	}

	static void load(String fname) {
		try (FileInputStream fs = new FileInputStream(fname); ObjectInputStream os = new ObjectInputStream(fs)) {
			map=(Map<TT, Car>) os.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	static void write(String fname) {
		try (FileOutputStream fs = new FileOutputStream(fname); ObjectOutputStream os = new ObjectOutputStream(fs)) {
			os.writeObject(map);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static class Car implements java.io.Serializable {
		/**
		 * 
		 */
//		static enum TT{
//			T1, T2
//		}
		
		private static final long serialVersionUID = 1L;
		String name;
	}

	static enum TT{
		T1, T2
	}

}
