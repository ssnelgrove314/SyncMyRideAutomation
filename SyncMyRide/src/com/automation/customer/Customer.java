package com.automation.customer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Customer {

	private String firstName1;
	private String lastName1;
	private String email1;
	private String zipCode1;
	private String password1;
	private String streetAddress1;
	private String city1;
	private String state1;
	private String vin1;
	private boolean isFord;

	/**
	 * 
	 * @param firstName First name of the customer
	 * @param lastName Last name of the customer
	 * @param email customer email
	 * @param street the street address, ex: 1234 Pallettown Dr
	 * @param city the city
	 * @param state the state
	 * @param zip the zip code
	 * @param pass the password
	 */
	public Customer(String firstName, 
			String lastName, String email, String street, 
			String city, String state, String zip, String pass, String vin, boolean ford) {
//		this.verifyData();
		firstName1 = firstName;
		lastName1 = lastName;
		email1 = email;
		zipCode1 = zip;
		password1 = pass;
		streetAddress1 = street;
		city1 = city;
		state1 = US.parse( this.getState() ).unnabreviated;
		vin1 = vin;
		isFord = ford;

	}

	private void verifyData() throws InvalidDataException {
		
		boolean firstNameVerify = firstName1.length() != 0;
		
		boolean lastNameVerify = lastName1.length() != 0;
		
		boolean emailVerify = email1.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		
		boolean zipVerify = ( zipCode1.length() == 5);
		
		boolean passwordVerify = ( password1.length() >= 6 || password1.length() <= 15 );
		
		boolean vinVerify = ( vin1.length() == 17 );
		
		boolean stateVerify = (!state1.equals(null));
		
	}
	
	
	
	public String toString() {
		String spc = "\", \"";
		return ("Customer " + 
				getFirstName() + " = new Customer(\"" + getFirstName() + spc + 
				getLastName() + spc + getEmail() + spc + getStreetAddress() + spc + 
				getCity() + spc + getState() + spc + getZipCode() + spc + 
				getPassword() + spc + getVin() + "\" ," + isFord() + " );");

		
	}

	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstName1;
	}

	public String getLastName() {
		// TODO Auto-generated method stub
		return lastName1;
	}

	public String getZipCode() {
		// TODO Auto-generated method stub
		return zipCode1;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return email1;
	}

	public String getStreetAddress() {
		// TODO Auto-generated method stub
		return streetAddress1;
	}

	public String getCity() {
		// TODO Auto-generated method stub
		return city1;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password1;
	}

	public String getState() {
		// TODO Auto-generated method stub
		return state1;
	}
	
	public String getVin() {
		return vin1;
	}
	
	public boolean isFord() {
		return isFord;
	}
	
	public void setPassword(String pass) {
		password1 = pass;
	}
	
	public String getDateSixMonths() {
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setTimeZone(cal.getTimeZone());

		cal.add(Calendar.MONTH, 6);
		if( cal.get(Calendar.DAY_OF_WEEK) == 1) {
			cal.add(Calendar.DATE, 1);
		}

		return (dateFormat.format(cal.getTime()));
	}
	
	public String generatePassword() {
		return vin1.substring(9, 17);
	}


}

