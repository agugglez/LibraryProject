package edu.mum.library.view;

public class UserObjectForView {
	private Object controller;
	private Object parameter;

	public Object getController() {
		return controller;
	}

	public void setController(Object controller) {
		this.controller = controller;
	}

	public UserObjectForView(Object controller, Object parameter) {
		super();
		this.controller = controller;
		this.parameter = parameter;
	}

	public Object getParameter() {
		return parameter;
	}

	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}
}
