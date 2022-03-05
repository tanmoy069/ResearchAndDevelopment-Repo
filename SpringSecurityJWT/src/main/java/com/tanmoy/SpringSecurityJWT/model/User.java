package com.tanmoy.SpringSecurityJWT.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "User", uniqueConstraints = @UniqueConstraint(columnNames = { "username" }))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String username;
	private String password;
	private int roleId;
	private boolean isActive;
	private boolean isCompleteApproval;

	public User() {
		super();
	}

	public User(String username, String password, int roleId) {
		super();
		this.username = username;
		this.password = getBycrptPassword(password);
		this.roleId = roleId;
		this.isActive = true;
		this.isCompleteApproval = roleId == 1 ? true : false;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = getBycrptPassword(password);
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public boolean isCompleteApproval() {
		return isCompleteApproval;
	}

	public void setCompleteApproval(boolean isCompleteApproval) {
		this.isCompleteApproval = isCompleteApproval;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getRole() {
		return roleId == 1 ? "Admin" : "Blogger";
	}

	public String getStatus() {
		return isActive ? "Active" : "Deactivate";
	}

	public String getApprovalStatus() {
		return isCompleteApproval ? "Approval Completed" : "Approval Not Completed";
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", roleId=" + roleId
				+ ", isActive=" + isActive + ", isCompleteApproval=" + isCompleteApproval + "]";
	}

	private String getBycrptPassword(String password) {
		BCryptPasswordEncoder bcryptPassword = new BCryptPasswordEncoder(12);
		return bcryptPassword.encode(password);
	}
}