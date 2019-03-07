package edu.mum.library.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import edu.mum.library.model.base.BaseEntity;

public class CheckoutRecord extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 8618292187178043371L;

	private List<CheckoutEntry> checkoutEntries;

	public CheckoutRecord() {
		super();
		checkoutEntries = new ArrayList<>();
	}

	public void checkoutBook(Member member, BookCopy bc) {
		LocalDate checkoutDate = LocalDate.now();
		LocalDate dueDate = LocalDate.now().plusDays(bc.getBook().getAvailability());
		CheckoutEntry ce = new CheckoutEntry(checkoutDate, dueDate, member, bc);
		checkoutEntries.add(ce);
		bc.setAvailable(false);
	}

	public List<CheckoutEntry> getCheckoutEntries() {
		return Collections.unmodifiableList(checkoutEntries);
	}

	public void printCheckoutRecord() {
		String header =  "Checkout Date" + "\t" + "Due Date" + "\t" + "ISBN" + "\t"
				+ "Title" + "";
		System.out.println(header);
		String out = checkoutEntries.stream().map(e -> e.toString()).collect(Collectors.joining("\n"));
		System.out.println(out);
	}

}
