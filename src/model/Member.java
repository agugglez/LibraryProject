package model;

public class Member extends Person {

	/**
	 *
	 */
	private static final long serialVersionUID = -9033791289938541323L;
	private String memberId;

	private CheckoutRecord checkoutRecord;

	public String getMemberId() {
		return memberId;
	}

	public CheckoutRecord getCheckoutRecord(){
		return checkoutRecord;
	}

	public Member(String memberId, String firstName, String lastName, String phoneNumber) {
		super(firstName, lastName, phoneNumber);
		// TODO Auto-generated constructor stub
	}

	public void printCheckoutRecord(){
		//not implemented
	}

	public void checkoutBook(BookCopy bc){
		//not implemented
	}
}
