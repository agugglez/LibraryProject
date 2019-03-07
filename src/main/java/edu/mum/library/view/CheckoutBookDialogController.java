package edu.mum.library.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.mum.library.common.LibraryException;
import edu.mum.library.service.LibraryService;
import edu.mum.library.view.base.BaseFxModalController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CheckoutBookDialogController extends BaseFxModalController {

	@Autowired
	private LibraryUiManager libraryUiManager;

	@Override
	public void postInit() {
		this.registerRequired("Member Id", memberIdField::getText);
		this.registerRequired("ISBN", isbnField::getText);
	}

	@FXML
	private TextField memberIdField;
	@FXML
	private TextField isbnField;

	private boolean okClicked = false;
	@Autowired
	private LibraryService libraryService;

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
		if (isInputValid()) {

			try {
				libraryService.checkoutBook(memberIdField.getText(), isbnField.getText());

				libraryUiManager.showCheckoutOverviewDialog(memberIdField.getText());

			} catch (LibraryException e) {
				fxViewManager.showError(this.getCurrentStage(), e.getMessage(), "Error", "Database error");
			}

			return;
		}
	}
}
