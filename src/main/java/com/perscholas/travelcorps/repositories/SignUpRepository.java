package com.perscholas.travelcorps.repositories;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SignUpRepository {
	public List<Integer> getVolunteerSignUps(Integer projectId) throws SQLException, ClassNotFoundException, IOException;
	Boolean signUpForProject(Integer volunteerId, Integer projectId) throws SQLException, ClassNotFoundException, IOException;
	Boolean cancelProjectSignup(Integer volunteerId, Integer projectId) throws IOException, SQLException;
}
