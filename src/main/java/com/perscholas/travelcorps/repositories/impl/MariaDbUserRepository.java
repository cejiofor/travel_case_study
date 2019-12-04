package com.perscholas.travelcorps.repositories.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.perscholas.travelcorps.models.User;
import com.perscholas.travelcorps.repositories.UserRepository;

@Repository("mariaDbUserRepository")
public class MariaDbUserRepository implements UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate mariaDbJdbcTemplate;
	
	@Override
	public List<User> getAllUsers() throws SQLException {
		String selectUsers = "SELECT * FROM users";
		List<User> result = mariaDbJdbcTemplate.query(selectUsers, new UserMapper());
		return result;
	}
	
	@Override
	public Integer registerUser(User user) throws SQLException, ClassNotFoundException, IOException{
		Integer id = -1;
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", user.getUserName());
		params.addValue("password", user.getPassword());
		params.addValue("prime_contact", user.getPrimeContact());
		String createUserSql = "insert into users (username, password, prime_contact) values (:username, :password, :prime_contact)";		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		Integer createResult = mariaDbJdbcTemplate.update(createUserSql, params, keyHolder);
		if (createResult > 0) {
			id = keyHolder.getKey().intValue();
		}
		return id;
	}
	
	@Override
	public User getUserById(int userId) throws ClassNotFoundException, IOException, SQLException{
		String selectUserById = "select * from users where user_id = :userId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		User user = null;
		try
		{
			user = (User)mariaDbJdbcTemplate.queryForObject(selectUserById, params, new UserMapper());
		}
		catch (EmptyResultDataAccessException e)
		{
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	
	@Override
	public User getUserByName(String username) throws ClassNotFoundException, IOException, SQLException{
		String selectUserByName = "select * from users where username = :username";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		User user = null;
		try
		{
			user = (User) mariaDbJdbcTemplate.queryForObject(selectUserByName, params, new UserMapper());
		}
		catch (EmptyResultDataAccessException e)
		{
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	@Override
	public Boolean updateUser(User u) throws SQLException, ClassNotFoundException, IOException{
		Integer result;
		Map<String, Object> params = new HashMap<>();
		params.put("username", u.getUserName());
		params.put("password", u.getPassword());
		params.put("prime_contact", u.getPrimeContact());
		params.put("user_id", u.getUserId());
		String updateSql = "update users set username = :username, password = :password, prime_contact = :prime_contact where user_id = :user_id";
		result = mariaDbJdbcTemplate.update(updateSql, params);
		if (result > 0) {
			return true;
		}
		return false;	
	}
	
	@Override
	public Boolean removeUser(int userId) throws IOException, SQLException{
		Integer result;
		String deleteSql = "delete from susers where user_id = :user_id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", userId);
		result = mariaDbJdbcTemplate.update(deleteSql, params);
		if (result > 0) {
			return true;
		}
		return false;
	}
	
	private final class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User u = new User();
			u.setUserId(rs.getInt(1));
			u.setUserName(rs.getString(2));
			u.setPassword(rs.getString(3));
			u.setPrimeContact(rs.getBoolean(4));
			return u;
		}
	}

}
