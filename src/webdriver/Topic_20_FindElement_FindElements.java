package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_FindElement_FindElements {
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
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_FindElement() {
	//		Tìm thấy 1 element/node
	// Thao tác trực tiếp lên node đó
	// Vì nó tìm thấy nên không cần phải chờ hết 15s
		
	//		Tìm thấy nhiều element/node
	// Nó sẽ thao tác với Node đầu tiên
	// Không quan tâm các node còn lại
		
	//		Không tìm thấy element/node nào
	// Có cơ chế tìm lại (0.5s tìm lại 1 lần)
	// Trong thời gian tìm lại mà thấy element thì thỏa mãn đk => pass
	// Nếu trong 15s không thấy element:
	// + đánh fail TC
	// + Throw exception: NoSuchElementException
	}

	@Test
	public void TC_02_FindElements() {
	//		Tìm thấy 1 element/node
	// tìm thấy và lưu vào list 1 element
	// tìm thấy => không chờ hết 15s
		
	//		Tìm thấy nhiều element/node
	// tìm thấy và lưu vào list
		
	//		Không tìm thấy element/node nào
	// Có cơ chế tìm lại (0.5s tìm lại 1 lần)
	// Trong thời gian tìm lại mà thấy element thì thỏa mãn đk => pass
	// Nếu trong 15s không thấy element:
	// + không đánh fail TC => vẫn chạy step tiếp theo
	// + Trả về 1 empty list
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}