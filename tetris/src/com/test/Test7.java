package com.test;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.google.gson.reflect.TypeToken;
import com.main.control.manager.SignManager.SignType;

public class Test7 {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("cc", "CCC");
		map.put("aa", "AAA");
		map.put("bb", "BBB");
		
		List<String> newList=(List<String>) Arrays.asList(new String[]{"cc","dd"});

	
		System.out.println(map.keySet().containsAll(newList));
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
