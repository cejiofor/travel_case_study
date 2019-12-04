package com.perscholas.travelcorps.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {
	@Id
	@GeneratedValue
	private int userId;
	
	@Size(min=2, max=25,  message="Name must be between 2 and 25 characters long.")
	@NotBlank(message="User Name is required.")
	private String userName;
	
	@Size(min=8, message="Password must be at least 8 characters long.")
	@NotBlank(message="passoword is required.")
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String country;
	private Boolean isVolunteer;
	
	public User() {
	}

	public User(
			@Size(min = 2, max = 25, message = "Name must be between 2 and 25 characters long.") @NotBlank(message = "User Name is required.") String userName,
			@Size(min = 8, message = "Password must be at least 8 characters long.") @NotBlank(message = "passoword is required.") String password,
			String firstName, String lastName, String address, String city, String state, String country,
			Boolean isVolunteer) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;	
		this.city = city;
		this.state = state;
		this.country = country;
		this.isVolunteer = isVolunteer;
	}

	public User(int userId,
			@Size(min = 2, max = 25, message = "Name must be between 2 and 25 characters long.") @NotBlank(message = "User Name is required.") String userName,
			@Size(min = 8, message = "Password must be at least 8 characters long.") @NotBlank(message = "passoword is required.") String password,
			String firstName, String lastName, String address, String city, String state, String country,
			Boolean isVolunteer) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.isVolunteer = isVolunteer;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Boolean getIsVolunteer() {
		return isVolunteer;
	}

	public void setIsVolunteer(Boolean isVolunteer) {
		this.isVolunteer = isVolunteer;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", address=" + address + ", city=" + city + ", state=" + state
				+ ", country=" + country + ", isVolunteer=" + isVolunteer + "]";
	}
	
	
	
}
