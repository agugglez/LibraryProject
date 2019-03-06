package edu.mum.library.view.dto;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import edu.mum.library.model.Address;
import edu.mum.library.model.Member;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MemberDto extends BaseDto<Member> {

	private StringProperty city;

	private StringProperty firstName;

	private StringProperty lastName;

	private StringProperty memberId;

	private StringProperty phoneNumber;

	private StringProperty state;

	private StringProperty street;

	private StringProperty zipcode;

	public MemberDto(Member member) {
		this.memberId = new SimpleStringProperty(member.getMemberId());
		this.firstName = new SimpleStringProperty(member.getFirstName());
		this.lastName = new SimpleStringProperty(member.getLastName());
		this.phoneNumber = new SimpleStringProperty(member.getPhoneNumber());

		this.street = new SimpleStringProperty(member.getAddress().getStreet());
		this.city = new SimpleStringProperty(member.getAddress().getCity());
		this.state = new SimpleStringProperty(member.getAddress().getState());
		this.zipcode = new SimpleStringProperty(member.getAddress().getZipcode());
	}
	public StringProperty firstNameProperty() {
		return firstName;
	}
	public String getCity() {
		return city.getValue();
	}
	public String getFirstName() {
		return firstName.getValue();
	}

	public String getLastName() {
		return lastName.getValue();
	}
	public String getMemberId() {
		return memberId.getValue();
	}
	public String getPhoneNumber() {
		return phoneNumber.getValue();
	}
	public String getState() {
		return state.getValue();
	}

	public String getStreet() {
		return street.getValue();
	}

	public String getZipcode() {
		return zipcode.getValue();
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public StringProperty memberIdProperty() {
		return memberId;
	}

	public void setCity(String city) {
		this.city.set(city);
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public void setMemberId(String memberId) {
		this.memberId.set(memberId);
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}

	public void setState(String state) {
		this.state.set(state);
	}

	public void setStreet(String street) {
		this.street.set(street);
	}

	public void setZipcode(String zipcode) {
		this.zipcode.set(zipcode);
	}

	public Member toMember() {
		Member member = new Member(this.getMemberId(), this.getFirstName(), this.getLastName(), this.getPhoneNumber());
		try {
			BeanUtils.copyProperties(member, this);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		member.setPersonAddress(new Address(this.getStreet(), this.getCity(), this.getState(), this.getZipcode()));
		return member;
	}
}
