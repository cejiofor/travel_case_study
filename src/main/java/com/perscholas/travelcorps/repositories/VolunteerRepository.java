package com.perscholas.travelcorps.repositories;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.perscholas.travelcorps.models.User;
import com.perscholas.travelcorps.models.Volunteer;

public interface VolunteerRepository {
	List<Volunteer> getAllVolunteers() throws SQLException;	
	Integer registerVolunteer(Volunteer volunteer) throws SQLException, ClassNotFoundException, IOException;
	Volunteer getVolunteerById(int volunteerId) throws ClassNotFoundException, IOException, SQLException;
	Volunteer getVolunteerByName(String name) throws ClassNotFoundException, IOException, SQLException;
	Boolean updateVolunteer(Volunteer v) throws SQLException, ClassNotFoundException, IOException;
	Boolean removeVolunteer(int volunteerId) throws IOException, SQLException;
}
