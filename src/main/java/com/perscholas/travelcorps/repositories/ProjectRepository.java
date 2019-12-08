package com.perscholas.travelcorps.repositories;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.perscholas.travelcorps.models.Project;

public interface ProjectRepository {
	List<Project> getAllProjects() throws SQLException;	
	List<Project> getProjectsbyOrg(int orgId) throws SQLException;	
	Integer registerProjects(Project project) throws SQLException, ClassNotFoundException, IOException;
	Project getProjectById(int projectId) throws ClassNotFoundException, IOException, SQLException;
	Project getProjectByName(String name) throws ClassNotFoundException, IOException, SQLException;
	Boolean updateProject(Project project) throws SQLException, ClassNotFoundException, IOException;
	Boolean removeProject(int projectId) throws IOException, SQLException;
}
