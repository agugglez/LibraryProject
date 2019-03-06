package edu.mum.library.view;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import edu.mum.library.common.NoAutoSettingGetting;
import edu.mum.library.view.base.BaseFxModalController;
import javafx.fxml.FXML;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextInputControl;

public abstract class LibraryFxModalEditController<T> extends BaseFxModalController {

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		this.getCurrentStage().close();
	}

	@SuppressWarnings("unchecked")
	protected T provideEntity() {
		return (T) UserObjectForView.getParamFromStage(this.getCurrentStage());
	}

	protected Set<Field> ignoreFieldList = new HashSet<>(
			FieldUtils.getFieldsListWithAnnotation(this.getClass(), NoAutoSettingGetting.class));

	// FieldUtils
	@Override
	public void postInit() {

		this.entityDto = provideEntity();

		fromDtoToView();

		morePost();

	}

	protected boolean shouldSkipe(Field field) {
		return ignoreFieldList.contains(field);
	}

	protected void fromDtoToView() {

		if (entityDto != null) {
			loopFXMLFieldList(this.entityDto, (obj, name) -> {
				try {
					if (obj instanceof TextInputControl) {
						((TextInputControl) obj).setText(BeanUtils.getProperty(this.entityDto, name));
					} else if (obj instanceof Labeled) {
						((Labeled) obj).setText(BeanUtils.getProperty(this.entityDto, name));
					}
				} catch (Exception e) {
					// throw new RuntimeException("failed to do field setting",e);
					e.printStackTrace();
				}
			});
		}
	}

	protected void fromViewToDto() {
		fromViewToDto(entityDto);
	}

	protected void fromViewToDto(T entityDto) {
		loopFXMLFieldList(entityDto, (obj, name) -> {
			if (obj instanceof TextInputControl) {
				try {
					BeanUtils.setProperty(entityDto, name, ((TextInputControl) obj).getText());
				} catch (Exception e) {
					// throw new RuntimeException("failed to do field setting",e);
					e.printStackTrace();
				}
			}
		});
	}

	private void loopFXMLFieldList(T entityDto, BiConsumer<Object, String> consumer) {
		if (entityDto != null) {
			try {
				List<Field> fieldList = FieldUtils.getFieldsListWithAnnotation(this.getClass(), FXML.class);
				for (Field field : fieldList) {
					if (shouldSkipe(field)) {
						continue;
					}
					field.setAccessible(true);
					Object obj = field.get(this);
					String name = field.getName();
					if (name.endsWith("Field")) {
						name = name.substring(0, name.length() - "Field".length());
					}
					consumer.accept(obj, name);
				}
			} catch (Exception e) {
				// throw new RuntimeException("failed to do field setting",e);
				e.printStackTrace();
			}
		}
	}

	protected void morePost() {

	}

	protected static class CheckField<T> {
		private Supplier<T> supplier;

		public CheckType getCheckType() {
			return checkType;
		}

		private String name;
		private CheckType checkType;

		public Supplier<T> getSupplier() {
			return supplier;
		}

		public CheckField(String name, Supplier<T> supplier) {
			this(name, supplier, CheckType.REQUIRED);
		}

		public CheckField(String name, Supplier<T> supplier, CheckType checkType) {
			super();
			this.supplier = supplier;
			this.name = name;
			this.checkType = checkType;
		}

		public String getName() {
			return name;
		}
	}

	private static interface ICheck<T> {
		boolean check(CheckField<T> val);

		public String formatErrorMessage(String name);
	}

	private static class RequiredCheck implements ICheck<String> {

		@Override
		public boolean check(CheckField<String> val) {
			return !StringUtils.isEmpty(val.getSupplier().get());
		}

		@Override
		public String formatErrorMessage(String name) {
			return "No valid " + name + "!\n";
		}
	}

	public static enum CheckType {
		REQUIRED(new RequiredCheck());
		CheckType(ICheck<String> check) {
			this.check = check;
		}

		private ICheck<String> check;

		public String check(CheckField<String> val) {
			if (!check.check(val)) {
				return check.formatErrorMessage(val.name);

			}
			return "";

		}
	}

	protected List<CheckField<String>> checkFieldList = new ArrayList<>();

	protected void registerRequired(String name, Supplier<String> supplier) {
		checkFieldList.add(new CheckField<String>(name, supplier));
	}

	/**
	 * Validates the user input in the text fields.
	 *
	 * @return true if the input is valid
	 */
	protected boolean isInputValid() {
		String errorMessage = "";

		for (CheckField<String> checkField : checkFieldList) {
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

	protected T entityDto;
}
