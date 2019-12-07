package com.perscholas.travelcorps.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.perscholas.travelcorps.models.User;

public interface UserRepository {
	List<User> getAllUsers() throws SQLException;	
	Integer registerUser(User user) throws SQLException, ClassNotFoundException, IOException;
	User getUserById(int userId) throws ClassNotFoundException, IOException, SQLException;
	User getUserByName(String name) throws ClassNotFoundException, IOException, SQLException;
	Boolean updateUser(User u) throws SQLException, ClassNotFoundException, IOException;
	Boolean removeUser(int userId) throws IOException, SQLException;
}
