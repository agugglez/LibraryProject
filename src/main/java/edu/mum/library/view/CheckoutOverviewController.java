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

	@FXML
	private TableColumn<CheckoutEntryDto, LocalDate> checkoutColumn;
	@FXML
	private TableColumn<CheckoutEntryDto, LocalDate> dueColumn;
	@FXML
	private TableColumn<CheckoutEntryDto, String> copyNumberColumn;

	@Autowired
	private MemberDao memberDao;

	private String memberId;

	List<CheckoutEntryDto> getAllMemberList() {
		Member member = memberDao.readById(memberId);
		return member.getCheckoutRecord().getCheckoutEntries().stream().map(m -> new CheckoutEntryDto(m))
				.collect(Collectors.toList());
	}

	@Override
	public void postInit() {
		this.memberId = (String) UserObjectForView.getParamFromStage(this.getCurrentStage());
		personTable.setItems(FXCollections.observableArrayList(getAllMemberList()));
		configureColumnForTableView();
	}

	private void configureColumnForTableView() {

		checkoutColumn.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
		dueColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		copyNumberColumn.setCellValueFactory(new PropertyValueFactory<>("copyNumber"));
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit details
	 * for a new person.
	 */
	@FXML
	private void handleNewPerson() {

		onWindowClose();
	}
}
