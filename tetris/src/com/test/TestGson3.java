package com.test;

import com.google.gson.Gson;
import com.main.model.MainSign;
import com.main.model.Sign;

public class TestGson3 {

	public static void main(String[] args) {

		MainSign sign=new MainSign(2,2);
		sign.addCube(1, 2);
		
		String js=new Gson().toJson(sign,MainSign.class);
		
		System.out.println(js);
		
		MainSign newSign=new Gson().fromJson(js, MainSign.class);
		System.out.println(newSign);
		
		
	}

}
