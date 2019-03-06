package edu.mum.library.view;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.mum.library.dataaccess.BookDao;
import edu.mum.library.model.Book;
import edu.mum.library.service.LibraryService;
import edu.mum.library.view.dto.BookDto;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BookEditDialogController extends LibraryFxModalEditController<BookDto> {

	@Override
	public void morePost() {
		if (this.entityDto != null) {
			copiesField.setDisable(true);
			isbnField.setDisable(true);
		}
		this.registerRequired("ISBN", isbnField::getText);
		this.registerRequired("Title", titleField::getText);
		this.registerRequired("availability", availabilityField::getText);
		this.registerRequired("Copies", copiesField::getText);
	}

	@FXML
	private TextField isbnField;
	@FXML
	private TextField titleField;
	@FXML
	private TextField availabilityField;
	@FXML
	private TextField copiesField;

	@Autowired
	private LibraryService libraryService;
	@Autowired
	private BookDao bookDao;

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			if (this.entityDto != null) {
				this.fromViewToDto();
				// Read from database to update
				Book book = bookDao.readById(this.entityDto.getIsbn());
				book.setIsbn(isbnField.getText());
				book.setTitle(titleField.getText());
				book.setAvailability(Integer.parseInt(availabilityField.getText()));
				bookDao.save(book);

			} else {
				Book book = new Book(isbnField.getText(), titleField.getText(),
						Integer.parseInt(availabilityField.getText()), new ArrayList<>());

				libraryService.addBook(book, Integer.parseInt(copiesField.getText()));

			}
			okClicked = true;
			this.getCurrentStage().close();
		}
	}

	@Override
	protected String moreCheck() {
		StringBuilder sb = new StringBuilder();
		// try to parse the postal code into an int.
		try {
			int available = Integer.parseInt(availabilityField.getText());
			if (available <= 0) {
				sb.append("No valid vailability (must be greater than 0)!\n");
			}
		} catch (NumberFormatException e) {
			sb.append("No valid vailability (must be an integer)!\n");
		}
		try {
			int copies = Integer.parseInt(copiesField.getText());
			if (copies < 0) {
				sb.append("No valid copies (must be greater than or equal to 0)!\n");
			}
		} catch (NumberFormatException e) {
			sb.append("No valid copies (must be an integer)!\n");
		}
		return sb.toString();
	}
}
