package com.automation.syncdriver;

import com.automation.customer.Customer;

public class SyncDriverTester {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		Customer Carl = new Customer("Carl", "Rager", "mrager1994@gmail.com", "607 Forest View Rd", "Linthicum", "Maryland", "21090", "FocusRS1", "wf0dp3th8g4115395" ,true );
		SyncDriver test = new SyncDriver(Carl);
		test.registerAllAccounts();
		test.quit();
	
	}
}
