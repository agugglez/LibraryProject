package edu.mum.library.model;

import edu.mum.library.model.base.BaseEntity;

public abstract class Person extends BaseEntity {

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
