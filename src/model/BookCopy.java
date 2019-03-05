package model;

import java.io.Serializable;

public class BookCopy implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3987813167332764154L;

	private CheckoutEntry checkoutEntry;
	private String copyNumber;

	private Book originalBook;
	private boolean isAvailable;

	//package
	BookCopy(String copyNumber, Boolean isAvailable) {
		super();
		this.copyNumber = copyNumber;
		this.isAvailable = isAvailable;
	}

	public Book getBook() {
		return originalBook;
	}

	public CheckoutEntry getcheckoutEntry() {
		return checkoutEntry;
	}

	public String getCopyNumber() {
		return copyNumber;
	}

	public Boolean IsAvailable() {
		return isAvailable;
	}

}
