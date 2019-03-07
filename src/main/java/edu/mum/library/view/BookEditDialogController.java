package edu.mum.library.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.mum.library.common.LibraryException;
import edu.mum.library.common.NoAutoSettingGetting;
import edu.mum.library.dataaccess.BookDao;
import edu.mum.library.model.Author;
import edu.mum.library.model.Book;
import edu.mum.library.service.LibraryService;
import edu.mum.library.view.base.BaseFxModalController;
import edu.mum.library.view.dto.BookDto;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
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
		setAuthor();
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
	@FXML
	@NoAutoSettingGetting
	private TextArea authors;

	@Autowired
	private LibraryService libraryService;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private LibraryUiManager libraryUiManager;
	private List<Author> authorList = new ArrayList<>();

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}

	void setAuthor() {
		List<Author> list = new ArrayList<>();
		if (this.entityDto != null) {
			list.addAll(bookDao.readById(this.entityDto.getIsbn()).getBookAuthors());
		}
		list.addAll(this.authorList);
		String authorListText = list.stream().map(Object::toString).collect(Collectors.joining("\n"));
		authors.setText(authorListText);
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		try {
			if (isInputValid()) {
				if (this.entityDto != null) {
					this.fromViewToDto();
					// Read from database to update
					Book book = bookDao.readById(this.entityDto.getIsbn());
					book.setIsbn(isbnField.getText());
					book.setTitle(titleField.getText());
					book.setAvailability(Integer.parseInt(availabilityField.getText()));
					book.getBookAuthors().addAll(authorList);
					bookDao.save(book);

				} else {
					Book book = new Book(isbnField.getText(), titleField.getText(),
							Integer.parseInt(availabilityField.getText()), new ArrayList<>(authorList));

					libraryService.addBook(book, Integer.parseInt(copiesField.getText()));

				}
				okClicked = true;
				this.getCurrentStage().close();
			}
		} catch (LibraryException ex) {
			this.fxViewManager.showWarning(getCurrentStage(), ex.getMessage(), "Book Management",
					"Please correct Error");
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
		if (this.entityDto == null) {
			if (authorList.size() == 0) {
				sb.append("Please add author for the book!\n");
			}
		}
		return sb.toString();
	}

	@FXML
	public void addAuthor() {
		BaseFxModalController result = libraryUiManager.showAuthorEditDialogDialog();
		if (result.isOkClicked()) {
			authorList.add((Author) result.getReturnResult());
			setAuthor();
		}
	}
}
