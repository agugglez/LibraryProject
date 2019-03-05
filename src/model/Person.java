package model;

import java.io.Serializable;

public abstract class Person implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 793243928105436891L;
	private String firstName;
	private String lastName;
	private String phoneNumber;

	private Address personAddress;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Address getAddress() {
		return personAddress;
	}

	public Person(String firstName, String lastName, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}


}
