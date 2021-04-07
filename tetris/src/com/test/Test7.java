package com.test;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.google.gson.reflect.TypeToken;
import com.main.control.manager.SignManager.SignType;

public class Test7 {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("cc", "CCC");

		
	
		System.out.println(map.get("ccc"));
	}
	
	static class AA<T>{
		Map<String,Integer> map=new HashMap<>();
		TypeToken<T> token =new TypeToken<T>() {};
		
		<R>TypeToken getToken(R s){
			s.getClass();
			
		 return new TypeToken() {};
		}
	
	}
}
