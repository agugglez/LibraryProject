package edu.mum.library.model;

import edu.mum.library.model.base.BaseEntityWithPrimaryKey;

public class Staff extends BaseEntityWithPrimaryKey<String> {

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

	@Override
	public String getPrimaryKey() {
		return getUserId();
	}
}
