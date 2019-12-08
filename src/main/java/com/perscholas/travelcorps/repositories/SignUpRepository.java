package com.perscholas.travelcorps.repositories;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.perscholas.travelcorps.models.OrganizationUser;

public interface SignUpRepository {
	Boolean signUpForProject(Integer volunteerId, Integer projectId) throws SQLException, ClassNotFoundException, IOException;
	Boolean cancelProjectSignup(Integer volunteerId, Integer projectId) throws IOException, SQLException;
}
