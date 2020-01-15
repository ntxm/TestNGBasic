package HRMSystemTestNG;

/**
 * ===============
 * @author ntxm    
 * ===============
 * login() --> Open browser and navigate to HRMS
 * 
 * dashboard() -->  Check that correct page was opening. Verifying title.
 * 
 * quickLaunch() --> Check Quick Launch Block.
 * 		Step 1: Check if "Quick Launch" block is displayed
 * 		Step 2: Check if all icons from block is displayed
 * 		Step 3: Check if text of links is correct
 * 
 * employeeDistribution() --> Check if block "Employee Distribution by Subunit" Block.
 * 		Step 1: Verify if block is displayed.
 * 		Step 2: Verify text displayed when you hold mouse over the element
 * 
 * screensShot() --> Takes screenshot
 * 
 * tearDown() --> Close the browser
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import com.utils.CommonMethods;
import com.utils.Constants;

public class Dashboard extends CommonMethods {
	
	@BeforeClass
	public void login() {
		CommonMethods.setUp("chrome", Constants.HRM_URL);
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
		WebElement button = driver.findElement(By.id("btnLogin"));
		Actions action = new Actions(driver);
		action.click(button).perform();
		
	}
	
	
	@Test (priority = 1, enabled = true, groups = "Dashboard")
	//Title
	public void dashboard() {
		SoftAssert softAssert = new SoftAssert();
		String expectedTitle = "Dashboard";
		String actualTitle = driver.findElement(By.xpath("//div[@class='head']/h1")).getText();
		softAssert.assertEquals(actualTitle, expectedTitle, "ERROR: Title is not matched");
		softAssert.assertAll();
	}
	
	@Test(priority = 2, enabled = true, groups ="Dashboard")
	//Quick Launch
	public void quickLaunch() {
		//block displayed
		boolean isQuickLaunch = driver.findElement(By.id("panel_resizable_0_0")).isDisplayed();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isQuickLaunch, "ERROR: block does not displayed");
		
		//icons displayed
		List<WebElement> icons = driver.findElements(By.xpath("//table[@class='quickLaungeContainer']/tbody/tr/td//div[@class='quickLaunge']/a/img"));
		for(WebElement icon: icons) {
			boolean isIconDisplayed = icon.isDisplayed();
			softAssert.assertTrue(isIconDisplayed, "ERROR: icon not displayed");
		}
		
		//-----------------------------------
		//Link Text from Quick Launch Block
		//-----------------------------------
		
		//ArrayList of Expected values
		ArrayList<String> expectedTextLinks = new ArrayList<>();
		expectedTextLinks.add("Assign Leave");
		expectedTextLinks.add("Leave List");
		expectedTextLinks.add("Timesheets");
		
		//ArrayList of Actual values
		List<WebElement> textQuickLaunch = driver.findElements(By.xpath("//table[@class='quickLaungeContainer']/tbody/tr/td//div[@class='quickLaunge']/a/span"));
		ArrayList<String> actualTextLinks = new ArrayList<>();
		
		for(int i = 0; i<textQuickLaunch.size(); i++) {
			
			String actualText = textQuickLaunch.get(i).getText();
			actualTextLinks.add(actualText);
			
			softAssert.assertEquals(actualTextLinks.get(i), expectedTextLinks.get(i), "ERROR: NOT matched");
			
		}
		
		softAssert.assertAll();
		
	}
	
	@Test(priority = 3, enabled = true, groups ="Dashboard")
	public void employeeDistribution() {
		boolean isBlockDisplayed = driver.findElement(By.id("panel_resizable_1_0")).isDisplayed();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isBlockDisplayed, "ERROR: block does NOT displayed");
		
		WebElement diagramma = driver.findElement(By.xpath("//canvas[@class='flot-overlay']"));
		Actions action = new Actions(driver);
		action.moveToElement(diagramma).perform();
		boolean diagrammaText = driver.findElement(By.xpath("//div[@id='hover_div_graph_display_emp_distribution']")).isDisplayed();
		softAssert.assertTrue(diagrammaText, "ERROR: message is NOT displayed");
		softAssert.assertAll();
	}
	
	@AfterClass
	public void screensShot() {
		TakesScreenshot screen = (TakesScreenshot)driver;
		File testResult = screen.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(testResult, new File("screenshots/HRMS/Dashboard.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
