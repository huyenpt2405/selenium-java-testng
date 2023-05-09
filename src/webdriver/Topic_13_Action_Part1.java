package webdriver;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Action_Part1 {
	WebDriver driver;
	Actions actions;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
 
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
		chromeOptions.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    
		driver = new ChromeDriver(chromeOptions);
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}

//	@Test
	public void TC_02_Menu() {
		driver.get("https://www.myntra.com/");
		
		actions.moveToElement(driver.findElement(By.xpath("//nav//a[text()='Kids']"))).perform();
		sleepInSecond(1);
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
	}

	@Test
	public void TC_03_Menu() {
		driver.get("https://www.fahasa.com/");
		sleepInSecond(2);
		actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		sleepInSecond(1);
		actions.moveToElement(driver.findElement(By.cssSelector("a[title='Sách Trong Nước']"))).perform();
		sleepInSecond(1);
		driver.findElement(By.xpath("//div[@class='fhs-header-top-second-bar']//a[text()='Câu Chuyện Cuộc Đời']")).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb li:last-child strong")).getText(), "Câu Chuyện Cuộc Đời");
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