package model;

public class Member extends Person {

	/**
	 *
	 */
	private static final long serialVersionUID = -9033791289938541323L;
	private CheckoutRecord checkoutRecord;

	private String memberId;

	public Member(String memberId, String firstName, String lastName, String phoneNumber) {
		super(firstName, lastName, phoneNumber);
		// TODO Auto-generated constructor stub
	}

	public void checkoutBook(BookCopy bc){
		checkoutRecord.checkoutBook(this, bc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		return true;
	}

	public CheckoutRecord getCheckoutRecord(){
		return checkoutRecord;
	}

	public String getMemberId() {
		return memberId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		return result;
	}

	public void printCheckoutRecord(){
		checkoutRecord.printCheckoutRecord();
	}
}
