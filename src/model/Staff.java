package model;

import java.io.Serializable;

public class Staff implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3685593797957016882L;
	private String userId;
	private String password;

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public Staff(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}
}
