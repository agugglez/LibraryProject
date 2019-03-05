package edu.mum.library.dataaccess.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.mum.library.model.Book;
import edu.mum.library.model.Member;

public class Database implements Serializable {

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

	private Database() {
		super();

		member = new ArrayList<>();
		book = new ArrayList<>();
	}

}
