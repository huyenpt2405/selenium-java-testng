package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slower");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(), "Slower");
		sleepInSecond(3);
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(), "Faster");
		sleepInSecond(3);
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Fast");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(), "Fast");
	}
	
	@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection");
		
		selectItemInDropdown("i.dropdown.icon", "div.item span", "Jenny Hess");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
		sleepInSecond(3);
		
		selectItemInDropdown("i.dropdown.icon", "div.item span", "Matt");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
		sleepInSecond(3);
		
		selectItemInDropdown("i.dropdown.icon", "div.item span", "Christian");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");
		sleepInSecond(3);
	}

	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInDropdown("div.btn-group", "ul.dropdown-menu a", "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		sleepInSecond(3);
	}
	
	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		enterAndSelectDropdownItem("input.search", "div[role='option'] span", "Algeria");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");
		sleepInSecond(3);
		
		enterAndSelectDropdownItem("input.search", "div[role='option'] span", "Belgium");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");
		sleepInSecond(3);
	}
	
	public void enterAndSelectDropdownItem(String textboxCss, String dropwDownItemCss, String expectedText) {
		WebElement textboxEl = driver.findElement(By.cssSelector(textboxCss));
		textboxEl.clear();
		textboxEl.sendKeys(expectedText);
		sleepInSecond(1);
		
		List<WebElement> dropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(dropwDownItemCss)));
		
		for (WebElement webElement : dropdownItems) {
			if (webElement.getText().trim().equals(expectedText)) {
				sleepInSecond(1);
				webElement.click();
				break;
			}
		}
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	
	public void selectItemInDropdown(String parentCss, String dropdownItemsCss, String expectedText) {
//		1 - Click vào 1 thẻ bất kì cho nó xổ ra hết các item
		driver.findElement(By.cssSelector(parentCss)).click();
		sleepInSecond(1);
//		2 - Chờ cho tất cả các item load ra thành công
		
//		3 - Tìm item xem đúng cái đang cần hay không
//		 3.1 - Nếu nó nằm trong khoảng nhìn thấy của User thì k cần scroll xuống
//		 3.2 - Nếu nó k nằm trong khoảng nhìn thấy của user thì scroll xuống
//		 4 - Kiểm tra text của item đúng cái mình mong muốn k
//		 5 - Click vào item đó

		List<WebElement> speedDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(dropdownItemsCss)));

		for (WebElement tempElement : speedDropdownItems) {
			if (tempElement.getText().trim().equals(expectedText)) {
				sleepInSecond(1);
				tempElement.click();
				break;
			}
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}