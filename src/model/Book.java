package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book implements Serializable {

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

	public void addBookCopy(BookCopy bc) {
		bookCopies.add(bc);
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
}
