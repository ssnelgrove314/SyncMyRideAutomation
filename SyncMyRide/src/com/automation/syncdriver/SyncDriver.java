package com.automation.syncdriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.automation.customer.Customer;

/**
 * A ChromeDriver with added methods to automate my job.
 * @author Scott Snelgrove
 *
 */



public class SyncDriver extends ChromeDriver {

	private Customer customer;
	private boolean isAccountCreated;
	private Calendar cal = new GregorianCalendar();

	/**
	 * Constructs a SyncDriver and gets the Customer data from cust
	 * @param someasshole The customer
	 */

	public SyncDriver(Customer cust) {
		super();
		customer = cust;
		manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		isAccountCreated = false;

	}

	/**
	 * Goes through the process of registering an account with the given customer data.
	 * @throws InterruptedException 
	 */


	public void registerAllAccounts() {

		this.beginRegistration();
		if(!isAccountCreated) {
			this.accountDetails();
			this.loginFordAccount();
			this.setPreferedDealer();
		}
		this.loginToOrientation();
		this.fordOrientation();


		//			beginRegistration();
		//			accountDetails();
		//			
		//
		//			//For some fucking reason, it logs me out after I register??? Doesn't happen on the sync computer.
		//			// will have to check that out. I suppose I could add something.
		//
		//			setPreferedDealer();


	}

	public void beginRegistration() {
		get("https://owner.ford.com/tools/account/account"
				+ "-registration.html#/");


		if ( !this.findElements(By.cssSelector("#IPEinvL" )).isEmpty() ) {
			this.findElement(By.xpath("//*[@id=\"IPEinvL\"]/map/area[3]")).click();
		}

		findElement(By.id("fls-account-registration-first-name")).sendKeys(customer.getFirstName());
		findElement(By.id("fls-account-registration-last-name")).sendKeys(customer.getLastName());
		findElement(By.id("fls-email")).sendKeys(customer.getEmail());
		findElement(By.id("fls-email-confirm")).sendKeys(customer.getEmail());
		findElement(By.id("fls-account-registration-zip-code")).sendKeys(customer.getZipCode());
		findElement(By.cssSelector("#ng-app-SYNC > div.fls-content.clearer > div.ng-scope > div > div.col-2-3.content.ng-scope > form > input")).click();

		if( !this.findElements(By.cssSelector("#fls-password")).isEmpty() ) {
			isAccountCreated = true;
			System.out.println("account exists");
		}
		else {
			isAccountCreated = false;
			System.out.println("account doesn't exist");
		}
	}

	public void accountDetails() {

		try {
			this.findElement(By.cssSelector("#ng-app-SYNC > div.fls-content.clearer > div.ng-scope > div > div.col-2-3.content.ng-scope > form.fls-form.vehicle-confirm.ng-scope.ng-pristine.ng-valid > ul > li:nth-child(2) > a")).click();
		}
		catch (Exception e) {}

		findElement(By.id("vin")).clear();
		findElement(By.id("vin")).sendKeys(customer.getVin());
		findElement(By.id("vin")).submit();

		try {
			findElement(By.id("fls-street-address")).sendKeys(customer.getStreetAddress());
			findElement(By.id("fls-city")).sendKeys(customer.getCity());
			new Select(findElement(By.id("fls-state"))).selectByVisibleText(customer.getState());
		}
		catch (Exception e) {}

		findElement(By.id("fls-newPassword")).sendKeys(customer.getPassword());
		findElement(By.id("fls-confirm-password")).sendKeys(customer.getPassword());
		findElement(By.id("fls-get-updates")).click();
		findElement(By.id("fls-newPassword")).submit();
	}

	public void loginFordAccount() {
		this.get("https://owner.ford.com/tools/account/sign-in.html");
		this.findElement(By.id("fls-username")).sendKeys(customer.getEmail());
		this.findElement(By.id("fls-password")).sendKeys(customer.getPassword());
		this.findElement(By.id("fls-password")).submit();
	}

	public void setPreferedDealer() {
		try {
			findElement(By.cssSelector("#ng-app-SYNC > div.fls-content.clearer > div > div.fls-home-main > div.hero.home-hero > div > div.hero-vehicle-info-section.hero-preferred-dealer > div:nth-child(1) > div.fls-preferred-dealer-empty > input")).click();
			findElement(By.id("dealerSearchBox")).sendKeys("21045");
			this.findElement(By.id("dealerSearchBox")).submit();

			this.findElement(By.cssSelector("#fls-dealer-search-results > div.ng-isolate-scope > section > ul > li.fls-dealer-search-results-item.scroll-item-0 > div.fls-dealer-info > h3 > a")).click();
			this.findElement(By.cssSelector("#ng-app-SYNC > div.fls-content.clearer > div > div:nth-child(2) > div.fls-dealer-header.authenticated-header > div.fls-set-preferred-dealer.set-preferred-dealer.ng-scope.ng-isolate-scope > a")).click();
			this.findElement(By.cssSelector("#fls-set-preferred-dealer-modal > div > div.fls-set-preferred-dealer-confirmation > div > input")).click();
		}
		catch (Exception e){}


	}

	public void loginToOrientation() {
		this.get("https://www.wslb2b.ford.com/login.cgi?WslIP=167.102.190.28&portstripping=yes&back=https://www.nvousa.ford.com:13420/nvo");
		this.findElement(By.id("FSNloginUserIdInput")).sendKeys("R-Mar606");
		this.findElement(By.id("FSNloginPasswordInput")).sendKeys("appleford123");
		this.findElement(By.id("FSNloginPasswordInput")).submit();
		this.findElement(By.name("SubmitButton")).click();
	}

	public void fordOrientation() {
		this.get("https://www.nvousa.ford.com/nvo/");
		//TODO add check
		this.findElement(By.id("textPreparedFor")).sendKeys(customer.getFirstName() + " " + customer.getLastName());
		this.findElement(By.id("textEmail")).sendKeys(customer.getEmail());
		this.findElement(By.id("textPreparedBy")).sendKeys("Scott Snelgrove");
		//TODO Add prepared by salesman
		this.findElement(By.id("inputVin")).sendKeys(customer.getVin());
		this.findElement(By.cssSelector("#startGuideBtn > div.buttonTextHolderR > p")).click();

		//Waits for the Loading overlay to   
		//		while( ! this.findElement(By.id("overlay")).isDisplayed() ) {
		//			
		//		}

		//TODO make salesperson or something
		this.findElement(By.id("contactText")).sendKeys("Scott Snelgrove");

		//		this.findElement(By.id("followUpDate"))
		//TODO enable the ability for salesperson to set up a sync time.
		this.findElement(By.id("followUpTime")).sendKeys("Call to Schedule");
		this.findElement(By.id("maintContact")).sendKeys("Quick Lane");
		this.findElement(By.id("firstMaintTime")).sendKeys("Call to Schedule");

		((JavascriptExecutor)this).executeScript ("document.getElementById('firstMaintDate').removeAttribute('readonly',0);");
		this.findElement(By.id("firstMaintDate")).clear();
		this.findElement(By.id("firstMaintDate")).sendKeys(this.getDateSixMonths());

		this.findElement(By.id("syncId") ).sendKeys( customer.getEmail() );

		//		this.findElement(By.id("fistMaintDate")).sendKeys("02/06/2017");
		//		this.findElement(By.id("syncID")).sendKeys(customer.getEmail());
		this.findElement(By.cssSelector("#emailBtn > div")).click();
		this.findElement(By.id("emailCC")).sendKeys("checklist@appleford.com");
		this.findElement(By.id("sendBtnText")).click();
		this.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try {
		this.findElement(By.cssSelector("body > div:nth-child(8) > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button > span")).click();
		}
		catch (Exception e) {
			this.fordOrientation();
		}
	}

	public String getDateSixMonths() {
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setTimeZone(cal.getTimeZone());

		cal.add(Calendar.MONTH, 6);
		if( cal.get(Calendar.DAY_OF_WEEK) == 1) {
			cal.add(Calendar.DATE, 1);
		}

		return (dateFormat.format(cal.getTime()));
	}


	public void lincolnOrientation() {

	}


}
