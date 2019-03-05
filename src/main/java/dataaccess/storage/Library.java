package dataaccess.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Book;
import model.Member;

public class Library implements Serializable {

	public List<Member> getMembers() {
		return Collections.unmodifiableList(member);
	}

	public List<Book> getBooks() {
		return Collections.unmodifiableList(book);
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -6824278077021225904L;
	List<Member> member;
	List<Book> book;

	private Library() {
		super();

		member = new ArrayList<>();
		book = new ArrayList<>();
	}

}
