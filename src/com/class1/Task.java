package com.class1;

import org.testng.annotations.Test;

public class Task {
	
	@Test
	public static void testAddition () {
			int result = 2 + 5;
			System.out.println(result);
	}
	
	@Test
	public static void testDivision() {
		int result = 10 / 2;
		System.out.println(result);
		
	}
	
	
	@Test
	public static void testMult() {
		int result = 20 * 2;
		System.out.println(result);
		
	}
	
	

}
