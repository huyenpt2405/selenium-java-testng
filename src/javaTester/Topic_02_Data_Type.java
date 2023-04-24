package javaTester;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_02_Data_Type {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Kiểu dữ liệu nguyên thủy (primitive)
		// Số nguyên: byte short int long
		byte bNumber = 127;
		short sNumber = 3200;
		int iNumber = 12343212;
		long lNumber = 1133321903;
		
		// Số thực: float double
		float studentPoint = 9.5f;
		double employeeSalary = 56.5d;
		
		// Logic: Boolean
		boolean status = true;
		
//		Kí tự: char
		char a = 'A';
		// Kiểu dữ liệu tham chiếu ( reference)
//		Class
		FirefoxDriver driver = new FirefoxDriver();
		
//		Interface
		
		WebElement firstNameElement;
		
//		Collection: List/Set/Queue
		List<WebElement> checkboxes = driver.findElements(By.cssSelector(""));
		
//		String
		String firstName = "first name";
		
//		Array
		String[] studentArray = {"huyen", "pham"};
		
//		Object
		Object people;
		
//		Map
		Map<String, Integer> studen = new HashMap<String, Integer>();

	}

}
