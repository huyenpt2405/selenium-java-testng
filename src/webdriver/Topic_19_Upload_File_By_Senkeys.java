package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Upload_File_By_Senkeys {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	String winterFileName = "winter.jpg";
	String beachFileName = "beach.jpg";
	String skyFileName = "sky.jpg";

	String winterFilePath = projectPath + "\\uploadFiles\\" + winterFileName;
	String beachFilePath = projectPath + "\\uploadFiles\\" + beachFileName;
	String skyFilePath = projectPath + "\\uploadFiles\\" + skyFileName;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		jsExcutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_JQuery_File_Upload() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(winterFilePath);
		sleepInSecond(1);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(skyFilePath);
		sleepInSecond(1);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(beachFilePath);
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + winterFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + skyFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + beachFileName + "']")).isDisplayed());

		List<WebElement> startButtons = driver.findElements(By.cssSelector("tr.template-upload button.start"));
		for (WebElement button : startButtons) {
			button.click();
			sleepInSecond(2);
		}
		Assert.assertTrue(isImageLoaded(String.format("//img[contains(@src, %s)]", beachFileName)));
		Assert.assertTrue(isImageLoaded(String.format("//img[contains(@src, %s)]", winterFileName)));
		Assert.assertTrue(isImageLoaded(String.format("//img[contains(@src, %s)]", skyFileName)));
		
	}
	@Test
	public void TC_02_File_Upload_Multiple_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(winterFilePath + "\n" + skyFilePath + "\n" + beachFilePath);
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + winterFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + skyFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + beachFileName + "']")).isDisplayed());
		
		List<WebElement> startButtons = driver.findElements(By.cssSelector("tr.template-upload button.start"));
		for (WebElement button : startButtons) {
			button.click();
			sleepInSecond(2);
		}
		Assert.assertTrue(isImageLoaded(String.format("//img[contains(@src, %s)]", beachFileName)));
		Assert.assertTrue(isImageLoaded(String.format("//img[contains(@src, %s)]", winterFileName)));
		Assert.assertTrue(isImageLoaded(String.format("//img[contains(@src, %s)]", skyFileName)));
		
	}
	

	public WebElement getElementByXpath(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExcutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalHeight != 'undefined' && arguments[0].naturalHeight > 0;",
				getElementByXpath(locator));
		return status;
	}

	@Test
	public void TC_02_Menu() {

	}

	@Test
	public void TC_03_Menu() {

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