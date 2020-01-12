package com.class1;

import org.testng.annotations.*;

/*
 * 
 *Task 1: Executing different test based TestNG annotations
 *
 *	Create class that will have:
 *	Before and After Class annotation
 *	Before and After Method annotation
 *	2 methods with Test annotation
 */
public class Task1 {
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("****Before class****");
	}

		
	@AfterClass
	public void afterClass() {
		System.out.println("****After class****");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("=====Before method=====");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("=====After method=====");
	}
	
	@Test
	public static void firstTest() {
		int result = 25 + 5;
		System.out.println("First Test:");
		System.out.println(result);
		System.out.println("First Test ended!");
	}
	
	@Test
	public static void secondTest() {
		int result = 25 - 5;
		System.out.println("Second Test:");
		System.out.println(result);
		System.out.println("Second Test ended!");
	}
}
