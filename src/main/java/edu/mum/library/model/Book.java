package edu.mum.library.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.mum.library.model.base.BaseEntityWithPrimaryKey;

public class Book extends BaseEntityWithPrimaryKey<String> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1132196726352622831L;
	private int availability;

	private List<Author> bookAuthors;

	private List<BookCopy> bookCopies;

	private String isbn;

	private String title;

	public Book(String title, String isbn, int availability, List<Author> authorList) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.availability = availability;

		bookCopies = new ArrayList<>();
		bookAuthors = new ArrayList<>();
	}

	public void addBookCopy() {
		String generated_id = title + "_copy" + bookCopies.size() + 1;
		bookCopies.add(new BookCopy(generated_id, true));
	}

	public int getAvailability() {
		return availability;
	}

	public List<Author> getBookAuthors() {
		return Collections.unmodifiableList(bookAuthors);
	}

	public List<BookCopy> getBookCopies() {
		return Collections.unmodifiableList(bookCopies);
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String getPrimaryKey() {
		return getIsbn();
	}

}
