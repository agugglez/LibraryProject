package edu.mum.library.view;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.mum.library.dataaccess.MemberDao;
import edu.mum.library.model.Member;
import edu.mum.library.view.base.BaseFxModalController;
import edu.mum.library.view.dto.CheckoutEntryDto;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CheckoutOverviewController extends BaseFxModalController {
	@FXML
	private TableView<CheckoutEntryDto> personTable;

	// this.isbn = new SimpleStringProperty(member.getIsbn());
	// this.title = new SimpleStringProperty(member.getTitle());
	// this.availability = new SimpleIntegerProperty(member.getAvailability());
	// this.numberofCopies = new
	// SimpleIntegerProperty(member.getBookCopies().size());
	@FXML
	private TableColumn<CheckoutEntryDto, LocalDate> checkoutColumn;
	@FXML
	private TableColumn<CheckoutEntryDto, LocalDate> dueColumn;
	@FXML
	private TableColumn<CheckoutEntryDto, String> copyNumberColumn;
	// @FXML
	// private TableColumn<BookDto, String> numberofCopiesColumn;

	@Autowired
	private LibraryUiManager libraryUiManager;

	// @Autowired
	// private LibraryService libraryService;

	@Autowired
	private MemberDao memberDao;

	private String memberId;

	List<CheckoutEntryDto> getAllMemberList() {
		Member member = memberDao.readById(memberId);
		return member.getCheckoutRecord().getCheckoutEntries().stream().map(m -> new CheckoutEntryDto(m))
				.collect(Collectors.toList());
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {




		// Listen for selection changes and show the person details when
		// changed.
		// personTable.getSelectionModel().selectedItemProperty()
		// .addListener((observable, oldValue, newValue) ->
		// showPersonDetails(newValue));

	}

	@Override
	public void postInit() {
		this.memberId = (String) ((UserObjectForView) this.getCurrentStage().getUserData()).getParameter();
		personTable.setItems(FXCollections.observableArrayList(getAllMemberList()));
		preJava8();
	}

	private void preJava8() {

		checkoutColumn.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
		dueColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		copyNumberColumn.setCellValueFactory(new PropertyValueFactory<>("copyNumber"));
		//numberofCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("numberofCopies"));
		// firstNameColumn
		// .setCellValueFactory(new Callback<CellDataFeatures<MemberDto,
		// String>, ObservableValue<String>>() {
		//
		// @Override
		// public ObservableValue<String> call(CellDataFeatures<MemberDto,
		// String> param) {
		// return param.getValue().firstNameProperty();
		// }
		// });

		// lastNameColumn
		// .setCellValueFactory(new Callback<CellDataFeatures<MemberDto,
		// String>, ObservableValue<String>>() {
		//
		// @Override
		// public ObservableValue<String> call(CellDataFeatures<MemberDto,
		// String> param) {
		// return param.getValue().lastNameProperty();
		// }
		// });
		// memberIdColumn
		// .setCellValueFactory(new Callback<CellDataFeatures<MemberDto,
		// String>, ObservableValue<String>>() {
		//
		// @Override
		// public ObservableValue<String> call(CellDataFeatures<MemberDto,
		// String> param) {
		// return param.getValue().memberIdProperty();
		// }
		// });
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleNewPerson() {

		onWindowClose();
	}
}
