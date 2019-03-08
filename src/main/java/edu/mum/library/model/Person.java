package edu.mum.library.model;

import edu.mum.library.model.base.BaseEntity;

public abstract class Person extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 793243928105436891L;
	protected String firstName;
	protected String lastName;
	protected Address personAddress;

	protected String phoneNumber;

	public Person(String firstName, String lastName, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return personAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Address getPersonAddress() {
		return personAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPersonAddress(Address personAddress) {
		this.personAddress = personAddress;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
