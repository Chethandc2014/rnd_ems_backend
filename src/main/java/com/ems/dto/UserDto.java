package com.ems.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3320215651327422483L;
	
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String role;
	
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

	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
