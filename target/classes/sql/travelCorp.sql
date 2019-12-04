DROP DATABASE IF EXISTS `travelcorps`;
CREATE DATABASE `travelcorps`; 
USE `travelcorps`; 
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
	user_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	username VARCHAR(20) UNIQUE KEY,
	password VARCHAR(20),
	first_name VARCHAR(100),
	last_name VARCHAR(100),
	address VARCHAR(100),
	city VARCHAR(100),
	state VARCHAR(100), 
	country VARCHAR(100),
	vol_user BOOL
); 

DROP TABLE IF EXISTS volunteers;
CREATE TABLE volunteers (
	volunteer_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	user_id INT NOT NULL,
	skill1 VARCHAR(100),
	skill2 VARCHAR(100),
	skill3 VARCHAR(100),
	skill4 VARCHAR(100),
	skill5 VARCHAR(100),
	CONSTRAINT `volunteer_user_fk` FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS orgs;
CREATE TABLE orgs (
	org_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	org_name VARCHAR(100),
	website VARCHAR(100),
	mission_id VARCHAR(200),
	email VARCHAR(100),
	address VARCHAR(100),
	prime_contact_id INT,
	CONSTRAINT `orgs__prime_user_fk` FOREIGN KEY (prime_contact_id) REFERENCES org_users(org_user_id) ON DELETE CASCADE
);
	

DROP TABLE IF EXISTS org_users;
CREATE TABLE org_users (
	org_user_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,
	org_id INT,
	prime_contact BOOL DEFAULT 0,
	CONSTRAINT `org_users_fk` FOREIGN KEY (org_user_id) REFERENCES users(user_id) ON DELETE CASCADE,
	CONSTRAINT `org_users_org_fk` FOREIGN KEY (org_id) REFERENCES orgs(org_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS projects;
CREATE TABLE projects (
	project_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	project_name VARCHAR(100),
	city VARCHAR(100),
	country VARCHAR(100),
	startDate DATE,
	endDate DATE,
	org_id INT,
	skill1 VARCHAR(100),
	skill2 VARCHAR(100),
	skill3 VARCHAR(100),
	skill4 VARCHAR(100),
	skill5 VARCHAR(100), 
	CONSTRAINT `projectOrg_fk` FOREIGN KEY (org_id) REFERENCES orgs (org_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS project_signups;
CREATE TABLE project_signups (
	`signups_volunteer_id` INT NOT NULL,
    `signups_project_id` INT NOT NULL,
    PRIMARY KEY (`signups_project_id`, `signups_volunteer_id`),
    CONSTRAINT `s_fk_projectId` FOREIGN KEY (`signups_project_id`)
		REFERENCES `projects` (`project_id`)
			ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `s_fk_volunteerId` FOREIGN KEY (`signups_volunteer_id`)
		REFERENCES `volunteers` (`volunteer_id`)
			ON UPDATE CASCADE ON DELETE CASCADE
);
