INSERT INTO users (username, PASSWORD, first_name, last_name, address, city, state, country, vol_user) 
	VALUES("jdoe@gmail.com", "password", "John", "Doe", "2130 Regular St", "Chicago", "IL", "USA", TRUE);
INSERT INTO users (username, PASSWORD, first_name, last_name, address, city, state, country, vol_user) 
	VALUES("janedoe@gmail.com", "password", "Jane", "Doe", "2130 Regular Dr", "Dallas", "TX", "USA", TRUE);
INSERT INTO users (username, PASSWORD, first_name, last_name, address, city, state, country, vol_user) 
	VALUES("salah@perscholas.org", "password123", "Salah", "Samm", "211 N. Ervay, Suite 700", "Dallas", "TX", "USA", FALSE);
INSERT INTO users (username, PASSWORD, first_name, last_name, address, city, state, country, vol_user) 
	VALUES("chris@perscholas.org", "password123", "Chris", "Eji", "211 N. Ervay, Suite 700", "Dallas", "TX", "USA", FALSE);
INSERT INTO users (username, PASSWORD, first_name, last_name, address, city, state, country, vol_user) 
	VALUES("eric@un.org", "password123", "Eric", "Apple", "405 E 42nd St", "New York", "NY", "USA", FALSE);
INSERT INTO users (username, PASSWORD, first_name, last_name, address, city, state, country, vol_user) 
	VALUES("robert@brac.net", "password123", "Robert", "White", "BRAC Centre, 75 Mohakhali", "Dhaka-1212", "Dhaka", "Bangladesh", FALSE);

INSERT INTO volunteers (user_id, skill1, skill2, skill3) VALUES(1, "Coding", "Teaching", "Design");
INSERT INTO volunteers (user_id, skill1) VALUES(2, "Coding");
	
INSERT INTO orgs VALUES (1, "Per Scholas", "perscholas.org",
	"Per Scholas is a US nonprofit providing tuition-free training for careers in Information Technology.",
	"admin@perscholas.org",
	"211 N. Ervay, Suite 700, Dallas, TX 75201", 3);
INSERT INTO orgs VALUES (2, "BRAC", "brac.net",
	"Fighting poverty, building platforms for tolerance, equality and inclusion, saying no to violence against women and children.",
	"admin@brac.net",
	"BRAC Centre, 75 Mohakhali, Dhaka-1212, Bangladesh", 5);
INSERT INTO orgs VALUES
(3, "United Nations", "un.org",
"The maintenance of international peace and security.",
"admin@un.org",
"United Nations Secretariat Building, 405 E 42nd St, New York, NY 10017", 6);

INSERT INTO org_users (org_id, user_id, prime_contact) VALUES (1, 3, TRUE);
INSERT INTO org_users (org_id, user_id, prime_contact) VALUES (1, 4, FALSE);
INSERT INTO org_users (org_id, user_id, prime_contact) VALUES (2, 5, TRUE);
INSERT INTO org_users (org_id, user_id, prime_contact) VALUES (3, 6, TRUE);

INSERT INTO projects (project_name, city, country, startDate, endDate, org_id, skill1, skill2) VALUES ("Student App", "Dallas", "USA", "2019-09-06", "2019-12-13",1, "Coding", "Teaching");
INSERT INTO projects (project_name, city, country, startDate, endDate, org_id, skill1, skill2) VALUES ("Food Distrubtion Project", "Dhaka", "Bangladesh", "2020-01-06", "2020-02-13", 2, "Project Management", "Translation");
INSERT INTO projects (project_name, city, country, startDate, endDate, org_id, skill1, skill2) VALUES ("Peace Summit Event", "Singapore", "Singapore", "2020-06-14", "2020-06-18", 3, "Mediation", "International Relations");

SET foreign_key_checks = 1;	