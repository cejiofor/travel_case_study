package com.perscholas.travelcorps.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.perscholas.travelcorps.repositories.SignUpRepository;

public class Project {
	private int projectID;
	private String projectName;
	private String city;
	private String country;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	private int orgID;
	private List<String> skills;
	
	@Autowired
	SignUpRepository signUpRepository;

	public Project() {
		super();
		this.skills = new ArrayList<String>();
	}
	
	public Project(String projectName, String city, String country, Date startDate, Date endDate, int orgID, List<String> skills) {
		super();
		this.projectName = projectName;
		this.city = city;
		this.country = country;
		this.startDate = startDate;
		this.endDate = endDate;
		this.orgID = orgID;
		this.skills = skills;
		 
	}
	
	public Project(int projectID, String projectName, String city, String country, Date startDate, Date endDate,
			int orgID, List<String> skills) {
		super();
		this.projectID = projectID;
		this.projectName = projectName;
		this.city = city;
		this.country = country;
		this.startDate = startDate;
		this.endDate = endDate;
		this.orgID = orgID;
		this.skills = skills;
	}

	public static java.sql.Date getSqlDate(Date utilDate) {
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
	
	public static java.util.Date getUtilDate(java.sql.Date sqlDate){
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
	
//	public Boolean volunteerIdSignLoop(Integer volunteerId) throws ClassNotFoundException, SQLException, IOException {
//		List<Integer> volunteerSignUps = new ArrayList<Integer>();
//		try{
//			volunteerSignUps = signUpRepository.getVolunteerSignUps(this.projectID);
//		}
//		catch(Exception e) {
//			System.out.println("Not Working: "+e.getMessage());
//		}
//		if (volunteerSignUps != null) {
//			for (Integer volId : volunteerSignUps) {
//				if (volId == volunteerId) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}

}

