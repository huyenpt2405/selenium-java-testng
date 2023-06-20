package webdriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Dialog_Part1 {
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
 
		// Turn off notification in chrome
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		
		driver.findElement(By.xpath("//div[@id='button-login-dialog']/button[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		
		By loginPopup = By.cssSelector("div#modal-login-v1.in div.modal-content");
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("div.modal.fade.in input#account-input")).sendKeys("huyenphamthu");
		driver.findElement(By.cssSelector("div.modal.fade.in input#password-input")).sendKeys("huyenphamthu");
		driver.findElement(By.xpath("//div[@class='modal fade in']//button[text()='Đăng nhập']")).click();
		
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='modal fade in']//div[text()='Tài khoản không tồn tại!']")).isDisplayed());
		driver.findElement(By.cssSelector("div.modal.fade.in div.modal-header button.close")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
	}

	@Test
	public void TC_02_Fixed_Popup_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		
		By loginPopup = By.cssSelector("div#k-popup-account-login");
		
		// Verify undisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		
		driver.findElement(By.xpath("//nav//a[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("abc@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("12345678");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		driver.findElement(By.cssSelector("div#k-popup-account-login div.modal-header button")).click();
		sleepInSecond(2);
		
		// Verify undisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
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