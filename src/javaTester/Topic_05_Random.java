package javaTester;

import java.util.Random;

public class Topic_05_Random {
	public static void main(String[] args) {
		
		// Data type: Class/ Interface/ Collection / String/ Float ...
		Random random = (new Random());
		random.nextInt();
		random.nextInt(99999);
		System.out.println("Automation" + random.nextInt(99999) + "@gmail.com");
	}
}
