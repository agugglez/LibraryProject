package edu.mum.library.model;

import edu.mum.library.model.base.BaseEntityWithPrimaryKey;

public class BookCopy extends BaseEntityWithPrimaryKey<String> {

	/**
	 *
	 */
	private static final long serialVersionUID = -3987813167332764154L;

	private CheckoutEntry checkoutEntry;

	public CheckoutEntry getCheckoutEntry() {
		return checkoutEntry;
	}

	public void setCheckoutEntry(CheckoutEntry checkoutEntry) {
		this.checkoutEntry = checkoutEntry;
	}

	public void checkout(CheckoutEntry ce) {
		this.setCheckoutEntry(ce);
		this.setAvailable(false);
	}
	public void returnBook()
	{
		this.setCheckoutEntry(null);
		this.setAvailable(true);
	}

	private String copyNumber;

	private boolean isAvailable;
	private Book originalBook;

	// package
	BookCopy(Book originalBook, String copyNumber, Boolean isAvailable) {
		super();
		this.originalBook = originalBook;
		this.copyNumber = copyNumber;
		this.isAvailable = isAvailable;
	}

	public Book getBook() {
		return originalBook;
	}

	public String getCopyNumber() {
		return copyNumber;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String getPrimaryKey() {
		return getCopyNumber();
	}

}
