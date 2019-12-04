package com.perscholas.travelcorps.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.io.IOException;

import com.perscholas.travelcorps.models.Organization;

public class OrganizationDAO extends DAOClass {
	
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
	
	public List<Organization> getAllOrgs() throws SQLException {
		// Declare variables
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Organization organization = null;
		List<Organization> orgList = null;

		// Assign query string to a variable
		String qString = "select * from orgs";

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
			//Create list to hold Organization objects
			orgList = new ArrayList<Organization>();
			// Read the ResultSet instance
			while (rs.next()) {
				// Each iteration creates a new Organization
				organization = new Organization();
				// Assign columns/fields to related fields in the Organization object
				organization.setOrgID(rs.getInt(1));
				organization.setOrgName(rs.getString(2));
				organization.setWebsite(rs.getString(3));
				organization.setMission(rs.getString(4));
				organization.setEmail(rs.getString(5));
				organization.setAddress(rs.getString(6));
				// organization.setProjects(projects);
				
				// Add the Organization to the list
				orgList.add(organization);
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
		return orgList;
	}	
	
	public Integer registerOrg(Organization organization) throws SQLException, ClassNotFoundException, IOException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// Assign insert statement string to variable
		String insertString = "insert into orgs (org_id, org_name, website, mission_id, email, address) values (?,?,?,?,?,?) ";
		
	    int ID = -1;
	    String[] COL = {"org_id"};
	    
	    MariaDBConnection mariaDB = new MariaDBConnection();
		 
	    try
	    {
	        conn = mariaDB.getConnection();
	        stmt = conn.prepareStatement(insertString, COL);
	        
	        stmt.setInt(1, organization.getOrgID());
	        stmt.setString(2, organization.getOrgName());
	        stmt.setString(3, organization.getWebsite());
	        stmt.setString(4, organization.getMission());
	        stmt.setString(5, organization.getEmail());
	        stmt.setString(6, organization.getAddress());

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
	
	public Organization getOrgById(int orgID) throws ClassNotFoundException, IOException, SQLException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Organization organization = null;
		
		// Assign query string to variable
		String qString = "select * from orgs where org_id = ?";
		
		// Create MySqlConnection class instance
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mariaDB.getConnection();
			stmt = conn.prepareStatement(qString);
			
			// Set query parameters (?)
			stmt.setInt(1, orgID); //org_id if from String parameter passed to method
			
			// Run query and assign to ResultSet
			rs = stmt.executeQuery();
			
			// Retrieve ResultSet and assign to new Organization
			if (rs.next()) {
				organization = new Organization();
				organization.setOrgID(rs.getInt(1));
				organization.setOrgName(rs.getString(2));
				organization.setWebsite(rs.getString(3));
				organization.setMission(rs.getString(4));
				organization.setEmail(rs.getString(5));
				organization.setAddress(rs.getString(6));
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
		return organization;
	}
	
	public Organization getOrgByName(String name) throws ClassNotFoundException, IOException, SQLException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Organization organization = null;
		
		// Assign query string to variable
		String qString = "select * from orgs where org_name = ?";
		
		// Create MySqlConnection class instance
		MariaDBConnection mariaDB = new MariaDBConnection();
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mariaDB.getConnection();
			stmt = conn.prepareStatement(qString);
			
			// Set query parameters (?)
			stmt.setString(1, name); // org_id if from String parameter passed to method
			// Run query and assign to ResultSet
			rs = stmt.executeQuery();
			// Retrieve ResultSet and assign to new Organization
			if (rs.next()) {
				organization = new Organization();
				organization.setOrgID(rs.getInt(1));
				organization.setOrgName(rs.getString(2));
				organization.setWebsite(rs.getString(3));
				organization.setMission(rs.getString(4));
				organization.setEmail(rs.getString(5));	
				organization.setAddress(rs.getString(6));
				// organization.setUserID(rs.getString(7));			
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
		return organization;
	}
	
	public Boolean updateOrg(Organization organization) throws SQLException, ClassNotFoundException, IOException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		Integer updateResult = null;
		
		// Assign update string to variable
		String updateString = "update orgs "
				+ "set org_name = ?, website = ?, mission = ?, email = ?, address = ? "
				+ "where org_id = ?";
		
		// Create MySqlConnection class instance
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mariaDB.getConnection();
			stmt = conn.prepareStatement(updateString);
			
			// Set query parameters (?)
	        stmt.setString(1, organization.getOrgName());
	        stmt.setString(2, organization.getWebsite());
	        stmt.setString(3, organization.getMission());
	        stmt.setString(4, organization.getEmail());
			stmt.setString(5, organization.getAddress());
			stmt.setInt(6, organization.getOrgID());

			
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
	
	public Boolean removeOrg(int orgID) throws IOException, SQLException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		Integer updateResult = null;
		
		// Assign delete string to variable
		String deleteString = "delete from orgs where org_id = ?";
		
		// Create MySqlConnection class instance
		MariaDBConnection mariaDB = new MariaDBConnection();
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mariaDB.getConnection();
			stmt = conn.prepareStatement(deleteString);
			
			// Set query parameters (?)
			stmt.setInt(1, orgID);
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
