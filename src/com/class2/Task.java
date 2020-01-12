package com.class2;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.utils.CommonMethods;
import com.utils.Constants;

public class Task extends CommonMethods {
	
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
		Assert.assertTrue(isLogoDisplayed, "Logo displayed");
//		if(isLogoDisplayed) {
//			System.out.println("Test pass. Logo is displayed");
//		}else {
//			System.err.println("Test fail. Logo is not displayed");
//		}
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
	Assert.assertEquals(loginText, expectedText, "Error msg matched");
//		if(loginText.equals(expectedText)) {
//			System.out.println("Test passed");
//		}else {
//			System.out.println("Test failed");
//		}
	}

}



