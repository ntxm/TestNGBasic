package HRMSystemTestNG;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
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
	
	@AfterClass
	public void tearDown() {
		driver.quit();
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
	
	@Test(priority = 1, enabled = true, groups ="Dashboard")
	//Quick Launch
	public void quickLaunch() {
		//block displayed
		boolean isQuickLaunch = driver.findElement(By.id("panel_resizable_0_0")).isDisplayed();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isQuickLaunch, "ERROR: block does not displayed");
		
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
	
	
	
	
	
	

}
