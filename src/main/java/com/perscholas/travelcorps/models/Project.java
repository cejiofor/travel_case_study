package com.perscholas.travelcorps.models;

import java.util.Date;
import java.util.List;

public class Project {
	private int projectID;
	private String projectName;
	private String city;
	private String country;
	private Date startDate;
	private Date endDate;
	private int orgID;
	private List<String> skills;

	public Project() {
	}
	
	public Project(int projectID, String projectName, String city, String country, Date startDate, Date endDate, int orgID) {
		this.projectID = projectID;
		this.projectName = projectName;
		this.city = city;
		this.country = country;
		this.startDate = startDate;
		this.endDate = endDate;
		this.orgID = orgID;
		 
	}
	
	public java.sql.Date getSqlDate(Date utilDate) {
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
	
	public java.util.Date getUtilDate(java.sql.Date sqlDate){
		java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
		return utilDate;
	}
	
	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getOrgID() {
		return orgID;
	}

	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

}

