package com.example.demp;


public class Employee {
	
	private String firstName;
	private String LastName;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Employee(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		LastName = lastName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	

}
