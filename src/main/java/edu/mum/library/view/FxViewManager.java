package edu.mum.library.view;

import org.springframework.stereotype.Component;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

@Component
public class FxViewManager extends BaseUI {

	public void showError(Stage stage, String errorMessage, String title, String headerText) {
		showAlert(stage, errorMessage, title, headerText, AlertType.ERROR);
	}

	public void showAlert(Stage stage, String errorMessage, String title, String headerText, AlertType type) {
		Alert alert = new Alert(type);
		alert.initOwner(stage);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(errorMessage);

		alert.showAndWait();
	}
	public void showWarning(Stage stage, String errorMessage, String title, String headerText) {
		showAlert(stage, errorMessage, title, headerText, AlertType.WARNING);
	}
	public void showInformation(Stage stage, String message, String title, String headerText) {
		showAlert(stage, message, title, headerText, AlertType.INFORMATION);
	}
}
