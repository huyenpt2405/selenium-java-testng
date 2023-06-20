package testng;

import org.testng.annotations.Test;

public class Topic_03_Priority {
	// TestNG => default priority = alphabet
//	@Test(priority = 1)
	@Test(description = "Jira 07 - Create new Employee and verify the employee created success") // lien ket voi ticket ID
	public void EndUser_01_Create_New_Employee() {

	}

//	@Test(priority = 2)
	@Test
	public void EndUser_02_View_Employee() {

	}

	@Test(enabled = false, description = "Jira 07 - Update Employee and save updated infor success") // disable TC
	public void EndUser_03_Update_Employee() {

	}

	@Test
	public void EndUser_04_Move_Employee() {

	}
}
