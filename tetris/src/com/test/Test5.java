package com.test;

public class Test5 {
	public static void main(String a[]) {
		
		III car=new Car() {

			@Override
			public String testM() {
				return "aaa";
			}
			
		};
		
		car.ttt();
	}
	
	
	static class Car implements III{
		
		public void ttt() {
			this.testShow();
		}
		
		public void testShow() {
			System.out.println(testM());
		}
		
		public String testM() {
			return null;
		}
		
	}
	
	interface III{
		public void ttt();
	}
}
