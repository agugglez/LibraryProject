package edu.mum.library.view;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.mum.library.model.Address;
import edu.mum.library.model.Author;
import edu.mum.library.view.dto.AuthorDto;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class AuthorEditDialogController extends LibraryFxModalEditController<AuthorDto> {

	@Override
	protected void morePost() {
		if (entityDto != null) {
		}
		this.registerRequired("First Name", firstNameField::getText);
		this.registerRequired("Last Name", lastNameField::getText);
	}

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField zipcodeField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField stateField;
	@FXML
	private TextField phoneNumberField;
	@FXML
	private TextField credentialsField;
	@FXML
	private TextArea shortBioField;

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 *
	 * @return
	 */
	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			Author author = new Author(firstNameField.getText(), lastNameField.getText(), phoneNumberField.getText());
			author.setPersonAddress(new Address(streetField.getText(), cityField.getText(), stateField.getText(),
					zipcodeField.getText()));
			// libraryService.addMember(member);
			author.setCredentials(credentialsField.getText());
			author.setShortBio(shortBioField.getText());
			okClicked = true;
			this.setReturnResult(author);
			this.getCurrentStage().close();
		}
	}

	@Override
	protected String moreCheck() {
		if (!StringUtils.isEmpty(zipcodeField.getText())) {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(zipcodeField.getText());
			} catch (NumberFormatException e) {
				return "No valid postal code (must be an integer)!\n";
			}
		}
		return "";
	}

}
