package com.perscholas.travelcorps.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.*;
import java.io.IOException;

import com.perscholas.travelcorps.models.User;

public class UserDAO extends DAOClass {
	public List<User> getAllUsers() throws SQLException {
		// Declare variables
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		User u = null;
		List<User> userList = null;

		// Assign query string to a variable
		String qString = "select * from users";

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
			//Create list to hold User objects
			userList = new ArrayList<User>();
			// Read the ResultSet instance
			while (rs.next()) {
				// Each iteration creates a new user
				u = new User();
				// Assign columns/fields to related fields in the User object
				// 1,2 and 3 represent column numbers/positions
				u.setUserId(rs.getInt(1));
				u.setUserName(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setPrimeContact(rs.getBoolean(4));
				// Add the user to the list
				userList.add(u);
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
		return userList;
	} 	
	
	public Integer registerUser(User user) throws SQLException, ClassNotFoundException, IOException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// Assign insert statement string to variable
		String insertString = "insert into users (username, password, prime_contact) values (?,?,?)";
		
	    int ID = -1;
	    String[] COL = {"user_id"};
	    
	    MariaDBConnection mariaDB = new MariaDBConnection();
		 
	    try
	    {
	        conn = mariaDB.getConnection();
	        stmt = conn.prepareStatement(insertString, COL);
	        
	        stmt.setString(1, user.getUserName());
	        stmt.setString(2, user.getPassword());
	        stmt.setBoolean(3, user.getPrimeContact());
	       
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
	
	public User getUserById(int userId) throws ClassNotFoundException, IOException, SQLException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User u = null;
		
		// Assign query string to variable
		String qString = "select * from users where user_id = ?";
		
		// Create MySqlConnection class instance
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mariaDB.getConnection();
			stmt = conn.prepareStatement(qString);
			
			// Set query parameters (?)
			stmt.setInt(1, userId); // user_id if from String parameter passed to method
			
			// Run query and assign to ResultSet
			rs = stmt.executeQuery();
			
			// Retrieve ResultSet and assign to new User
			if (rs.next()) {
				u = new User();
				u.setUserId(rs.getInt(1));
				u.setUserName(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setPrimeContact(rs.getBoolean(4));
				
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
		return u;
	}
	
	public User getUserByName(String name) throws ClassNotFoundException, IOException, SQLException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User u = null;
		
		// Assign query string to variable
		String qString = "select * from users where username = ?";
		
		// Create MySqlConnection class instance
		MariaDBConnection mariaDB = new MariaDBConnection();
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mariaDB.getConnection();
			stmt = conn.prepareStatement(qString);
			
			// Set query parameters (?)
			stmt.setString(1, name); // user_id if from String parameter passed to method
			
			// Run query and assign to ResultSet
			rs = stmt.executeQuery();
			
			// Retrieve ResultSet and assign to new User
			if (rs.next()) {
				u = new User();
				u.setUserId(rs.getInt(1));
				u.setUserName(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setPrimeContact(rs.getBoolean(4));
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
		return u;
	}
	
	public Boolean updateUser(User u) throws SQLException, ClassNotFoundException, IOException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		Integer updateResult = null;
		
		// Assign update string to variable
		String updateString = "update users "
				+ "set username = ?, password = ?, prime_contact = ? "
				+ "where user_id = ?";
		
		// Create MySqlConnection class instance
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mariaDB.getConnection();
			stmt = conn.prepareStatement(updateString);
			
			// Set query parameters (?)
			stmt.setString(1, u.getUserName());
			stmt.setString(2, u.getPassword());
			stmt.setBoolean(3, u.getPrimeContact());
			stmt.setInt(4, u.getUserId());
			
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
	
	public Boolean removeUser(int userId) throws IOException, SQLException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		Integer updateResult = null;
		
		// Assign delete string to variable
		String deleteString = "delete from users where user_id = ?";

		// Create MySqlConnection class instance
		MariaDBConnection mariaDB = new MariaDBConnection();
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mariaDB.getConnection();
			stmt = conn.prepareStatement(deleteString);
			
			// Set query parameters (?)
			stmt.setInt(1, userId);
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
	
	public Boolean resetAutoIncrement() throws SQLException, ClassNotFoundException, IOException {
		// Declare variables
		Connection conn = null;
		Statement stmt = null;
		Integer updateResult = null;
		
		// Assign update string to variable
		String updateString = "ALTER TABLE users AUTO_INCREMENT = 3";
		
		// Create MySqlConnection class instance
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mariaDB.getConnection();
			stmt = conn.createStatement();

			// Run query and assign to ResultSet
			updateResult = stmt.executeUpdate(updateString);
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
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		UserDAO udao = new UserDAO();
		User newUser = new User(4, "mariah", "cell1122", false);
		udao.registerUser(newUser);
		
		List<User> newList = udao.getAllUsers();
		
		System.out.println(Arrays.toString(newList.toArray()));
	}

	public Integer createUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}
}
