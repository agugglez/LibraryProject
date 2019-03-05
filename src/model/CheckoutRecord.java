package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CheckoutRecord implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 8618292187178043371L;

	private List<CheckoutEntry> checkoutEntries;

	public CheckoutRecord() {
		super();
		checkoutEntries = new ArrayList<>();
	}

	public void checkoutBook(Member member, BookCopy bc){
		LocalDate checkoutDate = LocalDate.now();
		LocalDate dueDate = LocalDate.now().plusDays(bc.getBook().getAvailability());
		CheckoutEntry ce = new CheckoutEntry(checkoutDate, dueDate, member, bc);
		checkoutEntries.add(ce);
	}

	public List<CheckoutEntry> getCheckoutEntries(){
		return Collections.unmodifiableList(checkoutEntries);
	}

	public void printCheckoutRecord(){
		throw new NotImplementedException();
	}

}
