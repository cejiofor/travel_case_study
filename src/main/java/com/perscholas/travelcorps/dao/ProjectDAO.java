package com.perscholas.travelcorps.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.io.IOException;

import com.perscholas.travelcorps.models.Project;

public class ProjectDAO extends DAOClass {
	public void testConnection() {
		MariaDBConnection mariadbConnection = new MariaDBConnection();
		try {
			mariadbConnection.getConnection();
			System.out.println("Connected to MariaDB!");
		}
		catch(Exception e) {
			System.out.println("Database connection failed.");
		}
	}
	
	public List<Project> getAllProjects() throws SQLException {
		// Declare variables
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Project proj = null;
		List<Project> projList = null;

		// Assign query string to a variable
		String qString = "select * from projects";

		// Create MySqlConnection class instance
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		// Begin try/catch block to query the database
		try
		{
			// Connect to database
			conn = mariaDB.getConnection();
			// If the connection fails the application won't make it to this point
			System.out.println("Connected to database.");
			// Create Statement instance/object
			stmt = conn.createStatement();
		
			// Run query and assign to the ResultSet instance
			rs = stmt.executeQuery(qString);
			//Create list to hold Project objects
			projList = new ArrayList<Project>();
			// Read the ResultSet instance
			while (rs.next()) {
				// Each iteration creates a new Project
				proj = new Project();
				// Assign columns/fields to related fields in the Project object
				proj.setProjectID(rs.getInt(1));
				proj.setProjectName(rs.getString(2));
				proj.setCity(rs.getString(3));
				proj.setCountry(rs.getString(4));
				proj.setStartDate(rs.getDate(5));
				proj.setEndDate(rs.getDate(6));
				proj.setOrgID(rs.getInt(7));

				// Add the Project to the list
				projList.add(proj);
				// Repeat until rs.next() returns false (i.e., end of ResultSet)
			}
		}
		catch (ClassNotFoundException | IOException | SQLException e)
		{
			System.out.println("Error: " + e.getMessage());
			e.getStackTrace();
		}
		finally
		{
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return projList;
	}	
	
	public Integer registerProject(Project proj) throws SQLException, ClassNotFoundException, IOException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// Assign insert statement string to variable
		String insertString = "insert into projects (project_id, project_name, city, country, startDate, endDate, org_id) values (?,?,?,?,?,?,?) ";
		
	    int ID = -1;
	    String[] COL = {"project_id"};
	    
	    MariaDBConnection mariaDB = new MariaDBConnection();
		 
	    try
	    {
	        conn = mariaDB.getConnection();
	        stmt = conn.prepareStatement(insertString, COL);
	        
	        stmt.setInt(1, proj.getProjectID());
	        stmt.setString(2, proj.getProjectName());
	        stmt.setString(3, proj.getCity());
	        stmt.setString(4, proj.getCountry());
	        stmt.setDate(5, proj.getSqlDate(proj.getStartDate()));
			stmt.setDate(6, proj.getSqlDate(proj.getEndDate()));
			stmt.setInt(7, proj.getOrgID());

	        stmt.executeUpdate();
	        
	        rs = stmt.getGeneratedKeys();
	        if(rs != null && rs.next()) {
	            ID = rs.getInt(1);
	        }
	        System.out.println(ID);
	    }
	    catch (SQLException e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		finally
		{
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	    
		return ID;
	}
	
	public Project getProjectById(int orgID) throws ClassNotFoundException, IOException, SQLException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Project proj = null;
		
		// Assign query string to variable
		String qString = "select * from projects where project_id = ?";
		
		// Create MySqlConnection class instance
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mariaDB.getConnection();
			stmt = conn.prepareStatement(qString);
			
			// Set query parameters (?)
			stmt.setInt(1, orgID); // Volunteer_id if from String parameter passed to method
			
			// Run query and assign to ResultSet
			rs = stmt.executeQuery();
			
			// Retrieve ResultSet and assign to new Project
			if (rs.next()) {
				proj = new Project();
				proj.setProjectID(rs.getInt(1));
				proj.setProjectName(rs.getString(2));
				proj.setCity(rs.getString(3));
				proj.setCountry(rs.getString(4));
				proj.setStartDate(rs.getDate(5));
				proj.setEndDate(rs.getDate(6));
				proj.setOrgID(rs.getInt(7));
			}
		}
		catch (ClassNotFoundException | IOException | SQLException e)
		{
			System.out.println("Error: " + e.getMessage());
			e.getStackTrace();
		}
		finally
		{
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return proj;
	}
	
	public Project getProjectByName(String name) throws ClassNotFoundException, IOException, SQLException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Project proj = null;
		
		// Assign query string to variable
		String qString = "select * from projects where project_name = ?";
		
		// Create MySqlConnection class instance
		MariaDBConnection mariaDB = new MariaDBConnection();
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mariaDB.getConnection();
			stmt = conn.prepareStatement(qString);
			
			// Set query parameters (?)
			stmt.setString(1, name); // project_id if from String parameter passed to method
			// Run query and assign to ResultSet
			rs = stmt.executeQuery();
			// Retrieve ResultSet and assign to new Project
			if (rs.next()) {
				proj = new Project();
				proj.setProjectID(rs.getInt(1));
				proj.setProjectName(rs.getString(2));
				proj.setCity(rs.getString(3));
				proj.setCountry(rs.getString(4));
				proj.setStartDate(rs.getDate(5));
				proj.setEndDate(rs.getDate(6));
				proj.setOrgID(rs.getInt(7));		
			}
		}
		catch (ClassNotFoundException | IOException | SQLException e)
		{
			System.out.println("Error: " + e.getMessage());
			System.out.println(e.getStackTrace());
		}
		finally
		{
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return proj;
	}
	
	public Boolean updateOrg(Project proj) throws SQLException, ClassNotFoundException, IOException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		Integer updateResult = null;
		
		// Assign update string to variable
		String updateString = "update projects "
				+ "set project_name = ?, city = ?, county = ?, startDate = ?, endDate = ?, org_id =? "
				+ "where project_id = ?";
		
		// Create MySqlConnection class instance
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mariaDB.getConnection();
			stmt = conn.prepareStatement(updateString);
			
			// Set query parameters (?)
	        stmt.setString(1, proj.getProjectName());
	        stmt.setString(2, proj.getCity());
	        stmt.setString(3, proj.getCountry());
	        stmt.setDate(4, proj.getSqlDate(proj.getStartDate()));
			stmt.setDate(5, proj.getSqlDate(proj.getEndDate()));
			stmt.setInt(6, proj.getOrgID());
			stmt.setInt(7, proj.getProjectID());
			
			// Run query and assign to ResultSet
			updateResult = stmt.executeUpdate();
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
	
	public Boolean removeProject(int projectID) throws IOException, SQLException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		Integer updateResult = null;
		
		// Assign delete string to variable
		String deleteString = "delete from projects where project_id = ?";
		
		// Create MySqlConnection class instance
		MariaDBConnection mariaDB = new MariaDBConnection();
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mariaDB.getConnection();
			stmt = conn.prepareStatement(deleteString);
			
			// Set query parameters (?)
			stmt.setInt(1, projectID);
			// Run query and assign to ResultSet
			updateResult = stmt.executeUpdate();
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

