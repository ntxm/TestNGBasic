package com.class2;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.utils.CommonMethods;
import com.utils.Constants;

public class AssertionsDemo extends CommonMethods{
	
	@BeforeMethod
	public void openAndNavigate() {
		setUp("chrome", Constants.HRM_URL);
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
	
	@Test(priority = 1, enabled = true)
	public void titleValidation() {
		String expectedTitle = "Human Management Systems";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Title are matched");
		
//		if(actualTitle.equals(expectedTitle)) {
//			System.out.println("test pass");
//		}else {
//			System.out.println("test fails");
//		}
	}
	
	@Test(priority = 2,enabled = true)
	public void logoValidation() {
		boolean isLogoDisplayed = driver.findElement(By.xpath("//div[@id='divLogo']/img")).isDisplayed();
		
		Assert.assertTrue(isLogoDisplayed, "Syntax Logo is displayed");
		
//		if(isLogoDisplayed) {
//			System.out.println("Test pass. Logo displayed");
//		}else {
//			System.out.println("Test fail. Logo is not displayed");
//		}
	}
	

}
