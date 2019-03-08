package edu.mum.library.model;

import edu.mum.library.model.base.BaseEntity;

public class Address extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = -7402787440134026375L;

	private String city;
	private String state;
	private String street;
	private String zipcode;

	public Address(String street, String city, String state, String zipcode) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getStreet() {
		return street;
	}
	public String getZipcode() {
		return zipcode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "street:" + street + ", city:" + city + ", state:" + state + ", zipcode:" + zipcode + "";
	}

}
