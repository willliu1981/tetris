package com.test;

import java.util.HashMap;
import java.util.Map;

import com.model.MainSign;
import com.model.Sign;

public class Test3 {

	public static void main(String[] args) {

		Map<String ,Sign> map=new HashMap<>();
		map.put("A", new MainSign(2,2));

		System.out.println(map);
		Sign sign=map.remove ("A");
		System.out.println(sign==null);
		System.out.println(map);
		
		
	}

}
