package webdriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Explicit_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String beachFileName = "beach.jpg";
	String skyFileName = "sky.jpg";
	String winterFileName = "winter.jpg";
	
	String uploadFilePath = projectPath + File.separator + "uploadFiles" + File.separator;
	String beachPath = uploadFilePath + beachFileName;
	String skyPath = uploadFilePath + skyFileName;
	String winterPath = uploadFilePath + winterFileName;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
 
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 15);
	}

	@Test
	public void TC_01_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start button")).click();
		// Loading icon Mat 5s de an di, sau do moi load text
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");		
	}
	
	@Test
	public void TC_03_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start button")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_04_Ajax_loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		By selectedDateText = By.id("ctl00_ContentPlaceholder1_Label1");
		// Doi cho Date time dc hien thi
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
		
		// Verify No selected date is displayed
		Assert.assertEquals(driver.findElement(selectedDateText).getText(), "No Selected Dates to display.");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='15']")));
		driver.findElement(By.xpath("//a[text()='17']")).click();
		
		// Wait cho ajax loading bien mat trong 15s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='17']/parent::td[contains(@class, 'rcSelected')]")).isDisplayed());
		Assert.assertEquals(driver.findElement(selectedDateText).getText(), "Wednesday, May 17, 2023");
	}
	
	@Test
	public void TC_05_Upload() {
		driver.get("https://gofile.io/uploadFiles");
		explicitWait = new WebDriverWait(driver, 30);
		By uploadSuccessMessage = By.xpath("//div[contains(@class, 'mainUploadSuccess')]//div[text()='Your files have been successfully uploaded']");
		By showFileLink = By.cssSelector("div.mainUploadSuccessLink a");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.filesUploadButton")));
		
		driver.findElement(By.id("filesUploadInput")).sendKeys(beachPath + "\n" + skyPath + "\n" + winterPath);
		
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar"))));
		
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(uploadSuccessMessage)).isDisplayed());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(showFileLink)).click();
		
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[text()='%s']/parent::a/parent::div/following-sibling::div//span[text()='Download']", beachFileName)))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[text()='%s']/parent::a/parent::div/following-sibling::div//span[text()='Download']", skyFileName)))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[text()='%s']/parent::a/parent::div/following-sibling::div//span[text()='Download']", winterFileName)))).isDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}