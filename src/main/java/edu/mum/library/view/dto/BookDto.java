package edu.mum.library.view.dto;

import java.util.ArrayList;

import edu.mum.library.model.Book;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookDto extends BaseDto<Book> {

	private IntegerProperty availability;
	private StringProperty isbn;
	private IntegerProperty numberofCopies;
	private StringProperty title;

	public BookDto(Book member) {
		this.isbn = new SimpleStringProperty(member.getIsbn());
		this.title = new SimpleStringProperty(member.getTitle());
		this.availability = new SimpleIntegerProperty(member.getAvailability());
		this.numberofCopies = new SimpleIntegerProperty(member.getBookCopies().size());
	}

	public StringProperty isbnProperty() {
		return isbn;
	}

	public StringProperty titleProperty() {
		return title;
	}

	public IntegerProperty availabilityProperty() {
		return availability;
	}

	public IntegerProperty numberofCopiesProperty() {
		return numberofCopies;
	}

	public int getAvailability() {
		return availability.get();
	}

	public String getIsbn() {
		return isbn.get();
	}

	public int getNumberofCopies() {
		return numberofCopies.get();
	}

	public String getTitle() {
		return title.get();
	}

	public void setAvailability(int availability) {
		this.availability.set(availability);
	}

	public void setIsbn(String isbn) {
		this.isbn.set(isbn);
	}

	public void setNumberofCopies(int numberofCopies) {
		this.numberofCopies.set(numberofCopies);
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public Book toBook() {
		Book member = new Book(this.isbn.getValue(), this.title.getValue(), this.availability.getValue().intValue(),
				new ArrayList<>());
		return member;
	}
}
