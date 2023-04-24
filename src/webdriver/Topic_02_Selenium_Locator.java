package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	// 8 loại locator (type: By)
	// Id, class/name/tagname/linktext/partialLink/xpath/css selector
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
 
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// Mở trang register
		driver.get("https://demo.nopcommerce.com/register");
	}

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("FirstName")).sendKeys("Automation");;
	}
	@Test
	public void TC_02_Class() {
		driver.get("https://demo.nopcommerce.com/search");
		driver.findElement(By.className("search-text")).sendKeys("Macbook");
	}
	
	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("advs")).click();
	}
	@Test
	public void TC_04_TagName() {
		System.out.println(driver.findElements(By.tagName("input")).size());
	}
	@Test
	public void TC_05_LinkText() {
		driver.findElement(By.linkText("Addresses")).click();
		// To do
	}
	@Test
	public void TC_06_PartialLinkText() {
		driver.findElement(By.partialLinkText("Apply for")).click();
		// To do
	}

	@Test
	public void TC_07_CssSelector() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Last Name");
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("Email ");
	}

	@Test
	public void TC_08_Xpath() {
		// To do
		
//		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Last Name");
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("Email ");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}