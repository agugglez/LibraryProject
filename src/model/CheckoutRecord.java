package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

	public void addCheckoutEntry(CheckoutEntry e){
		checkoutEntries.add(e);
	}

	public void checkoutBook(BookCopy bc){
		//not implemented
	}

	public void printCheckoutRecord(){
		//not implemented
	}

}
