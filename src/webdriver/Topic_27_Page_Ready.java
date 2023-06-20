package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Page_Ready {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExcutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
 
		driver = new ChromeDriver();
		jsExcutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Orange_HRM_API() {
		driver.get("https://api.orangehrm.com/");
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loader")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")), "OrangeHRM REST API Documentation");
	}

	@Test
	public void TC_02_Nop_Ecommere() {
		driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
		
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		
		driver.findElement(By.cssSelector("button.login-button")).click();
		
		Assert.assertTrue(waitForAjaxLoadingInvisible());
		
		driver.findElement(By.xpath("//a[contains(@class,'nav-link') and text()='Logout']")).click();
		Assert.assertEquals(driver.getTitle(), "Your store. Login");
		Assert.assertTrue(waitForAjaxLoadingInvisible());
	}
	
	@Test
	public void TC_02_Nop_Ecommere_II() {
		driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
		
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		
		driver.findElement(By.cssSelector("button.login-button")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.xpath("//a[contains(@class,'nav-link') and text()='Logout']")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		Assert.assertEquals(driver.getTitle(), "Your store. Login");
	}

	@Test
	public void TC_03_Blog_Test_Project() {
		driver.get("https://blog.testproject.io/");
		
		action.moveToElement(driver.findElement(By.cssSelector("h1.main-heading")));
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		String keyword = "Selenium";
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys(keyword);
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		
		List<WebElement> postTitleElements = driver.findElements(By.cssSelector("h3.post-title"));
		for (WebElement element : postTitleElements) {
			Assert.assertTrue(element.getText().contains(keyword));
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean waitForAjaxLoadingInvisible() {
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
	}
	
	public boolean isPageLoadedSuccess() {
		jsExcutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return (Boolean) jsExcutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return (Boolean) jsExcutor.executeScript("return document.readyState;").toString().equals("complete");
			}
		};
		
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
}