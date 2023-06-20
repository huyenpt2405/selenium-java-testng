package webdriver;

import java.util.HashMap;
import java.util.List;
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

public class Topic_17_Iframe_Frame {
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
	public void TC_01_Iframe() {
		driver.get("https://skills.kynaenglish.vn/");
		
		WebElement facebokkIframe =  driver.findElement(By.xpath("//iframe[contains(@src, 'kyna.vn')]"));
		Assert.assertTrue(facebokkIframe.isDisplayed());
		
		driver.switchTo().frame(facebokkIframe);
		Assert.assertEquals(driver.findElement(By.cssSelector("a[title='Kyna.vn']")).getText(), "Kyna.vn");
		
		driver.switchTo().defaultContent();
		Assert.assertTrue(driver.findElement(By.id("cs_chat_iframe")).isDisplayed());
		
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.cssSelector("div.button_bar")).click();
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Huyen");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("9437580203");
		new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("TƯ VẤN TUYỂN SINH");
		
		driver.switchTo().defaultContent();
		
		String searchText = "Excel";
		driver.findElement(By.id("live-search-bar")).sendKeys(searchText);
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		
		List<WebElement> courseTitle = driver.findElements(By.cssSelector("ul.k-box-card-list>li h4"));
		for (WebElement webElement : courseTitle) {
			Assert.assertTrue(webElement.getText().contains(searchText));
		}
	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");
		driver.findElement(By.name("fldLoginUserId")).sendKeys("huyenpt2405");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		
		driver.switchTo().defaultContent();
		Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());
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