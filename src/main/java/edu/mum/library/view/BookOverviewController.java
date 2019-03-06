package edu.mum.library.view;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.mum.library.dataaccess.BookDao;
import edu.mum.library.view.dto.BookDto;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BookOverviewController extends BaseFxController {
	@FXML
	private TableView<BookDto> personTable;

//	this.isbn = new SimpleStringProperty(member.getIsbn());
//	this.title = new SimpleStringProperty(member.getTitle());
//	this.availability = new SimpleIntegerProperty(member.getAvailability());
//	this.numberofCopies = new SimpleIntegerProperty(member.getBookCopies().size());
	@FXML
	private TableColumn<BookDto, String> isbnColumn;
	@FXML
	private TableColumn<BookDto, String> titleColumn;
	@FXML
	private TableColumn<BookDto, String> availabilityColumn;
	@FXML
	private TableColumn<BookDto, String> numberofCopiesColumn;

	@Autowired
	private LibraryUiManager libraryUiManager;

	// @Autowired
	// private LibraryService libraryService;

	@Autowired
	private BookDao bookDao;

	List<BookDto> getAllMemberList() {
		return bookDao.getAll().stream().map(m -> new BookDto(m)).collect(Collectors.toList());
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		personTable.setItems(FXCollections.observableArrayList(getAllMemberList()));
		preJava8();

		// Listen for selection changes and show the person details when
		// changed.
//		personTable.getSelectionModel().selectedItemProperty()
//				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));

	}

	private void preJava8() {

		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));
		numberofCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("numberofCopies"));
//		firstNameColumn
//				.setCellValueFactory(new Callback<CellDataFeatures<MemberDto, String>, ObservableValue<String>>() {
//
//					@Override
//					public ObservableValue<String> call(CellDataFeatures<MemberDto, String> param) {
//						return param.getValue().firstNameProperty();
//					}
//				});

//		lastNameColumn
//				.setCellValueFactory(new Callback<CellDataFeatures<MemberDto, String>, ObservableValue<String>>() {
//
//					@Override
//					public ObservableValue<String> call(CellDataFeatures<MemberDto, String> param) {
//						return param.getValue().lastNameProperty();
//					}
//				});
//		memberIdColumn
//				.setCellValueFactory(new Callback<CellDataFeatures<MemberDto, String>, ObservableValue<String>>() {
//
//					@Override
//					public ObservableValue<String> call(CellDataFeatures<MemberDto, String> param) {
//						return param.getValue().memberIdProperty();
//					}
//				});
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			String isbn = personTable.getItems().get(selectedIndex).getIsbn();
			personTable.getItems().remove(selectedIndex);
			// TODO
			this.bookDao.delete(isbn);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(application.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleNewPerson() {
		BookDto tempPerson = null;// new Person();
		if (libraryUiManager.showBookEditDialog(tempPerson)) {
			personTable.setItems(FXCollections.observableArrayList(getAllMemberList()));
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	@FXML
	private void handleEditPerson() {
		BookDto selectedPerson = personTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = libraryUiManager.showBookEditDialog(selectedPerson);
			if (okClicked) {

			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(application.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}
}
