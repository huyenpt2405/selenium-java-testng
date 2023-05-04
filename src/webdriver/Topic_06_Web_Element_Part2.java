package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Part2 {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By emailTextBox = By.id("mail");
	By ageUnder18Radio = By.id("under_18");
	By educationTextArea = By.id("edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By job1Dropdown = By.id("job1");
	By job2Dropdown = By.id("job2");
	By developmentRedio = By.id("development");
	By passwordTextBoxDisabled = By.id("disable_password");
	By ageRadioDisabled = By.id("radio-disabled");
	By biographyTextArea = By.id("bio");
	By job3Dropdown = By.id("job3");
	By interestsChecboxDisabled = By.id("check-disbaled");
	By slider1Slider = By.id("slider-1");
	By slider2Slider = By.id("slider-2");
	By javaLanguageRadio = By.id("java");
	
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
//	@Test
	public void TC_01_Verify_Display_Element() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Textbox/ Text area => nhap text vao va in ra console
		WebElement emailEl = driver.findElement(emailTextBox);
		if (emailEl.isDisplayed()) {
			emailEl.sendKeys("Selenium WebDriver");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
		}
		
		WebElement educationEl = driver.findElement(educationTextArea);
		if (educationEl.isDisplayed()) {
			educationEl.sendKeys("Selenium WebDriver");
			System.out.println("Education textarea is displayed");
		} {
			System.out.println("Education textarea is not displayed");
		}
		
		WebElement ageUnder1El = driver.findElement(ageUnder18Radio);
		if (ageUnder1El.isDisplayed()) {
			ageUnder1El.click();;
			System.out.println("Age under 18 is displayed");
		} else {
			System.out.println("Age under 18 is not displayed");
		}
		
		WebElement nameUser5El = driver.findElement(nameUser5Text);
		if (nameUser5El.isDisplayed()) {
			System.out.println("Name User 5 is displayed");
		} else {
			System.out.println("Name User 5 is not displayed");
		}
	}
	
//	@Test
	public void TC_02_Verify_Enabled_Element() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(emailTextBox).isEnabled()) {
			System.out.println("Email textbox is enabled");
		} else {
			System.out.println("Email textbox is disabled");
		}
		
		if (driver.findElement(educationTextArea).isEnabled()) {
			System.out.println("Education textarea is enabled");
		} {
			System.out.println("Education textarea is disabled");
		}
		
		if (driver.findElement(ageUnder18Radio).isEnabled()) {
			System.out.println("Age under 18 is enabled");
		} else {
			System.out.println("Age under 18 is disabled");
		}
		
		if (driver.findElement(job1Dropdown).isEnabled()) {
			System.out.println("Job Role 1 is enabled");
		} else {
			System.out.println("Job Role 1 is disabled");
		}
		 
		if (driver.findElement(job2Dropdown).isEnabled()) {
			System.out.println("Job Role 2 is enabled");
		} else {
			System.out.println("Job Role 2 is disabled");
		}
		
		if (driver.findElement(developmentRedio).isEnabled()) {
			System.out.println("Interests Development checkbox is enabled");
		} else {
			System.out.println("Interests Development checkbox is disabled");
		}
		
		if (driver.findElement(slider1Slider).isEnabled()) {
			System.out.println("Slider 1 is enabled");
		} else {
			System.out.println("Slider 1 is disabled");
		}
		
		// Step 3 Kiem tra cac phan tu disable
		
		if (driver.findElement(passwordTextBoxDisabled).isEnabled()) {
			System.out.println("passwordTextBoxDisabled is enabled");
		} else {
			System.out.println("passwordTextBoxDisabled is disbaled");
		}
		
		if (driver.findElement(ageRadioDisabled).isEnabled()) {
			System.out.println("ageRadioDisabled is enabled");
		} else {
			System.out.println("ageRadioDisabled is disbaled");
		}
		
		if (driver.findElement(biographyTextArea).isEnabled()) {
			System.out.println("biographyTextArea is enabled");
		} else {
			System.out.println("biographyTextArea is disbaled");
		}
		
		if (driver.findElement(job3Dropdown).isEnabled()) {
			System.out.println("job3Dropdown is enabled");
		} else {
			System.out.println("job3Dropdown is disbaled");
		}
		
		if (driver.findElement(interestsChecboxDisabled).isEnabled()) {
			System.out.println("interestsChecboxDisabled is enabled");
		} else {
			System.out.println("interestsChecboxDisabled is disbaled");
		}
		
		if (driver.findElement(slider2Slider).isEnabled()) {
			System.out.println("slider2Slider is enabled");
		} else {
			System.out.println("slider2Slider is disbaled");
		}
	}
	
//	@Test
	public void TC_03_Verify_Selected_Element() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement ageUnder18El = driver.findElement(ageUnder18Radio);
		Assert.assertFalse(ageUnder18El.isSelected());
		ageUnder18El.click();
		
		WebElement javaLangEl = driver.findElement(javaLanguageRadio);
		Assert.assertFalse(javaLangEl.isSelected());
		javaLangEl.click();
		sleepInSecond(3);
		
		Assert.assertTrue(ageUnder18El.isSelected());
		Assert.assertTrue(javaLangEl.isSelected());
		
		javaLangEl.click();
		sleepInSecond(3);
		Assert.assertFalse(javaLangEl.isSelected());
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test
	public void TC_04_Verify_Combined() {
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.id("email")).sendKeys("huyen@gmail.com");
		
		By passwordTextBox = By.id("new_password");
		By signupButton = By.id("create-account-enabled");
		
		// Verify nhap so
		driver.findElement(passwordTextBox).sendKeys("123");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// Verify nhap chu thuong
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("ab");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// Verify nhap chu hoa
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("AD");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// Verify nhap ki tu dac biet
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("#");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	
		// Verify nhap lon hon 8 ki tu
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("123456789");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		
		// Verify valid password
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("Huyen2405@");
		sleepInSecond(3);
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
	}
	
//	@BeforeMethod
//	public void openUrl() {
//		driver.get("https://automationfc.github.io/basic-form/index.html");
//	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
