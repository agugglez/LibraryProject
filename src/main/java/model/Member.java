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

	public CheckoutRecord getCheckoutRecord(){
		return checkoutRecord;
	}

	public String getMemberId() {
		return memberId;
	}

	public void printCheckoutRecord(){
		checkoutRecord.printCheckoutRecord();
	}
}