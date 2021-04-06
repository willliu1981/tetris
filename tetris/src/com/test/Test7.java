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
		map.put(null, null);
		map.put(null, "xxx");
		
		
		Type t= new TypeToken<Map<String,Integer>>(){}.getType();
		
		//AA<Map<Integer, Double>> a=new AA<Map<Integer,Double>>();
		//System.out.println(a.token .getType().getTypeName());
		
		//TypeToken token=new TypeToken<String>() {};
		
		AA<String> a=new AA<String>();
	
		System.out.println(a.token.getType().getTypeName());
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
