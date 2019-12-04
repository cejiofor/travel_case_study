package com.perscholas.travelcorps.models;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Volunteer extends User {
	private int volunteerID;
	private int userId;
	private List<String> skills;
	
	public Volunteer() {
		super();
	}
	
	public Volunteer(int volunteerID, int userId, List<String> skills) {
		super();
		this.volunteerID = volunteerID;
		this.userId = userId;
		this.skills = skills;
	}

	public Volunteer(int userId,
			@Size(min = 2, max = 25, message = "Name must be between 2 and 25 characters long.") @NotBlank(message = "User Name is required.") String userName,
			@Size(min = 8, message = "Password must be at least 8 characters long.") @NotBlank(message = "passoword is required.") String password,
			String firstName, String lastName, String address, String city, String state, String country,
			Boolean isVolunteer, List<String> skills) {
		super(userId, userName, password, firstName, lastName, address, city, state, country, isVolunteer);
		this.skills = skills;
	}
	
	public int getVolunteerID() {
		return volunteerID;
	}

	public void setVolunteerID(int volunteerID) {
		this.volunteerID = volunteerID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "Volunteer [volunteerID=" + volunteerID + ", userId=" + userId + ", skills=" + skills
				+ ", getUserName()=" + getUserName() + ", getPassword()=" + getPassword() + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + ", getAddress()=" + getAddress()
				+ ", getCity()=" + getCity() + ", getState()=" + getState() + ", getCountry()=" + getCountry()
				+ ", getIsVolunteer()=" + getIsVolunteer() + "]";
	}

	
	
	

}
