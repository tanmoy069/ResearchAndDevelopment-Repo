package com.tanmoy.CustomAnnotation.model;

import com.tanmoy.CustomAnnotation.annotations.JsonField;

public class Users {

	@JsonField("Tanmoy")
	private String firstName;

	@JsonField("Tushar")
	private String lastName;

	@JsonField(value = "tanmoytushar",name = "username")
	private String name;

	@JsonField
	private String password;

	public Users() {
		super();
	}

	public Users(String firstName, String lastName, String name, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.name = name;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Users [firstName=" + firstName + ", lastName=" + lastName + ", name=" + name + ", password=" + password
				+ "]";
	}

}
