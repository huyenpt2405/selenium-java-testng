package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Element_Condition_Status {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Visible_Displayed_Visibility() {
		driver.get("https://www.facebook.com/");
		// Có trên UI (bắt buộc)
		// Có trên DOM (bắt buộc)
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("huyen@gmail.com");
	}
	
	@Test
	public void TC_02_Invisible_Undisplayed_Invisibility() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		// Không có trên UI (bắt buộc)
		// Có trên DOM (bắt buộc)
		
		// chờ k hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}
	
	@Test
	public void TC_02_Invisible_Undisplayed_Invisibility_II() {
		driver.get("https://www.facebook.com/");
		// Không có trên UI (bắt buộc)
		// Không Có trên DOM (bắt buộc)
		
		// chờ k hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}
	
	@Test
	public void TC_03_Presence_I() {
		driver.get("https://www.facebook.com/");
		// có trên UI (bắt buộc)
		// Có trên DOM (bắt buộc)
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	}
	
	@Test
	public void TC_03_Presence_II() {
		driver.get("https://www.facebook.com/");
		// Khoong có trên UI (bắt buộc)
		// Có trên DOM (bắt buộc)
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
		
	}
	@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");
		// Khoong có trên UI (bắt buộc)
		// Khoong co trên DOM (bắt buộc)
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		
		// Phase 1: Element co trong DOM
		// chờ k hiển thị trong vòng 10s
		WebElement regEnterEmailTextBox = driver.findElement(By.name("reg_email_confirmation__"));	
		// Thao tac voi element khac lam cho re enter email khong con trong DOM nua
		
		//Close popup di
		driver.findElement(By.cssSelector("img._8idr")).click();
		
		explicitWait.until(ExpectedConditions.stalenessOf(regEnterEmailTextBox));
		
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