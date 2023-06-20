package webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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

public class Topic_16_Dialog_Part3 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress = "huyen" + randomNumber() + "@gmail.com";

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		// Turn off notification in chrome
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Random_Popup_In_DOM() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(15);
		By lePopup = By.cssSelector("div.lepopup-popup-container div:first-child");

		if (driver.findElement(lePopup).isDisplayed()) {
			// Nhap email
			driver.findElement(By.cssSelector("div.lepopup-input input")).sendKeys(emailAddress);
			sleepInSecond(1);
			driver.findElement(By.cssSelector("a.lepopup-button")).click();
			sleepInSecond(5);
			Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Thank you!']/parent::div")).getText().contains("Your sign-up request was successful. We will contact you shortly. Please"));
			// Dong popup
			// Sau 5s no tu dong dong
			sleepInSecond(6);
		}
		
		String searchText = "Agile Testing Explained";
		driver.findElement(By.id("search-input")).sendKeys(searchText);
		driver.findElement(By.id("search-submit")).click();
		
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("ul#posts-container li:first-child div h2")).getText(), searchText);
	}
	
	@Test
	public void TC_02_Random_Popup_In_DOM() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(30);
		By popup = By.cssSelector("div#tve-p-scroller");
		
		// isDisplayed chi dung de check element luon co trong DOM
		// Se throw NoSuchElementException khi khong tim thay element
		if (driver.findElement(popup).isDisplayed()) {
			driver.findElement(By.cssSelector("div.thrv_icon")).click();
			sleepInSecond(3);
		}
		
		driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://vnk.edu.vn/lich-khai-giang/");
	}
	
	@Test
	public void TC_03_Random_Popup_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		
		By popup = By.cssSelector("div.popup-content");
		
		if (driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()) {
			driver.findElement(By.id("popup-name")).sendKeys("Huyen Pham");
			driver.findElement(By.id("popup-email")).sendKeys(emailAddress);
			driver.findElement(By.id("popup-phone")).sendKeys("0847374573");
			sleepInSecond(3);
			
			driver.findElement(By.cssSelector("button#close-popup")).click();
			Assert.assertEquals(driver.findElements(popup).size(), 0);
		}
		
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(3);
		
		String courseName = "Khóa học Thiết kế và Thi công Hệ thống BMS";
		driver.findElement(By.id("search-courses")).sendKeys(courseName);
		driver.findElement(By.id("search-course-button")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElements(By.cssSelector("div.course")).size(), 1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.course h4")).getText(), courseName);
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
	
	public int randomNumber() {
		return new Random().nextInt(99999);
	}
}