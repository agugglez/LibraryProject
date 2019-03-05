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

	public void addBookCopy() {
		String generated_id = title + "_copy"+ bookCopies.size() + 1;
		bookCopies.add(new BookCopy(generated_id, true));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
}
