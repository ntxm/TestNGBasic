package com.class2;

import org.openqa.selenium.By;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.utils.CommonMethods;
import com.utils.Constants;

public class Task2 extends CommonMethods {
	
	@BeforeMethod
	public void navigateTo() {
		CommonMethods.setUp("chrome", Constants.HRM_URL);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	@Test 
	public void login() throws InterruptedException {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(2000);
		//navigate to "add employee" page
		driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("Add Employee")).click();
		
		//create an object of SoftAssert claass
		SoftAssert softAlert = new SoftAssert();
		
		//verify the elements
		//Fullname
		boolean fullName = driver.findElement(By.xpath("//label[@class='hasTopFieldHelp']")).isDisplayed();
		softAlert.assertTrue(fullName, "Full name is NOT displayed");
		//Employee ID
		boolean employeeID = driver.findElement(By.xpath("//label[@for='employeeId']")).isDisplayed();
		softAlert.assertTrue(employeeID, "Employee ID is NOT displayed");
		//Photograph
		boolean photo = driver.findElement(By.xpath("//label[@for='photofile']")).isDisplayed();
		softAlert.assertTrue(photo, "Photo is NOT displayed");
		
		
		//create new employee
		String firstName = "Aeron";
		String lastName = "Booker";
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(lastName);
		//add photo to the profile
		driver.findElement(By.xpath("//input[@id='photofile']")).sendKeys("/Users/natalia/Downloads/1234.png");
		//save new employee
		driver.findElement(By.id("btnSave")).click();
		
		//Verify if employee added successfully
		String employeeName = driver.findElement(By.xpath("//div[@id='profile-pic']/h1")).getText();
		softAlert.assertEquals(employeeName, firstName + " " + lastName, "Employee is NOT created successfully");
		softAlert.assertAll();
		
		
	}

}
