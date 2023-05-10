package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElements;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Checkbox_Radio_Button {
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
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		By loginButtonBy = By.cssSelector("button.fhs-btn-login");
		
		WebElement loginButtonDisabled = driver.findElement(loginButtonBy);
		Assert.assertFalse(loginButtonDisabled.isEnabled());
		String buttonBackground = loginButtonDisabled.getCssValue("background-image");
		Assert.assertTrue(buttonBackground.contains("rgb(224, 224, 224)"));
		
		driver.findElement(By.id("login_username")).sendKeys("0738466331");
		driver.findElement(By.id("login_password")).sendKeys("123456789");
		sleepInSecond(2);
		
		WebElement logginButton = driver.findElement(loginButtonBy);
		Assert.assertTrue(logginButton.isEnabled());
		Color loginButtonBackgroundColour = Color.fromString(logginButton.getCssValue("background-color"));
		String loginButtonHex = loginButtonBackgroundColour.asHex().toUpperCase();
		Assert.assertTrue(loginButtonHex.equals("#C92127"));
	}

	@Test
	public void TC_02_Default_Checkbox_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		scrollIntoView(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
        WebElement optionEl = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		optionEl.click();
		sleepInSecond(1);
		Assert.assertTrue(optionEl.isSelected());
		optionEl.click();
		Assert.assertFalse(optionEl.isSelected());
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		scrollIntoView(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 125kW']/preceding-sibling::input")));
		WebElement expectedRadio = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 125kW']/preceding-sibling::input"));
		Assert.assertFalse(expectedRadio.isEnabled());
	}
	
	@Test
	public void TC_03_Default_Checkbox_Radio() {
		driver.get("https://automationfc.github.io/multiple-fields/");

        WebElement optionEl = driver.findElement(By.xpath("//label[contains(text(),'Anemia')]/preceding-sibling::input"));
		optionEl.click();
		sleepInSecond(1);
		Assert.assertTrue(optionEl.isSelected());
		optionEl.click();
		Assert.assertFalse(optionEl.isSelected());
		
		WebElement expectedRadio = driver.findElement(By.xpath("//label[contains(text(), \"I don't drink\")] /preceding-sibling::input"));
		expectedRadio.click();
		Assert.assertTrue(expectedRadio.isSelected());
	}
	
	@Test
	public void TC_04_Default_Checkbox_Multiple() {
		driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
//        for (WebElement webElement : allCheckboxes) {
//			webElement.click();
//			Assert.assertTrue(webElement.isSelected());
//			sleepInSecond(1);
//		}
		
		// check neu value = Arthritis thi select
        for (WebElement webElement : allCheckboxes) {
			if (webElement.getAttribute("value").equals("Arthritis")){
				webElement.click();
				Assert.assertTrue(webElement.isSelected());
				break;
			}
		}
        
	}
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoViewIfNeeded({behavior: 'auto', block: 'center', inline: 'center'});", element);
	}

	@Test
	public void TC_03_Form() {
		// To do
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
}