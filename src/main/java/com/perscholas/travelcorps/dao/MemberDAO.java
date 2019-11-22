package com.perscholas.travelcorps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.perscholas.travelcorps.models.Member;

public class MemberDAO {
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
	
	public Integer createMember(Member member) throws SQLException {
		String name = member.getName();
		String email = member.getEmail();
		String password = member.getPassword();
		String favoriteLang = member.getFavoriteLanguage();
		
		// Create an instance of the mariaDB connection
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		//Declare SQL variables to manage connection and read data
		// String createQuery = null;
		// Statement createStmt = null;
		Connection sqlConnect = null;
		PreparedStatement insertStmt = null;
		String sqlQuery = null;
		Integer result = null;
		
		try {
			//Create a connection to MariaDB database
			sqlConnect = mariaDB.getConnection();
			
			////Create Table 
			// createQuery = "CREATE TABLE Members (Member_id INT PRIMARY KEY, Member_name VARCHAR(100), Member_price double";
			// createStmt = sqlConnect.createStatement();
			//// Run the sql query and return the results to a ResultSet variable
			// results = createStmt.executeQuery(createQuery);
			
			// SQL query to be run to receive data from SQL table
			sqlQuery = "INSERT INTO members (name, email, password, favorite_language) values (?, ?, ?, ?)";
			
			// Statement needed to run the sql query
			insertStmt = sqlConnect.prepareStatement(sqlQuery);

			// Set parameters for the prepared statement
			insertStmt.setString(1, name);
			insertStmt.setString(2, email);
			insertStmt.setString(3, password);
			insertStmt.setString(4, favoriteLang);
			
			
			// run the sql query and return the new ID to a result variable
			result = insertStmt.executeUpdate();
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		finally {
				
			if (insertStmt != null) {
				insertStmt.close();
			}
			if (sqlConnect != null) {
				sqlConnect.close();
			}
		}
		return result;
		
	}
	
	public List<Member> getMembers() throws SQLException {
		// List to store all Members
		List<Member> memberList = new ArrayList();
		// Create an instance of the mariaDB connection
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		//Declare SQL variables manage connection and read data
		Connection sqlConnect = null;
		String selectQuery = null;
		Statement selectStmt = null;
		ResultSet results = null;
		
		try {
			//Create a connection to MariaDB database
			sqlConnect = mariaDB.getConnection();
			
			// SQL query to be run to receive data from SQL table
			selectQuery = "SELECT * FROM members";
			
			// Statement needed to run the sql query
			selectStmt = sqlConnect.createStatement();
			
			// run the sql query and return the results to a ResultSet variable
			results = selectStmt.executeQuery(selectQuery);
			
			// iterate through the java result set and set student properties from ArrayList properties
			while (results.next())
			{
				Member member = new Member();
				member.setMemberId(results.getInt("member_id"));
				member.setName(results.getString("name"));
				member.setEmail(results.getString("email"));
				member.setPassword(results.getString("password"));

				member.setFavoriteLanguage(results.getString("favorite_language"));
				
				memberList.add(member);
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		finally {
			if (results != null) {
				results.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (sqlConnect != null) {
				sqlConnect.close();
			}
		}
		return memberList;
	}
	
	public Member getMember(String name) throws SQLException {
		Member Member = null;
		// Create an instance of the mariaDB connection
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		// Declare SQL variables needed to manage connection and read data
		Connection sqlConnect = null;
		PreparedStatement selectStmt = null;
		String selectQuery = null;
		ResultSet results = null;
		Member member = new Member();;
			
		try {
			//Create a connection to MariaDB database
			sqlConnect = mariaDB.getConnection();
			
			// SQL query to be run to get Member from SQL table
			selectQuery = "SELECT * FROM Members WHERE name = ?";
			
			// Statement needed to run the sql query
			selectStmt = sqlConnect.prepareStatement(selectQuery);
			selectStmt.setString(1, name.toString());
			
			// run the sql query 
			results = selectStmt.executeQuery();
			
			while (results.next())
			{
				System.out.println(member.toString());
				member.setMemberId(results.getInt("member_id"));
				member.setName(results.getString("name"));
				member.setEmail(results.getString("email"));
				member.setPassword(results.getString("password"));
				member.setFavoriteLanguage(results.getString("favorite_language"));
				
			}
			
		} 
		catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			if (results != null) {
				results.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (sqlConnect != null) {
				sqlConnect.close();
			}
		}
		return member;
		
	}
	
	public Boolean updateMember(Member member) throws SQLException {
		// Create an instance of the mariaDB connection
		MariaDBConnection mariaDB = new MariaDBConnection();

		// Declare SQL variables needed to manage connection and read data
		Connection sqlConnect = null;
		PreparedStatement updateStmt = null;
		String updateQuery = null;
		int results = -1;
		
		try {
			// SQL query to be run to udpate Member data in SQL table
			updateQuery = "UPDATE Members "
					+ "set name = ?, "
					+ "email = ? "
					+ "password = ? "
					+ "favorite_language = ? "
					+ "where member_id = ?";
			
			//Create a connection to MariaDB database
			sqlConnect = mariaDB.getConnection();
			
			// Statement needed to run the sql query
			updateStmt = sqlConnect.prepareStatement(updateQuery);

			// Set parameters for the prepared statement
			updateStmt.setString(1, member.getName());
			updateStmt.setString(2,member.getEmail());
			updateStmt.setString(3,member.getPassword());
			updateStmt.setString(4,member.getFavoriteLanguage());
			updateStmt.setInt(5,member.getMemberId());
			
			// run the sql query
			results = updateStmt.executeUpdate();
		} 
		catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
			e.printStackTrace();
		}finally {
			if (updateStmt != null) {
				updateStmt.close();
			}
			if (sqlConnect != null) {
				sqlConnect.close();
			}
		}
		if (results >0) {
			return true;
		}
		return false;
	}
	
	public boolean removeMember(int id) throws SQLException {
		// Create an instance of the mariaDB connection
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		// Declare SQL variables needed to manage connection and read data
		Connection sqlConnect = null;
		String removeQuery = null;
		PreparedStatement removeStmt = null;
		int results = -1;
			
		try {
			//Create a connection to MariaDB database
			sqlConnect = mariaDB.getConnection();
			
			// SQL query to be run to add student data to SQL table
			removeQuery = "DELETE FROM Members where member_id = ?";
			
			// Statement needed to run the sql query
			removeStmt = sqlConnect.prepareStatement(removeQuery);
	
			// Set parameters for the prepared statement
			removeStmt.setInt(1, id);
			
			// run the sql query 
			results = removeStmt.executeUpdate();
			// Returns: either (1) the row count for SQL Data Manipulation Language (DML) statements or 
			// (2) 0 for SQL statements that return nothing
		} 
		catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			if (removeStmt != null) {
				removeStmt.close();
			}
			if (sqlConnect != null) {
				sqlConnect.close();
			}
		}
		if (results >0) {
			return true;
		}
		return false;
	}
	
}
