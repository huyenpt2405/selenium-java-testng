package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
 
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Switch_Window_By_ID() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String basicFormWindowID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		switchToWindowByID(basicFormWindowID);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium");
		driver.switchTo().window(basicFormWindowID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		closeAllWindowsWithoutParent(basicFormWindowID);
	}
	
	@Test
	public void TC_02_Switch_Window_By_Title() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String basicFormWindowID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		switchToWindowByPageTitle("Google");
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium");
		switchToWindowByPageTitle("Selenium WebDriver");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		switchToWindowByPageTitle("Facebook");
		sleepInSecond(3);
		closeAllWindowsWithoutParent(basicFormWindowID);
	}
	
	@Test
	public void TC_03_Live_Guru() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		sleepInSecond(1);
		
		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/ul//a[@class='link-compare']")).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");
		
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/ul//a[@class='link-compare']")).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		
		String mobileWindowID = driver.getWindowHandle();
		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		switchToWindowByID(mobileWindowID);
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
//		driver.findElement(By.cssSelector("button[title='Close Window']")).click();
//		driver.switchTo().window(mobileWindowID);
		closeAllWindowsWithoutParent(mobileWindowID);
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		sleepInSecond(2);
		
		driver.switchTo().alert().accept();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The comparison list was cleared.");
		
	}
	
	public void switchToWindowByID(String sourceID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		for (String id : allWindowIDs) {
			if (!id.equals(sourceID)) {
				driver.switchTo().window(id);
			}
		}
	}
	
	public void switchToWindowByPageTitle(String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			sleepInSecond(2);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}
	
	public void closeAllWindowsWithoutParent(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		if (allWindowIDs.size() == 0) {
			return;
		}
		
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
				sleepInSecond(1);
			}
		}
		driver.switchTo().window(parentID);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	} 
}