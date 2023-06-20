package webdriver;

import java.util.HashMap;
import java.util.Map;
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

public class Topic_16_Dialog_Part2 {
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Fixed_Popup_Not_In_DOM() {
		driver.get("https://tiki.vn/");

		By loginPopup = By.cssSelector("div.ReactModal__Content");

		// Verify popup chua hien thi trong DOM
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);

		driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
		sleepInSecond(1);

		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).isDisplayed());

		driver.findElement(By.cssSelector("button.btn-close")).click();

		// Verify popup da duoc close
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
	}
	
	@Test
	public void TC_02_Fixed_Popup_Not_In_DOM() {
		driver.get("https://www.facebook.com/");
		
		By registerPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		Assert.assertEquals(driver.findElements(registerPopup).size(), 0);
		
		driver.findElement(By.xpath("//a[text()='Create new account']")).click();
		Assert.assertTrue(driver.findElement(registerPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("Huyen");
		sleepInSecond(1);
		driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Huyen");
		sleepInSecond(1);
		driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("Huyenpt@gmail.com");
		sleepInSecond(1);
		driver.findElement(By.cssSelector("input[name='reg_passwd__']")).sendKeys("123456789Qa@");
		sleepInSecond(1);
		new Select(driver.findElement(By.cssSelector("select#day"))).selectByVisibleText("24");
		new Select(driver.findElement(By.cssSelector("select#month"))).selectByVisibleText("May");
		new Select(driver.findElement(By.cssSelector("select#year"))).selectByVisibleText("1996");
		driver.findElement(By.xpath("//label[text()='Female']/following-sibling::input")).click();
		sleepInSecond(1);
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElements(registerPopup).size(), 0);
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