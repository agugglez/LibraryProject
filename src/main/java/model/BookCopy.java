package model;

import java.io.Serializable;

public class BookCopy implements Serializable {

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
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookCopy other = (BookCopy) obj;
		if (copyNumber == null) {
			if (other.copyNumber != null)
				return false;
		} else if (!copyNumber.equals(other.copyNumber))
			return false;
		return true;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((copyNumber == null) ? 0 : copyNumber.hashCode());
		return result;
	}

	public Boolean IsAvailable() {
		return isAvailable;
	}

}
