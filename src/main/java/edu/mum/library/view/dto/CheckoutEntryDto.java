package edu.mum.library.view.dto;

import java.time.LocalDate;

import edu.mum.library.model.CheckoutEntry;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CheckoutEntryDto extends BaseDto<CheckoutEntry> {

	private StringProperty copyNumber;

	private SimpleObjectProperty<LocalDate> checkoutDate;
	private SimpleObjectProperty<LocalDate> dueDate;

	public CheckoutEntryDto(CheckoutEntry checkoutEntry) {
		this.copyNumber = new SimpleStringProperty(checkoutEntry.getBookCopy().getCopyNumber());
		this.checkoutDate = new SimpleObjectProperty<>(checkoutEntry.getCheckoutDate());
		this.dueDate = new SimpleObjectProperty<>(checkoutEntry.getDueDate());

//		this.memberId = new SimpleStringProperty(member.getMemberId());
//		this.firstName = new SimpleStringProperty(member.getFirstName());
//		this.lastName = new SimpleStringProperty(member.getLastName());
//		this.phoneNumber = new SimpleStringProperty(member.getPhoneNumber());
//
//		this.street = new SimpleStringProperty(member.getAddress().getStreet());
//		this.city = new SimpleStringProperty(member.getAddress().getCity());
//		this.state = new SimpleStringProperty(member.getAddress().getState());
//		this.zipcode = new SimpleStringProperty(member.getAddress().getZipcode());
	}
	public StringProperty copyNumberProperty() {
		return copyNumber;
	}
	public SimpleObjectProperty<LocalDate> checkoutDateProperty() {
		return checkoutDate;
	}
	public SimpleObjectProperty<LocalDate> dueDateProperty() {
		return dueDate;
	}
}
