package edu.mum.library.view.base;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import edu.mum.library.view.BaseUI;
import edu.mum.library.view.FxViewManager;
import edu.mum.library.view.UserObjectForView;
import javafx.stage.Stage;

public abstract class BaseFxController extends BaseUI {

	@Autowired
	protected FxViewManager fxViewManager;

	protected Stage currentStage;

	public Stage getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(Stage currentStage) {
		this.currentStage = currentStage;
	}

	protected Object closeResult;

	public Object getCloseResult() {
		return closeResult;
	}

	public void setCloseResult(Object closeResult) {
		this.closeResult = closeResult;
	}

	public void onWindowClose() {

		this.getCurrentStage().setUserData(new UserObjectForView(this, null));

	}

	public void postInit() {

	}

	public void closeWindow() {
		this.getCurrentStage().close();
	}

	protected Object returnResult;

	public Object getReturnResult() {
		return returnResult;
	}

	protected void setReturnResult(Object returnResult) {
		this.returnResult = returnResult;
	}
	
	protected static class CheckField {
		private Supplier<String> supplier;

		public CheckType getCheckType() {
			return checkType;
		}

		private String name;
		private CheckType checkType;

		public Supplier<String> getSupplier() {
			return supplier;
		}

		public CheckField(String name, Supplier<String> supplier) {
			this(name, supplier, CheckType.REQUIRED);
		}

		public CheckField(String name, Supplier<String> supplier, CheckType checkType) {
			super();
			this.supplier = supplier;
			this.name = name;
			this.checkType = checkType;
		}

		public String getName() {
			return name;
		}
	}

	private static interface ICheck {
		boolean check(CheckField val);

		public String formatErrorMessage(String name);
	}

	private static class RequiredCheck implements ICheck {

		@Override
		public boolean check(CheckField val) {
			return !StringUtils.isEmpty(val.getSupplier().get());
		}

		@Override
		public String formatErrorMessage(String name) {
			return "No valid " + name + "!\n";
		}
	}

	public static enum CheckType {
		REQUIRED(new RequiredCheck());
		CheckType(ICheck check) {
			this.check = check;
		}

		private ICheck check;

		public String check(CheckField val) {
			if (!check.check(val)) {
				return check.formatErrorMessage(val.name);

			}
			return "";

		}
	}

	protected List<CheckField> checkFieldList = new ArrayList<>();

	protected void registerRequired(String name, Supplier<String> supplier) {
		checkFieldList.add(new CheckField(name, supplier));
	}

	/**
	 * Validates the user input in the text fields.
	 *
	 * @return true if the input is valid
	 */
	protected boolean isInputValid() {
		String errorMessage = "";

		for (CheckField checkField : checkFieldList) {
			errorMessage += checkField.getCheckType().check(checkField);

		}
		errorMessage += moreCheck();
		if (errorMessage.length() == 0) {
			return true;
		} else {
			fxViewManager.showError(this.getCurrentStage(), errorMessage, "Invalid Fields",
					"Please correct invalid fields");

			return false;
		}
	}

	protected String moreCheck() {
		return "";
	}
}
