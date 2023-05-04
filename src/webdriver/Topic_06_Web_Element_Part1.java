package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Part1 {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Web_Element() {
		WebElement element = driver.findElement(By.className(""));
		
		// Dung cho textbox/textarea/ dropdown editable
		// xoa du lieu truoc khi nhap text
		element.clear(); // **
		
		// Nhap du lieu
		element.sendKeys(""); // **
		
		element.click();// **
		
		String searchAttribute = element.getAttribute("placeholder");// **
		String emailValue = element.getAttribute("value");
		
		// get css value (background-color/ fonts/ fontsize/ color/ location/ position)
		// GUI
		String cssValue = element.getCssValue("background-color");// *
		
		element.getLocation(); // vi tri cua element so voi web (ben ngoai)
		
		element.getSize(); // get kich thuoc element (ben trong)
		
		element.getRect(); // Location + Size
		
		// chup hinh khi test case failed (generate report)
		// file/byte/ base64
		element.getScreenshotAs(OutputType.FILE); // *
		
		// Lay ra ten the HTML cua element => truyen vao cho 1 locator khac
		driver.findElement(By.id("email")).getTagName();
		driver.findElement(By.name("email")).getTagName();
		
		// truyen vao cho 1 locator khac
		String emailTextboxTagName = driver.findElement(By.cssSelector("email")).getTagName();
		driver.findElement(By.xpath("//" + emailTextboxTagName + "[@id='email']"));
		
		element.getTagName();
		
		// lay text tu err /success message
		element.getText();// **
		
		// verify xem 1 element co hien thi hay khong
		// pham vi tat ca element
		element.isDisplayed(); // **
		
		Assert.assertTrue(element.isDisplayed());// **
		Assert.assertFalse(element.isDisplayed());
		
		
		// Dung de verify xem element co thao tac dc hay khong
		Assert.assertTrue(element.isEnabled());// *
		Assert.assertFalse(element.isEnabled());
		
		
		// Dung de verify xem element dc chon hay chwa
		// Pham vi checkbox/Radio
		Assert.assertTrue(element.isSelected());// *
		Assert.assertFalse(element.isSelected());
		
		
		// Dung trong form
		element.submit();
		
	}
}
