package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert_Dailog {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
 
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(2);
		
		// Wait and switch to Alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Verify alert title
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
	}

	@Test
	public void TC_02_Confirmation_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(2);
		
		// Wait and switch to Alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Verify alert Title
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(2);
		
		// Wait and switch to alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Verify alert title
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		String inputText = "huyenphamthu";
		alert.sendKeys(inputText);
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + inputText);
	}
	
//	@Test
	public void TC_04_Alert() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		sleepInSecond(5);
		
		driver.findElement(By.cssSelector("a.login-btn")).click();
		// sleepInSecond(2);
		
		// Wair and switch to alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "Customer ID  cannot be left blank.");
		
	}
	
	@Test
	public void TC_05_Auth_Alert() {
		driver.get(passUserNameAndPassToUrl("http://the-internet.herokuapp.com/basic_auth", "admin", "admin"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	@Test
	public void TC_06_Auth_Alert_PartII() {
		driver.get("https://the-internet.herokuapp.com/");
		driver.findElement(By.xpath("//a[contains(text(), 'Basic Auth')]")).click();
		sleepInSecond(2);
		
		driver.get(passUserNameAndPassToUrl("http://the-internet.herokuapp.com/basic_auth", "admin", "admin"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	public String passUserNameAndPassToUrl(String url, String username, String password) {
		String[] arrayUrl = url.split("//");
		return arrayUrl[0] + username + ":" + password + "@" + arrayUrl[1];
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