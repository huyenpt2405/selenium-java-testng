package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_TextBox_TextArea {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String employeeID = String.valueOf(rand.nextInt(99999));
	String firstName = "Huyen", lastName = "Pham", userName = "huyen" + employeeID, password = "Huyen123@";
	String passportNumber = "9950-611-38-7266";
	String commentInput = "This is generated data\nof real people";

//	By firstNameTextBox = By.name("firstName");
//	By lastNameTextBox = By.name("lastName");
////	By By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input") = By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input");
//	By numberTextBox = By.xpath("//label[contains(text(), 'Number')]/parent::div/following-sibling::div/input");
//	By commentsTextArea = By.xpath("//label[contains(text(), 'Comments')]/parent::div/following-sibling::div/textarea");
//	By userNameTextBox = By.name("username");
//	By passwordTextBox = By.name("password");
//	By loginButton = By.xpath("//button[contains(string(), 'Login')]");
//	By editButton = By.cssSelector("i.bi-pencil-fill");
//	By immigrationTabs = By.xpath("//a[text()='Immigration']");

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
	public void TC_01_Url() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[contains(string(), 'Login')]")).click();
		sleepInSecond(5);

		driver.findElement(By.xpath("//a//span[text()='PIM']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//li//a[text()='Add Employee']")).click();
		sleepInSecond(2);

		driver.findElement(By.name("firstName")).sendKeys(firstName);
		driver.findElement(By.name("lastName")).sendKeys(lastName);

		WebElement employeeIDEl = driver
				.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));
		employeeIDEl.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		sleepInSecond(1);
		employeeIDEl.sendKeys(Keys.DELETE);
		sleepInSecond(2);
		employeeIDEl.sendKeys(employeeID);

		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//div//span")).click();
//		sleepInSecond(2);

		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input"))
				.sendKeys(userName);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input"))
				.sendKeys(password);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input"))
				.sendKeys(password);
		driver.findElement(By.xpath("//button[contains(string(), 'Save')]")).click();

		sleepInSecond(10);

		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
						.getAttribute("value"),
				employeeID);

		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);

		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//label[contains(text(), 'Number')]/parent::div/following-sibling::div/input"))
				.sendKeys(passportNumber);
		driver.findElement(
				By.xpath("//label[contains(text(), 'Comments')]/parent::div/following-sibling::div/textarea"))
				.sendKeys(commentInput);
		driver.findElement(By.xpath("//button[contains(string(), 'Save')]")).click();
		sleepInSecond(8);

		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(5);

		Assert.assertEquals(driver
				.findElement(By.xpath("//label[contains(text(), 'Number')]/parent::div/following-sibling::div/input"))
				.getAttribute("value"), passportNumber);
		Assert.assertEquals(driver
				.findElement(
						By.xpath("//label[contains(text(), 'Comments')]/parent::div/following-sibling::div/textarea"))
				.getAttribute("value"), commentInput);

		driver.findElement(By.cssSelector("li.oxd-userdropdown")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		driver.findElement(By.name("username")).sendKeys(userName);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[contains(string(), 'Login')]")).click();
		sleepInSecond(8);

		driver.findElement(By.xpath("//a//span[text()='My Info']")).click();
		sleepInSecond(4);

		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
						.getAttribute("value"),
				employeeID);
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver
				.findElement(By.xpath("//label[contains(text(), 'Number')]/parent::div/following-sibling::div/input"))
				.getAttribute("value"), passportNumber);
		Assert.assertEquals(driver
				.findElement(
						By.xpath("//label[contains(text(), 'Comments')]/parent::div/following-sibling::div/textarea"))
				.getAttribute("value"), commentInput);

	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}