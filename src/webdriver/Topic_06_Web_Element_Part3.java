package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Part3 {

	WebDriver driver;
	Random rand;
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress, firstName, lastName, password, fullName;
	
	By myAccountLink = By.cssSelector(".footer a[title='My Account']");
	By loginButton = By.id("send2");
	By emailTextBox = By.id("email");
	By passwordTextBox = By.id("pass");
	By createAccountLink = By.cssSelector("a[title='Create an Account']");
	By firstNameTextBox = By.id("firstname");
	By lastNametextBox = By.id("lastname");
	By emailAddressTextBox = By.id("email_address");
	By regPasswordTextBox = By.id("password");
	By regConfirmPasswordTextBox = By.id("confirmation");
	By registerButton = By.cssSelector("button[title='Register']");
	By thanksForRegisterText = By.cssSelector("li.success-msg span");
	By userInformationText = By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		
		rand = new Random();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		emailAddress = "automation" + rand.nextInt(99999) + "@gmail.com";
		firstName = "Huyen";
		lastName = "Pham";
		password = "123456";
		fullName = firstName + " " + lastName;
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	
//	@Test
	public void Login_01_Empty_Email_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccountLink).click();
		sleepInSecond(3);
		driver.findElement(loginButton).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}
	
//	@Test
	public void Login_02_Invalid_Email() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccountLink).click();
		sleepInSecond(3);
		
		driver.findElement(emailTextBox).sendKeys("123434234@12312.123123");
		driver.findElement(passwordTextBox).sendKeys("123456");
		driver.findElement(loginButton).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
//	@Test
	public void Login_03_Password_Less_Than_6_Characters() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccountLink).click();
		sleepInSecond(3);
		
		driver.findElement(emailTextBox).sendKeys(emailAddress);
		driver.findElement(passwordTextBox).sendKeys("123");
		driver.findElement(loginButton).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
//	@Test
	public void Login_04_Incorrect_Email_Or_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccountLink).click();
		sleepInSecond(3);
		
		driver.findElement(emailTextBox).sendKeys(emailAddress);
		driver.findElement(passwordTextBox).sendKeys("123123123");
		driver.findElement(loginButton).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");
	}
	
	@Test
	public void Register_05_Create_Account() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(myAccountLink).click();
		sleepInSecond(2);
		driver.findElement(createAccountLink).click();
		sleepInSecond(2);
		driver.findElement(firstNameTextBox).sendKeys(firstName);
		driver.findElement(lastNametextBox).sendKeys(lastName);
		driver.findElement(emailAddressTextBox).sendKeys(emailAddress);
		driver.findElement(regPasswordTextBox).sendKeys(password);
		driver.findElement(regConfirmPasswordTextBox).sendKeys(password);
		driver.findElement(registerButton).click();
		
		sleepInSecond(2);
		// Verify message when created successful
		Assert.assertEquals(driver.findElement(thanksForRegisterText).getText(), "Thank you for registering with Main Website Store.");
		
		// Verify User information is displayed correctly
		String userInfo = driver.findElement(userInformationText).getText();
		Assert.assertTrue(userInfo.contains(fullName));
		Assert.assertTrue(userInfo.contains(emailAddress));
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
		
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src, 'logo.png')]")).isDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
