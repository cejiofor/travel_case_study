SET foreign_key_checks = 0;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
	user_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	username VARCHAR(20) UNIQUE KEY,
	PASSWORD VARCHAR(20),
	prime_contact BOOL
);
DROP TABLE IF EXISTS volunteers;
CREATE TABLE volunteers (
	volunteer_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	user_id INT,
	volunteer_name VARCHAR(100),
	email VARCHAR(100),
	address VARCHAR(100), 
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
	username VARCHAR(20),
	CONSTRAINT `orgs_user_fk` FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE
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
	CONSTRAINT `projectOrg_fk` FOREIGN KEY (org_id) REFERENCES orgs (org_id) ON DELETE CASCADE
);
DROP TABLE IF EXISTS skills;
CREATE TABLE skills (
	skill_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	skill_name VARCHAR(100),
	description VARCHAR(100)
);
DROP TABLE IF EXISTS volunteer_skills;
CREATE TABLE volunteer_skills(
	project_id VARCHAR(100) REFERENCES projects(project_id),
	volunteer_id VARCHAR(100) REFERENCES volunteers(volunteer_id),
	skill_id INT REFERENCES skills(skill_id)
);
DROP TABLE IF EXISTS project_member;
CREATE TABLE project_member(
	project_id VARCHAR(100) REFERENCES projects(project_id),
	org_id VARCHAR(100) REFERENCES orgs(org_id),
	skill_id INT REFERENCES skills(skill_id), 
	CONSTRAINT PRIMARY KEY (project_id, org_id)
);

INSERT INTO users (username, PASSWORD, prime_contact) VALUES("cejiofor", "password", FALSE);
INSERT INTO users (username, PASSWORD, prime_contact) VALUES("bobdylan", "password", FALSE);
INSERT INTO users (username, PASSWORD, prime_contact) VALUES("sjobs", "password", FALSE);

INSERT INTO volunteers (user_id, volunteer_name, email, address) VALUES(1, "Chris Ejiofor", "cejiofor@gmail.com", "123 Fake Street, Dallas, TX USA");
INSERT INTO volunteers (user_id, volunteer_name, email, address) VALUES(2, "Bob Dylan", "bdylan@gmail.com", "123 Fake Road, Plano, TX USA");
INSERT INTO volunteers (user_id, volunteer_name, email, address) VALUES(3, "Steve Jobs", "sjobs@gmail.com", "123 Fake Street, Fort Worth, TX USA");

INSERT INTO orgs VALUES (1, "Per Scholas", "perscholas.org",
	"Per Scholas is a US nonprofit providing tuition-free training for careers in Information Technology.",
	"admin@perscholas.org",
	"211 N. Ervay, Suite 700 Dallas, TX 75201", 1);
INSERT INTO orgs VALUES (2, "BRAC", "brac.net",
	"Fighting poverty, building platforms for tolerance, equality and inclusion, saying no to violence against women and children.",
	"admin@brac.net",
	"BRAC Centre, 75 Mohakhali, Dhaka-1212, Bangladesh", 2);
INSERT INTO orgs VALUES
(3, "United Nations", "un.org",
"The maintenance of international peace and security.",
"admin@un.org",
"United Nations Secretariat Building, 405 E 42nd St, New York, NY 10017", 3);

INSERT INTO projects (project_id, project_name, city, country, startDate, endDate, org_id) VALUES (1, "Student App", "Dallas", "USA", "2019-09-06", "2019-12-13", 1);
INSERT INTO projects (project_id, project_name, city, country, startDate, endDate, org_id) VALUES (2, "Food Distrubtion Project", "Dhaka", "Bangladesh", "2020-01-06", "2020-02-13", 2);
INSERT INTO projects (project_id, project_name, city, country, startDate, endDate, org_id) VALUES (3, "Peace Summit Event", "Singapore", "Singapore", "2020-06-14", "2020-06-18", 3);

SET foreign_key_checks = 1;			

/*
INSERT INTO orgs (org_id, org_name, website, mission_id, email, address) VALUES 
(3, "United Nations Geneva", "www.unog.ch", 
"The maintenance of international peace and security.",
"admin@unog.ch", 
"Palais des Nations, 1211 Geneva 10, Switzerland.");

(org_id, org_name, website, mission_id, email, address)
*/