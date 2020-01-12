package com.class2;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.utils.CommonMethods;
import com.utils.Constants;

public class SortAssertDemo extends CommonMethods {

	@BeforeMethod
	public void navigateTo() {
		CommonMethods.setUp("chrome", Constants.HRM_URL);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	@Test 
	public void loginAndLogo() {
		//Verify if logo is displayed
		boolean isDisplayed = driver.findElement(By.xpath("//div[@id='divLogo']/img")).isDisplayed();
		isDisplayed = false;
		//Hard Assert will fail and execution will stop at that point
		//Assert.assertTrue(isDisplayed, "Logo is NOT displayed");
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isDisplayed, "Logo is not displayed");
		
		//login
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
		driver.findElement(By.id("btnLogin")).click();
		
		boolean welcomeDisplayed = driver.findElement(By.id("welcome")).isDisplayed();
		//Assert.assertTrue(welcomeDisplayed);
		softAssert.assertTrue(welcomeDisplayed, "Welcome element is NOT displayed");
		softAssert.assertAll();
	}
}
