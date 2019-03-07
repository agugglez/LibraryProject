package edu.mum.library.view.base;

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
}
