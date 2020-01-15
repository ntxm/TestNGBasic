package HRMSystemTestNG;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.*;

/*
 * Open chrome browser
 *	Go to “http://166.62.36.207/humanresources/symfony/web/index.php/auth/login”
 *	Login into the application
 *	Add 5 different Employees and create login credentials by providing: 
 *	First Name
 *	Last Name
 *	Username
 *	Password
 *	Provide Employee First and Last Name
 *	Verify Employee has been added successfully and take screenshot 
 *	(you must have 5 different screenshots)
 *	Close the browser
 */
import com.utils.CommonMethods;
import com.utils.Constants;

public class AddFiveEmployee extends CommonMethods {
	
	@BeforeClass
	public void login() {
		CommonMethods.setUp("chrome", Constants.HRM_URL);
		//Login
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
		driver.findElement(By.id("btnLogin")).click();
		
	}
	
	
	@BeforeMethod
	public void navigate() throws InterruptedException {
		//Navigate to the "Add Employee" Page
		driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Add Employee")).click();
	}
		
	
	@Test(dataProvider ="dataForAccount", enabled = true, groups = "Employee")
	public void addEmployee(String firstName, String lastName, String username, String password) throws InterruptedException {
		//Checkbox "Create login details"
		driver.findElement(By.xpath("//input[@id='chkLogin']")).click();
		//add information
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(lastName);
		driver.findElement(By.id("user_name")).sendKeys(username);
		driver.findElement(By.id("user_password")).sendKeys(password);
		driver.findElement(By.id("re_password")).sendKeys(password);
		
		//add photo
		driver.findElement(By.id("photofile")).sendKeys("/Users/natalia/Downloads/1234.png");
		
		//save new employee
		driver.findElement(By.id("btnSave")).click();
		
		//verify Employee has been added successfully 
		boolean checkEmployeePhoto = driver.findElement(By.xpath("//div[@class='imageHolder']/a/img")).isDisplayed();
		String actualName = driver.findElement(By.xpath("//div[@id='profile-pic']/h1")).getText();
		Assert.assertTrue(checkEmployeePhoto, "ERROR: photo is NOT displayed"); 
		Assert.assertEquals(actualName, firstName+" "+lastName, "ERROR: check name");
		Thread.sleep(2000);
		
	}
	
	@DataProvider
	public Object[][] dataForAccount(){
		
		Object[][] loginCredentials = {
				{"Olivia", "Berkman1010", "OliviaBerkman", "TestUser001#Run21!"},
				{"Garry", "Thomson1010", "GarryThomson", "TestUser002#Run22!"},
				{"Margharett", "Silvino1010", "MargharettSilvino", "TestUser003#Run23!"},
				{"Dan", "Markeloff1010", "DanMarkeloff", "TestUser004Run24!#"},
				{"Katty", "Simpson1010", "KattySimpson", "TestUser005Run25!#"}
		};
		
		return loginCredentials;
	}
	
	@AfterMethod
	public void screenshot() {
		//generate random name for screenshot name
		int scrName = (int) Math.random();
		TakesScreenshot screen = (TakesScreenshot)driver;
		File screenshot = screen.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("screenshots/HRMS/AddEmp" +scrName +".png"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
