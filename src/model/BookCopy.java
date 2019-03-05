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
	private String status;

	public BookCopy(String copyNumber, String status) {
		super();
		this.copyNumber = copyNumber;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

}
