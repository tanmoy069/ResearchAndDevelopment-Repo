package com.tanmoy.JerseyWebApiRnd.model;

public class User {

	private int userId;
	private String userName;
	private String pass;

	public User() {
		super();
	}
	
	public User(int userId, String userName, String pass) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.pass = pass;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", pass=" + pass + "]";
	}

}
