package com.class3;

import org.testng.annotations.*;

public class AlwaysRun {
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		System.out.println("I am before Suite");
	}
	
	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
		System.out.println("I am before Test");
	}
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		System.out.println("I am before class");
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		System.out.println("I am before method");
	}
	
	
	
	@Test
	public void testMethod() {
		System.out.println("I am test");
	}
	
	@Test
	public void testMethodTwo() {
		System.out.println("I am test two");
	}
	

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		System.out.println("I am after method");
	}
	
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.println("I am after class");
	}
	
	

}
