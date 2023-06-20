package tiki.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Shopper_01_Manage_Product {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
 
		driver = new ChromeDriver();
		System.out.println("______________Before Test______________");
	}
	
	@Test(groups= {"admin", "product"})
	public void TC_01_Create_Product() {

	}

	@Test(groups= {"admin", "product"})
	public void TC_02_View_Product() {

	}

	@Test(groups= {"admin", "product"})
	public void TC_03_Update_Product() {

	}

	@Test(groups= {"admin", "product"})
	public void TC_04_Delete_Product() {

	}
	
	@AfterTest(alwaysRun = true)
	public void afterTest() {
		System.out.println("______________After Test______________");
		driver.quit();
	}
}
