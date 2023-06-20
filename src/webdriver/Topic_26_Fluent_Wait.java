package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_26_Fluent_Wait {
	WebDriver driver;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	long timOut = 15; // Second
	long pollingTime = 1000; // Milisecond

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
 
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		// Fluent Wait
		fluentDriver = new FluentWait<WebDriver>(driver);
		// Set tong thoi gian va tan so
		fluentDriver.withTimeout(15, TimeUnit.SECONDS)
			.pollingEvery(500, TimeUnit.MILLISECONDS)
			.ignoring(NoSuchElementException.class);
		
		// Apply dieu kien
		WebElement startButton = fluentDriver.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div#start>button"));
			}
		});
		
		startButton.click();
		
		// Dung Implicit
//		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//		driver.findElement(By.cssSelector(""));
	}

	@Test
	public void TC_01_Apply_FindElement() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		findElement("//div[@id='start']/button").click();
		
		Assert.assertEquals(findElement("//div[@id='finish']/h4").getText(), "Hello World!");
	}

	@Test
	public void TC_02_Count_Down() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countDownElement = findElement("//div[@id='javascript_countdown_time']");
		
		fluentElement = new FluentWait<WebElement>(countDownElement);
		
		fluentElement.withTimeout(timOut, TimeUnit.SECONDS)
			.pollingEvery(pollingTime, TimeUnit.MILLISECONDS)
			.ignoring(NoSuchElementException.class);
		
		fluentElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				// TODO Auto-generated method stub
				String text = element.getText();
				
				return text.endsWith("00");
			}
		});
		
	}
	
	public WebElement findElement(String xpathLocator) {
		// Fluent Wait
		fluentDriver = new FluentWait<WebDriver>(driver);
		// Set tong thoi gian va tan so
		fluentDriver.withTimeout(timOut, TimeUnit.SECONDS)
			.pollingEvery(pollingTime, TimeUnit.MILLISECONDS)
			.ignoring(NoSuchElementException.class);
		
		// Apply dieu kien
		return fluentDriver.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpathLocator));
			}
		});
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}