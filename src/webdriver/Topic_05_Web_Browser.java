package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Url() {
		// To do 
		driver.close();
		driver.quit();
		//Tìm 1 element 
		WebElement emailTextBox = driver.findElement(By.xpath(""));
		emailTextBox.clear();
		emailTextBox.sendKeys("");
		
		// Tìm nhiều element
		List<WebElement> checkboxes = driver.findElements(By.xpath(""));
		
		// Mở 1 Url bất kì
		driver.get("");
		
		// lay url hien tai
		driver.getCurrentUrl();
		
		// Lay source code HTML cua page hien tai
		driver.getPageSource();
		 
		// Lay title cua page hien tai
		driver.getTitle();
		
		// Lay ra duoc ID cua window ma driver dang active
		String loginWindowID = driver.getWindowHandle();
		
		// Lay ra ID cua tat ca Window/Tab
		Set<String> allIDs = driver.getWindowHandles();
		
		// Cookie vs cache
		
		Options opt = driver.manage();
		opt.getCookies();
		
		// Timeout
		Timeouts time = opt.timeouts();
		
		// Khoarng thoi gian cho element xuat hien
		time.implicitlyWait(5, TimeUnit.SECONDS);
		
		// Khoang thoi gian cho page load xong trong vong x giay
		time.pageLoadTimeout(5,  TimeUnit.SECONDS);
		
		// Khoang thoi gian script duoc thuc thi trong vong x giay
		time.setScriptTimeout(5,  TimeUnit.SECONDS);
		
		Window window = opt.window();
		window.maximize();
		
		// Test FUI/GUI
		window.getPosition();
		window.getSize();
		
		Navigation nav = driver.navigate();
		nav.back();
		nav.forward();
		nav.refresh();
		
		// Support history tot hon
		nav.to("");
		
		TargetLocator tar =	driver.switchTo();
		tar.alert();
		tar.activeElement();
		tar.frame("");
		tar.window("");
	}

	@Test
	public void TC_02_Logo() {
		// To do
	}

	@Test
	public void TC_03_Form() {
		// To do
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}