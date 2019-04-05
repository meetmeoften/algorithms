package com.test.libraryManagement;

public class User {

	private static final Integer MAX_BOOK_COUNT = 10;

	String userName;
	String userId;

	public User(String userName, String userId) {
		this.userName = userName;
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
