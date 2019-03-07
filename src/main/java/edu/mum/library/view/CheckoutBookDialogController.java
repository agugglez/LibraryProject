package edu.mum.library.view;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.mum.library.common.LibraryException;
import edu.mum.library.dataaccess.BookDao;
import edu.mum.library.dataaccess.MemberDao;
import edu.mum.library.service.LibraryService;
import edu.mum.library.view.base.BaseFxModalController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CheckoutBookDialogController extends BaseFxModalController {

	@Autowired
	private LibraryUiManager libraryUiManager;

	@Override
	public void postInit() {
		// this.person = (BookDto) ((UserObjectForView)
		// this.getCurrentStage().getUserData()).getParameter();
		// if (person != null) {
		// memberIdField.setText(person.getIsbn());
		// memberIdField.setDisable(true);
		// isbnField.setText(person.getTitle());
		//// availabilityField.setText(Integer.toString(person.getAvailability()));
		//// copiesField.setDisable(true);
		//// copiesField.setText(Integer.toString(person.getNumberofCopies()));
		// // phoneNumberField.setPromptText("dd.mm.yyyy");
		// }
	}

	@FXML
	private TextField memberIdField;
	@FXML
	private TextField isbnField;
	// @FXML
	// private TextField availabilityField;
	// @FXML
	// private TextField copiesField;

	// private BookDto person;

	private boolean okClicked = false;
	@Autowired
	private LibraryService libraryService;
	@Autowired
	private BookDao bookDao;

	@Autowired
	private MemberDao memberDao;

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 *
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {//

			try {
				libraryService.checkoutBook(memberIdField.getText(), isbnField.getText());

				libraryUiManager.showCheckoutOverviewDialog(memberIdField.getText());

			} catch (LibraryException e) {
				fxViewManager.showError(this.getCurrentStage(), e.getMessage(), "Error", "Database error");
			}

			return;

			// SEARCH ISBN EXISTS

			// if (person != null) {
			// // person.setMemberId(memberIdField.getText());
			// // isbnIdField.setText(person.getIsbn());
			// // titleField.setText(person.getTitle());
			// //
			// availabilityField.setText(Integer.toString(person.getAvailability()));
			// // copiesField.setDisable(true);
			// //
			// copiesField.setText(Integer.toString(person.getNumberofCopies()));
			// person.setIsbn(memberIdField.getText());
			// person.setTitle(isbnField.getText());
			//// person.setAvailability(Integer.parseInt(availabilityField.getText()));
			// // Read from database to update
			// Book book = bookDao.readById(person.getIsbn());
			// book.setIsbn(memberIdField.getText());
			// book.setTitle(isbnField.getText());
			// book.setAvailability(Integer.parseInt(availabilityField.getText()));
			// bookDao.save(book);
			//
			// } else {
			// Book book = new Book(memberIdField.getText(),
			// isbnField.getText(),
			// Integer.parseInt(availabilityField.getText()), new
			// ArrayList<>());
			//
			// // person.setLastName();
			// // person.setStreet(streetField.getText());
			// // person.setZipcode(postalCodeField.getText());
			// // person.setCity(cityField.getText());
			// // person.setPhoneNumber();
			// // person.setState(stateField.getText());
			// libraryService.addBook(book,
			// Integer.parseInt(copiesField.getText()));
			//
			// }

			// okClicked = true;
			// this.getCurrentStage().close();
		}
	}

	/**
	 * Validates the user input in the text fields.
	 *
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";
		if (StringUtils.isEmpty(memberIdField.getText())) {
			errorMessage += "PLease input memeber Id Field \n";
		}
		if (StringUtils.isEmpty(isbnField.getText())) {
			errorMessage += "PLease input ISBN Field \n";
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(this.getCurrentStage());
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}
}
