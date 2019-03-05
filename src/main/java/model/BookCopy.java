package model;

import model.base.BaseEntityWithPrimaryKey;

public class BookCopy extends BaseEntityWithPrimaryKey<String> {

	/**
	 *
	 */
	private static final long serialVersionUID = -3987813167332764154L;

	private CheckoutEntry checkoutEntry;

	private String copyNumber;

	private boolean isAvailable;
	private Book originalBook;

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
	@Override
	public String getPrimaryKey() {
		return getCopyNumber();
	}

}
