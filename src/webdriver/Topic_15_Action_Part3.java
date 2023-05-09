package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Action_Part3 {
	WebDriver driver;
	Actions actions;
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
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Click_And_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));

		actions.clickAndHold(listNumber.get(0)).moveToElement(listNumber.get(7)).release().perform();
//		sleepInSecond(10);

		List<WebElement> listSelectedItem = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(listSelectedItem.size(), 8);
	}

	@Test
	public void TC_02_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		Keys key;
		if (osName.contains("Windows")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}

		actions.keyDown(key).perform();
		actions.click(listNumber.get(0)).click(listNumber.get(3)).click(listNumber.get(5)).click(listNumber.get(10))
				.perform();
		
		actions.keyUp(key).perform();
		
		List<WebElement> listSelectedItem = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
		Assert.assertEquals(listSelectedItem.size(), 4);
	}

	@Test
	public void TC_03_Menu() {
//		driver.get("https://www.fahasa.com/");
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