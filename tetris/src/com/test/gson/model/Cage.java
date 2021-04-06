package com.test.gson.model;

import java.util.HashMap;
import java.util.Map;

public class Cage {
	Map<String, Birds> mapBirds = new HashMap<>();// <master,pet>
	
	public Cage(Map<String, Birds> mapBirds) {
		this.mapBirds=mapBirds;
	}
	
	public void addBird(String name,Birds bird ) {
		this.mapBirds.put(name, bird);
	}
	
	public Birds getBird(String name) {
		return this.mapBirds.get(name);
	}
	
	public void showInfo() {
		this.mapBirds .forEach((x,y)->System.out.format("key=%s , value=%s\n",x,y));
	}
	
}
