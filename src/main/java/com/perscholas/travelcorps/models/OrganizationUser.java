package com.perscholas.travelcorps.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OrganizationUser extends User {
	private int orgUserId;
	private int userId;
	private int orgId;
	private Boolean isPrimeContact;

	public OrganizationUser(int userId, int orgId, Boolean isPrimeContact) {
		super();
		this.userId = userId;
		this.orgId = orgId;
		this.isPrimeContact = isPrimeContact;
	}

	public OrganizationUser(int orgUserId, int userId, int orgId, Boolean isPrimeContact) {
		super();
		this.orgUserId = orgUserId;
		this.userId = userId;
		this.orgId = orgId;
		this.isPrimeContact = isPrimeContact;
	}

	public OrganizationUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrganizationUser(int userId,
			@Size(min = 2, max = 25, message = "Name must be between 2 and 25 characters long.") @NotBlank(message = "User Name is required.") String userName,
			@Size(min = 8, message = "Password must be at least 8 characters long.") @NotBlank(message = "passoword is required.") String password,
			String firstName, String lastName, String address, String city, String state, String country,
			Boolean isVolunteer) {
		super(userId, userName, password, firstName, lastName, address, city, state, country, isVolunteer);
		// TODO Auto-generated constructor stub
	}

	

	public OrganizationUser(int userId,
			@Size(min = 2, max = 25, message = "Name must be between 2 and 25 characters long.") @NotBlank(message = "User Name is required.") String userName,
			@Size(min = 8, message = "Password must be at least 8 characters long.") @NotBlank(message = "passoword is required.") String password,
			String firstName, String lastName, String address, String city, String state, String country,
			Boolean isVolunteer, int orgId, Boolean isPrimeContact) {
		super(userId, userName, password, firstName, lastName, address, city, state, country, isVolunteer);
		this.userId = userId;
		this.orgId = orgId;
		this.isPrimeContact = isPrimeContact;
	}

	public int getOrgUserId() {
		return orgUserId;
	}

	public void setOrgUserId(int orgUserId) {
		this.orgUserId = orgUserId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public Boolean getIsPrimeContact() {
		return isPrimeContact;
	}

	public void setIsPrimeContact(Boolean isPrimeContact) {
		this.isPrimeContact = isPrimeContact;
	}
	
	
	

}
