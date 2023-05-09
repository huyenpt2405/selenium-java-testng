package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Checkbox_Radio {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExcutor;

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
	public void TC_01_Custom_Radio_Using_Input() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		WebElement radioButtonEl = driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div//input"));
		jsExcutor.executeScript("arguments[0].click()", radioButtonEl);
		
		Assert.assertTrue(radioButtonEl.isSelected());
	}
	
	@Test
	public void TC_02_Url() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(3);
		
		jsExcutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[aria-label='Hồ Chí Minh']")));
		sleepInSecond(2);
		
		jsExcutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[aria-label='Quảng Ngãi']")));
		sleepInSecond(2);
		
		// Cach 1
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Hồ Chí Minh'][aria-checked='true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Quảng Ngãi'][aria-checked='true']")).isDisplayed());
		
		// Cach 2
		Assert.assertEquals(driver.findElement(By.cssSelector("div[aria-label='Hồ Chí Minh']")).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(By.cssSelector("div[aria-label='Quảng Ngãi']")).getAttribute("aria-checked"), "true");
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