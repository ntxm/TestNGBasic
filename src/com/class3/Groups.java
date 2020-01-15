package com.class3;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.utils.CommonMethods;
import com.utils.Constants;

public class Groups extends CommonMethods {
	
	@BeforeMethod
	public void openAndNavigate() {
		setUp("chrome", Constants.HRM_URL);
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
	
	@Test(groups = {"smoke", "login"})
	public void titleValidation() {
		String expectedTitle = "Human Management System";
		String actualTitle = driver.getTitle();
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("test pass");
		}else {
			System.out.println("test fails");
		}
	}
	
	@Test(groups = {"regression", "sprint1"})
	public void logoValidation() {
		boolean isLogoDisplayed = driver.findElement(By.xpath("//div[@id='divLogo']/img")).isDisplayed();
		if(isLogoDisplayed) {
			System.out.println("Test pass. Logo displayed");
		}else {
			System.out.println("Test fail. Logo is not displayed");
		}
	}
	

}
