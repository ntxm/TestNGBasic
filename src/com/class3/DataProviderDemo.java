package com.class3;
/*
 * Verify user is able to login into HRMS with different uID and pwd
 */

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import com.utils.CommonMethods;
import com.utils.Constants;

public class DataProviderDemo extends CommonMethods {
	
	@BeforeMethod
	public void openBrowser() {
		CommonMethods.setUp("chrome", Constants.HRM_URL);
	}
	
	@Test(dataProvider = "getData")
	public void verifyLogin(String username, String password) throws InterruptedException {
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		Thread.sleep(3000);
		driver.findElement(By.id("btnLogin")).click();
		boolean isLogoDisplayed = driver.findElement(By.id("welcome")).isDisplayed();
		Assert.assertTrue(isLogoDisplayed, "Welcome is not displayed");
	}	
	
	//to create a data provider method we need to crreate a method that will be
	//returning object 2D
	@DataProvider
	public Object[][] getData(){
		Object[][] data =  {
				{"Admin", "Hum@nhrm123"},
				{"Syntax", "Syntax123!"},
				{"SyntaxUser", "Syntax123"}
		};
		return data;
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
