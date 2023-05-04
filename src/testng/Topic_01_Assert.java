package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Topic_01_Assert {
	
	public static void main(String[] args) {
		
		String fullName = "Automation Testing";
		// Assert
		
		// Mong doi dieu kien tra ve dung
		Assert.assertTrue(3 < 4);
		Assert.assertTrue(fullName.contains("Automation"));
		
		// Mong doi false
		Assert.assertFalse(fullName.contains("manual"));
		
		// Mong doi 2 dieu kien bang nhau
		Assert.assertEquals(fullName, "Automation Testing");
	}
}
