package com.test.gson.control.deserializer;

public class DeserializerFactory {
	private static  DeserializerFactory deserializerFactory=new DeserializerFactory();
	public final static String Default = "default";
	public final static String Enum = "Enum";
	public final static String Birds = "Birds";
	public final static String Map_String_Birds = "Map<String,Birds>";


	public static DeserializerFactory initializeGson() {

		return deserializerFactory;
	}
	


	/*
	 * get and set
	 */



}
