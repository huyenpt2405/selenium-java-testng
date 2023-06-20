package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Mix_ImplicitWait_ExplicitWait {
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
		
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Found_Element() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://www.facebook.com/");
		
		System.out.println("Thoi gian bat dau tim " + getTimeStamp());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("Thoi gian ket thuc " + getTimeStamp());
	}

	@Test
	public void TC_02_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
		System.out.println("Thoi gian bat dau tim " + getTimeStamp());
		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Thoi gian ket thuc " + getTimeStamp());
		}
	}

	@Test
	public void TC_03_Mixed_Implicit_Explicit() {
		// 3.1 Implicit = Explicit
		// 3.2 Implicit < Explicit
		// 3.3 Implicit > Explicit
		// implicit khoong bao gio bi anh huong boi wait khac
		// implicit se anh huong den cac wait khac: explicit va fluent
		// vi truoc khi apply cac dieu kien deu phai findElement/findElements truoc
		
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		
//		// Implicit
//		System.out.println("Thoi gian bat dau tim implicit " + getTimeStamp());
//		try {
//			driver.findElement(By.cssSelector("input#selenium"));
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("Thoi gian ket thuc  implicit" + getTimeStamp());
//		}
		
		//Explicit
		System.out.println("Thoi gian bat dau tim explicit " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Thoi gian ket thuc  explicit" + getTimeStamp());
		}
	}
	
	public void TC_04_Not_Found_Explicit_By() {
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		System.out.println("Thoi gian bat dau tim explicit " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Thoi gian ket thuc  explicit" + getTimeStamp());
		}
	}
	
	@Test
	public void TC_04_Not_Found_Explicit_Element() {
		// 0s => NoSucElementException
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		System.out.println("Thoi gian bat dau tim explicit " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Thoi gian ket thuc  explicit" + getTimeStamp());
		}
	}
	
	@Test
	public void TC_05_Not_Found_Explicit_Element() {
		// 7s => NoSucElementException
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");
		System.out.println("Thoi gian bat dau tim explicit " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Thoi gian ket thuc  explicit" + getTimeStamp());
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String getTimeStamp() {
		return (new Date()).toString();
	}
}