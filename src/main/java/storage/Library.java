package storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Book;
import model.Member;

public class Library implements Serializable {

	public List<Member> getMembers() {
		return Collections.unmodifiableList(members);
	}

	public List<Book> getBooks() {
		return Collections.unmodifiableList(books);
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -6824278077021225904L;
	List<Member> members;
	List<Book> books;

	public Library() {
		super();

		members = new ArrayList<>();
		books = new ArrayList<>();
	}

}
