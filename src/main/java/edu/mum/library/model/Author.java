package edu.mum.library.model;

public class Author extends Person{

	/**
	 *
	 */
	private static final long serialVersionUID = -3707643831540799292L;
	private String credentials;
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public void setShortBio(String shortBio) {
		this.shortBio = shortBio;
	}

	@Override
	public String toString() {
		return "firstName :" + firstName
				+ ", lastName: " + lastName + ", personAddress: (" + personAddress + "), phoneNumber:" + phoneNumber + "]";
	}

	private String shortBio;

	public Author(String firstName, String lastName, String phoneNumber) {
		super(firstName, lastName, phoneNumber);
		// TODO Auto-generated constructor stub
	}

	public String getCredentials() {
		return credentials;
	}

	public String getShortBio() {
		return shortBio;
	}
}
