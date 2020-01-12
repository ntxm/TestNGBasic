package com.class1;

import org.openqa.selenium.By;
import org.testng.annotations.*;

/*
 * using TestNG annotations automate following test cases
 *	TC 1: HRMS Application Login: 
 *	Open chrome browser
 *	Go to “http://166.62.36.207/humanresources/symfony/web/index.php/auth/login”
 *	Enter valid username and password
 *	Click on login button
 *	Then verify Syntax Logo is displayed
 *	Close the browser
 *	
 *	--------------
 * 	
 *	TC 2: HRMS Application Negative Login: 
 *
 *	Open chrome browser
 *	Go to “http://166.62.36.207/humanresources/symfony/web/index.php/auth/login”
 *	Enter valid username
 *	Leave password field empty
 *	Verify error message with text “Password cannot be empty” is displayed.
 *
 */
import com.utils.CommonMethods;
import com.utils.Constants;

public class HW1 extends CommonMethods {

	@BeforeMethod
	public void navigateTo() {
		CommonMethods.setUp("chrome", Constants.HRM_URL);
	}
	
	@AfterMethod
	public void close() {
		driver.close();
	}
	
	//verify positive scenario of login and if logo is displayed
	@Test 
	public void verifyLoginAndLogo() {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
		driver.findElement(By.id("btnLogin")).click();
		boolean isLogoDisplayed = driver.findElement(By.xpath("//div[@id='branding']//img")).isDisplayed();
		if(isLogoDisplayed) {
			System.out.println("Test pass. Logo is displayed");
		}else {
			System.err.println("Test fail. Logo is not displayed");
		}
	}
	
	//negative login scenario
	@Test
	public void verifyLoginAndMsg() {
	driver.findElement(By.id("txtUsername")).sendKeys("Admin");
	driver.findElement(By.id("txtPassword")).sendKeys("");
	driver.findElement(By.id("btnLogin")).click();
	
	//Verify login message
	String expectedText = "Password cannot be empty";
	String loginText = driver.findElement(By.xpath("//span[@id='spanMessage']")).getText();
		if(loginText.equals(expectedText)) {
			System.out.println("Test passed");
		}else {
			System.out.println("Test failed");
		}
	}

}
