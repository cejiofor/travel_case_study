package com.perscholas.travelcorps.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.io.IOException;

public class DAOClass {

	public Boolean resetTable() throws SQLException, ClassNotFoundException, IOException {
		// Declare variables
		Connection conn = null;
		Statement stmt = null;
		Integer updateResult = null;
		
		
		// Create strings that will come together to reset table as sql query
		String varname1 = ""
		+ "SET foreign_key_checks = 0;";


		String varname11 = ""
		+ "DROP TABLE IF EXISTS users;";


		String varname12 = ""
		+ "CREATE TABLE users (	user_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,	username VARCHAR(20) UNIQUE KEY, PASSWORD VARCHAR(20),	prime_contact BOOL);";


		String varname13 = ""
		+ "DROP TABLE IF EXISTS volunteers;";


		String varname14 = ""
		+ "CREATE TABLE volunteers (	volunteer_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,	user_id INT,	volunteer_name VARCHAR(100),	email VARCHAR(100),	address VARCHAR(100), 	CONSTRAINT `volunteer_user_fk` FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE);";


		String varname15 = ""
		+ "DROP TABLE IF EXISTS orgs;";


		String varname16 = ""
		+ "CREATE TABLE orgs (	org_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,	org_name VARCHAR(100),	website VARCHAR(100),	mission_id VARCHAR(200),	email VARCHAR(100),	address VARCHAR(100),	username VARCHAR(100),	CONSTRAINT `orgs_user_fk` FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE);";


		String varname17 = ""
		+ "DROP TABLE IF EXISTS projects;";


		String varname18 = ""
		+ "CREATE TABLE projects (	project_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,	project_name VARCHAR(100),	city VARCHAR(100),	country VARCHAR(100),	startDate DATE,	endDate DATE,	org_id INT, 	CONSTRAINT `projectOrg_fk` FOREIGN KEY (org_id) REFERENCES orgs (org_id) ON DELETE CASCADE);";


		String varname19 = ""
		+ "DROP TABLE IF EXISTS skills;";


		String varname110 = ""
		+ "CREATE TABLE skills (	skill_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,	skill_name VARCHAR(100),	description VARCHAR(100));";


		String varname111 = ""
		+ "DROP TABLE IF EXISTS volunteer_skills;";


		String varname112 = ""
		+ "CREATE TABLE volunteer_skills(	project_id VARCHAR(100) REFERENCES projects(project_id),	volunteer_id VARCHAR(100) REFERENCES volunteers(volunteer_id),	skill_id INT REFERENCES skills(skill_id));";


		String varname113 = ""
		+ "DROP TABLE IF EXISTS project_member;";


		String varname114 = ""
		+ "CREATE TABLE project_member(	project_id VARCHAR(100) REFERENCES projects(project_id),	org_id VARCHAR(100) REFERENCES orgs(org_id),	skill_id INT REFERENCES skills(skill_id), 	CONSTRAINT PRIMARY KEY (project_id, org_id));";


		String varname115 = ""
		+ "INSERT INTO users (username, PASSWORD, prime_contact) VALUES(\"cejiofor\", \"password\", FALSE);";


		String varname116 = ""
		+ "INSERT INTO users (username, PASSWORD, prime_contact) VALUES(\"bobdylan\", \"password\", FALSE);";


		String varname117 = ""
		+ "INSERT INTO users (username, PASSWORD, prime_contact) VALUES(\"sjobs\", \"password\", FALSE);";


		String varname118 = ""
		+ "INSERT INTO volunteers (user_id, volunteer_name, email, address) VALUES(1, \"Chris Ejiofor\", \"cejiofor@gmail.com\", \"123 Fake Street, Dallas, TX USA\");";


		String varname119 = ""
		+ "INSERT INTO volunteers (user_id, volunteer_name, email, address) VALUES(2, \"Bob Dylan\", \"bdylan@gmail.com\", \"123 Fake Road, Plano, TX USA\");";


		String varname120 = ""
		+ "INSERT INTO volunteers (user_id, volunteer_name, email, address) VALUES(3, \"Steve Jobs\", \"sjobs@gmail.com\", \"123 Fake Street, Fort Worth, TX USA\");";


		String varname121 = ""
		+ "INSERT INTO orgs VALUES (1, \"Per Scholas\", \"perscholas.org\",	\"Per Scholas is a US nonprofit providing tuition-free training for careers in Information Technology.\",	\"admin@perscholas.org\",	\"211 N. Ervay, Suite 700 Dallas, TX 75201\", 1);";


		String varname122 = ""
		+ "INSERT INTO orgs VALUES (2, \"BRAC\", \"brac.net\",	\"Fighting poverty, building platforms for tolerance, equality and inclusion, saying no to violence against women and children.\",	\"admin@brac.net\",	\"BRAC Centre, 75 Mohakhali, Dhaka-1212, Bangladesh\", 2);";


		String varname123 = ""
		+ "INSERT INTO orgs VALUES(3, \"United Nations\", \"un.org\",\"The maintenance of international peace and security.\",\"admin@un.org\",\"United Nations Secretariat Building, 405 E 42nd St, New York, NY 10017\", 3);";


		String varname124 = ""
		+ "INSERT INTO projects (project_id, project_name, city, country, startDate, endDate, org_id) VALUES (1, \"Student App\", \"Dallas\", \"USA\", \"2019-09-06\", \"2019-12-13\", 1);";


		String varname125 = ""
		+ "INSERT INTO projects (project_id, project_name, city, country, startDate, endDate, org_id) VALUES (2, \"Food Distrubtion Project\", \"Dhaka\", \"Bangladesh\", \"2020-01-06\", \"2020-02-13\", 2);";


		String varname126 = ""
		+ "INSERT INTO projects (project_id, project_name, city, country, startDate, endDate, org_id) VALUES (3, \"Peace Summit Event\", \"Singapore\", \"Singapore\", \"2020-06-14\", \"2020-06-18\", 3);";


		String varname127 = ""
		+ "SET foreign_key_checks = 1";			
		/*INSERT INTO orgs (org_id, org_name, website, mission_id, email, address) VALUES (3, "United Nations Geneva", "www.unog.ch", "The maintenance of international peace and security.","admin@unog.ch", "Palais des Nations, 1211 Geneva 10, Switzerland.");(org_id, org_name, website, mission_id, email, address)*/
				
		// Create a list of strings to loop over to rund sql queries
		List<String> sqlArr = new ArrayList<String>();
		sqlArr.add(varname1); 
		sqlArr.add(varname11);
		sqlArr.add(varname12);
		sqlArr.add(varname13);
		sqlArr.add(varname14);
		sqlArr.add(varname15); 
		sqlArr.add(varname16);
		sqlArr.add(varname17);
		sqlArr.add(varname18);
		sqlArr.add(varname19);
		sqlArr.add(varname110);
		sqlArr.add(varname111);
		sqlArr.add(varname112);
		sqlArr.add(varname113);
		sqlArr.add(varname114);
		sqlArr.add(varname115);
		sqlArr.add(varname116);
		sqlArr.add(varname117);
		sqlArr.add(varname118);
		sqlArr.add(varname119);
		sqlArr.add(varname120);
		sqlArr.add(varname121);
		sqlArr.add(varname122);
		sqlArr.add(varname123);
		sqlArr.add(varname124);
		sqlArr.add(varname125);
		sqlArr.add(varname126);
		sqlArr.add(varname127);
		
		// Create MySqlConnection class instance
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mariaDB.getConnection();
			stmt = conn.createStatement();
			for (String s: sqlArr) {
				// Run query and assign to ResultSet
				updateResult = stmt.executeUpdate(s);
			}
			
		}
		catch (ClassNotFoundException | SQLException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		finally
		{
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		if (updateResult > 0) {
			return true;
		}
		return false;
	}

}

