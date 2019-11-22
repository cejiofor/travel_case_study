package com.perscholas.travelcorps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.perscholas.travelcorps.models.Event;
import com.perscholas.travelcorps.models.Member;

public class EventDAO {
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
	
	public Integer createEvent(Event event) throws SQLException {
		String title = event.getTitle();
		String description = event.getDescription();
		String location = event.getLocation();
		LocalDateTime dateTime = event.getDateTime();
		Integer memberID = event.getMemberId();
		
		// Create an instance of the mariaDB connection
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		//Declare SQL variables to manage connection and read data
		Connection sqlConnect = null;
		PreparedStatement insertStmt = null;
		String sqlQuery = null;
		Integer result = null;
		
		try {
			//Create a connection to MariaDB database
			sqlConnect = mariaDB.getConnection();
			
			// SQL query to be run to receive data from SQL table
			sqlQuery = "INSERT INTO events (title, description, location, event_time, member_id) values (?, ?, ?, ?, ?)";
			
			// Statement needed to run the sql query
			insertStmt = sqlConnect.prepareStatement(sqlQuery);

			// Set parameters for the prepared statement
			insertStmt.setString(1, title);
			insertStmt.setString(2, description);
			insertStmt.setString(3, location);
			insertStmt.setTimestamp(4,Timestamp.valueOf(dateTime));
			insertStmt.setInt(5, memberID);
			
			
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
	
	public List<Event> getEvents() throws SQLException {
		// List to store all Events
		List<Event> eventList = new ArrayList();
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
			selectQuery = "SELECT * FROM events";
			
			// Statement needed to run the sql query
			selectStmt = sqlConnect.createStatement();
			
			// run the sql query and return the results to a ResultSet variable
			results = selectStmt.executeQuery(selectQuery);
			
			// iterate through the java result set and set student properties from ArrayList properties
			while (results.next())
			{
				Event event = new Event();
				event.setEventId(results.getInt("event_id"));
				event.setTitle(results.getString("title"));
				event.setDescription(results.getString("description"));
				event.setLocation(results.getString("location"));
				event.setDateTime(results.getTimestamp("event_time").toLocalDateTime());
				event.setMemberId(results.getInt("member_id"));
				event.setEventAttendees(getAttendersByEventId(event.getEventId()));
				eventList.add(event);
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
		return eventList;
	}
	
	private List<Member> getAttendersByEventId(Integer eventId) throws SQLException{
		// List to store all Members
		List<Member> eventAttendees = new ArrayList();
		// Create an instance of the mariaDB connection
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		// Declare SQL variables needed to manage connection and read data
		Connection sqlConnect = null;
		PreparedStatement selectStmt = null;
		String selectQuery = null;
		ResultSet results = null;
		Member member = null;
			
		try {
			//Create a connection to MariaDB database
			sqlConnect = mariaDB.getConnection();
			
			// SQL query to be run to get Event from SQL table
//			selectQuery = "SELECT * FROM events WHERE title = ?";
			
			selectQuery = "select members.member_id, members.name, members.email, members.password, "
					+ "members.favorite_language from members join signups on members.member_id = "
					+ "signups.signups_member_id where signups.signups_event_id = ?";
			
			
			// Statement needed to run the sql query
			selectStmt = sqlConnect.prepareStatement(selectQuery);
			selectStmt.setInt(1, eventId);
			
			// run the sql query 
			results = selectStmt.executeQuery();
			
			while (results.next())
			{
				member = new Member();
				member.setMemberId(results.getInt(1));
				member.setName(results.getString(2));
				member.setEmail(results.getString(3));
				member.setPassword(results.getString(4));
				member.setFavoriteLanguage(results.getString(5));
				eventAttendees.add(member);
				
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
		return eventAttendees;
	}
	
	public Event getEvent(String title) throws SQLException {
		// Create an instance of the mariaDB connection
		MariaDBConnection mariaDB = new MariaDBConnection();
		
		// Declare SQL variables needed to manage connection and read data
		Connection sqlConnect = null;
		PreparedStatement selectStmt = null;
		String selectQuery = null;
		ResultSet results = null;
		Event event = new Event();;
			
		try {
			//Create a connection to MariaDB database
			sqlConnect = mariaDB.getConnection();
			
			// SQL query to be run to get Event from SQL table
			selectQuery = "SELECT * FROM events WHERE title = ?";
			
			// Statement needed to run the sql query
			selectStmt = sqlConnect.prepareStatement(selectQuery);
			selectStmt.setString(1, title.toString());
			
			// run the sql query 
			results = selectStmt.executeQuery();
			
			while (results.next())
			{
				
				System.out.println(event.toString());
				event.setEventId(results.getInt("event_id"));
				event.setTitle(results.getString("title"));
				event.setDescription(results.getString("description"));
				event.setLocation(results.getString("location"));		
				event.setDateTime(results.getTimestamp("event_time").toLocalDateTime());
				event.setMemberId(results.getInt("member_id"));
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
		return event;
		
	}
	
	public Boolean updateEvent(Event event) throws SQLException {
		// Create an instance of the mariaDB connection
		MariaDBConnection mariaDB = new MariaDBConnection();

		// Declare SQL variables needed to manage connection and read data
		Connection sqlConnect = null;
		PreparedStatement updateStmt = null;
		String updateQuery = null;
		int results = -1;
		
		try {
			// SQL query to be run to udpate Event data in SQL table
			updateQuery = "UPDATE events "
					+ "set title = ?, "
					+ "description = ? "
					+ "location = ? "
					+ "event_time = ? "
					+ "member_id = ?"
					+ "where event_id = ?";
			
			//Create a connection to MariaDB database
			sqlConnect = mariaDB.getConnection();
			
			// Statement needed to run the sql query
			updateStmt = sqlConnect.prepareStatement(updateQuery);

			// Set parameters for the prepared statement
			updateStmt.setString(1, event.getTitle());
			updateStmt.setString(2,event.getDescription());
			updateStmt.setString(3,event.getLocation());
			updateStmt.setTimestamp(4,Timestamp.valueOf(event.getDateTime()));
			updateStmt.setInt(5,event.getMemberId());
			updateStmt.setInt(6,event.getEventId());
			
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
	
	public boolean removeEvent(int id) throws SQLException {
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
			removeQuery = "DELETE FROM events where event_id = ?";
			
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
