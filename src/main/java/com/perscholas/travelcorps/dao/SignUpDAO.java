package com.perscholas.travelcorps.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpDAO {
	public Boolean signupForEvent(Integer memberId, Integer eventId) 
			throws SQLException, ClassNotFoundException, IOException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Integer queryResult = null;
		
		// Assign insert statement string to variable
		String insertString = "insert into signups (signups_member_id, signups_event_id) values (?,?)";
	    
		MariaDBConnection mariaDB = new MariaDBConnection();
		
	    try
	    {
	        conn = mariaDB.getConnection();
	        stmt = conn.prepareStatement(insertString);
	        
	        stmt.setInt(1, memberId);
	        stmt.setInt(2, eventId);
	        
	        queryResult = stmt.executeUpdate();
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
        if (queryResult > 0) {
        	System.out.println("true");
        	return true;
        }
        System.out.println("false");
		return false;
	} // End of signupForEvent() method
	
	//****************cancelSignup() method (i.e., delete)*****************
	
	public Boolean cancelSignup(Integer memberId, Integer eventId) 
			throws IOException, SQLException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		Integer updateResult = null;
		
		// Assign delete string to variable
		String deleteString = "delete from signups where signups_member_id = ? and signups_event_id = ?";
		
		// Create DatabaseConnection class instance
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mariaDB.getConnection();
			stmt = conn.prepareStatement(deleteString);
			
			// Set query parameters (?)
			stmt.setInt(1, memberId);
			stmt.setInt(2, eventId);
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
			System.out.println("true");
			return true;
		}
		System.out.println("false");
		return false;
	} // End of cancelSignup() method
}