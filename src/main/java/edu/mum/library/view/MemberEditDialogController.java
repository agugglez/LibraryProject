package edu.mum.library.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.mum.library.dataaccess.MemberDao;
import edu.mum.library.model.Address;
import edu.mum.library.model.Member;
import edu.mum.library.service.LibraryService;
import edu.mum.library.servicebb.DateUtil;
import edu.mum.library.view.dto.MemberDto;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class MemberEditDialogController extends BaseFxModalController {

	@Override
	public void postInit() {
		this.person = (MemberDto) ((UserObjectForView) this.getCurrentStage().getUserData()).getParameter();
		if (person != null) {
			memberIdField.setText(person.getMemberId());
			memberIdField.setDisable(true);
			firstNameField.setText(person.getFirstName());
			lastNameField.setText(person.getLastName());
			streetField.setText(person.getStreet());
			postalCodeField.setText(person.getZipcode());
			cityField.setText(person.getCity());
			phoneNumberField.setText(person.getPhoneNumber());
			stateField.setText(person.getState());
			// phoneNumberField.setPromptText("dd.mm.yyyy");
		}
	}

	@FXML
	private TextField memberIdField;

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField postalCodeField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField stateField;
	@FXML
	private TextField phoneNumberField;

	private MemberDto person;

	private boolean okClicked = false;
	@Autowired
	private LibraryService libraryService;
	@Autowired
	private MemberDao memberDao;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
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
		if (true) {// isInputValid()
			if (person != null) {
				// person.setMemberId(memberIdField.getText());
				person.setFirstName(firstNameField.getText());
				person.setLastName(lastNameField.getText());
				person.setStreet(streetField.getText());
				person.setZipcode(postalCodeField.getText());
				person.setCity(cityField.getText());
				person.setPhoneNumber(phoneNumberField.getText());
				person.setState(stateField.getText());
				// TODO
				// 				// Read from database to update
				Member memeber = memberDao.readById(person.getMemberId());

				memeber.setFirstName(firstNameField.getText());
				memeber.setLastName(lastNameField.getText());
				memeber.getAddress().setStreet(streetField.getText());
				memeber.getAddress().setZipcode(postalCodeField.getText());
				memeber.getAddress().setCity(cityField.getText());
				memeber.setPhoneNumber(phoneNumberField.getText());
				memeber.getAddress().setState(stateField.getText());
				memberDao.save(memeber);

			} else {
				Member member = new Member(memberIdField.getText(), firstNameField.getText(), lastNameField.getText(),
						phoneNumberField.getText());
				member.setPersonAddress(new Address(streetField.getText(), cityField.getText(), stateField.getText(),
						postalCodeField.getText()));

				// person.setLastName();
				// person.setStreet(streetField.getText());
				// person.setZipcode(postalCodeField.getText());
				// person.setCity(cityField.getText());
				// person.setPhoneNumber();
				// person.setState(stateField.getText());
				libraryService.addMember(member);

			}
			okClicked = true;
			this.getCurrentStage().close();
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		this.getCurrentStage().close();
	}

	/**
	 * Validates the user input in the text fields.
	 *
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
			errorMessage += "No valid first name!\n";
		}
		if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
			errorMessage += "No valid last name!\n";
		}
		if (streetField.getText() == null || streetField.getText().length() == 0) {
			errorMessage += "No valid street!\n";
		}

		if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
			errorMessage += "No valid postal code!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(postalCodeField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid postal code (must be an integer)!\n";
			}
		}

		if (cityField.getText() == null || cityField.getText().length() == 0) {
			errorMessage += "No valid city!\n";
		}

		if (phoneNumberField.getText() == null || phoneNumberField.getText().length() == 0) {
			errorMessage += "No valid birthday!\n";
		} else {
			if (!DateUtil.validDate(phoneNumberField.getText())) {
				errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
			}
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
