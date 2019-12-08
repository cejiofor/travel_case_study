package com.perscholas.travelcorps.models;

import java.util.ArrayList;
import java.util.List;

public class Organization{
	private int orgID;
	private String orgName;
	private String website;
	private String mission;
	private String email;
	private String address;
	private int primeContactId;
	private List<Integer> projects;
	
	public Organization() {
		super();
	}
	
	public Organization(String orgName, String website, String mission, String email, String address, int primeContactId) {
		super();
		this.orgName = orgName;
		this.website = website;
		this.mission = mission;
		this.email = email;
		this.address = address;
		this.primeContactId = primeContactId;
		this.projects = new ArrayList<Integer>();
	}

	public Organization(int orgID, String orgName, String website, String mission, String email, String address,
			int primeContactId) {
		super();
		this.orgID = orgID;
		this.orgName = orgName;
		this.website = website;
		this.mission = mission;
		this.email = email;
		this.address = address;
		this.primeContactId = primeContactId;
	}

	public int getOrgID() {
		return orgID;
	}

	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPrimeContactId() {
		return primeContactId;
	}

	public void setPrimeContactId(int primeContactId) {
		this.primeContactId = primeContactId;
	}

	public List<Integer> getProjects() {
		return projects;
	}

	public void setProjects(List<Integer> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Organization [orgID=" + orgID + ", orgName=" + orgName + ", website=" + website + ", email=" + email
				+ ", address=" + address + ", mission=" + mission + ", projects=" + projects + "]";
	}
	
	

}
