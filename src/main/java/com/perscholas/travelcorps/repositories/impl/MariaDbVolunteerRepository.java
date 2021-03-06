package com.perscholas.travelcorps.repositories.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.perscholas.travelcorps.models.Volunteer;
import com.perscholas.travelcorps.repositories.UserRepository;
import com.perscholas.travelcorps.repositories.VolunteerRepository;

@Repository("mariaDbsqlIndex = Repository")
//@Repository("mariaDbVolunteerRepository")
public class MariaDbVolunteerRepository implements VolunteerRepository {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private NamedParameterJdbcTemplate mariaDbJdbcTemplate;
	
	@Override
	public List<Volunteer> getAllVolunteers() throws SQLException {
		String selectVolunteers = "SELECT * FROM volunteers";
		List<Volunteer> result = mariaDbJdbcTemplate.query(selectVolunteers, new VolunteerMapper());
		return result;
	}
	
	@Override
	public Integer registerVolunteer(Volunteer v) throws SQLException, ClassNotFoundException, IOException{
		Integer id = -1;
		MapSqlParameterSource params = new MapSqlParameterSource();
		String skillNum = "";
		String skillAdd = "";
		String skillParams = "";
		params.addValue("user_id", v.getUserId());
		System.out.println(v.toString());
		List<String> skills = v.getSkills();
		System.out.println(skills);
		for (int i = 0; i<skills.size(); i++) {
			skillNum = String.format("skill%d", i+1);
			skillAdd = skillAdd + ", "+skillNum;
			skillParams = skillParams + ", :" + skillNum;
			System.out.println(skillNum + "---"+skillAdd+"---"+skillParams);
			System.out.println(skills.get(i));
			params.addValue(skillNum, skills.get(i));
		}
//		params.addValue("skill1", v.getSkills().get(0));
//		params.addValue("skill2", v.getSkills().get(1));
//		params.addValue("skill3", v.getSkills().get(2));
//		params.addValue("skill4", v.getSkills().get(3));
//		params.addValue("skill5", v.getSkills().get(4));
//		String createUserSql = "insert into volunteers (user_Id, skill1, skill2, skill3, skill4, skill5) values (:user_Id, :skill1, :skill2, :skill3, :skill4, :skill5)";
		String createUserSql = "insert into volunteers (user_id"+skillAdd+") values (:user_id"+skillParams+")";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		Integer createResult = mariaDbJdbcTemplate.update(createUserSql, params, keyHolder);
		if (createResult > 0) {
			id = keyHolder.getKey().intValue();
		}
		return id;
	}
	
	@Override
	public Volunteer getVolunteerById(int volunteerId) throws ClassNotFoundException, IOException, SQLException{
		String selectVolunteerById = "select * from volunteers where volunteer_id = :volunteerId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("volunteerId", volunteerId);
		Volunteer volunteer = null;
		try
		{
			volunteer = (Volunteer)mariaDbJdbcTemplate.queryForObject(selectVolunteerById, params, new VolunteerMapper());
		}
		catch (EmptyResultDataAccessException e)
		{
			System.out.println(e.getMessage());
		}
		Integer userId = volunteer.getUserId();
		User u = userRepository.getUserById(userId);
		volunteer.setUserName(u.getUserName());
		volunteer.setPassword(u.getPassword());
		volunteer.setFirstName(u.getFirstName());
		volunteer.setLastName(u.getLastName());
		volunteer.setAddress(u.getAddress());
		volunteer.setCity(u.getCity());
		volunteer.setState(u.getState());
		volunteer.setCountry(u.getCountry());
		volunteer.setIsVolunteer(u.getIsVolunteer());
		return volunteer;
	}
	
	
	@Override
	public Volunteer getVolunteerByName(String username) throws ClassNotFoundException, IOException, SQLException{
		User u = userRepository.getUserByName(username);
		Integer id = u.getUserId();
		String selectVolunteerById = "select * from volunteers where user_id = :user_id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", id);
		Volunteer volunteer = null;
		try
		{
			volunteer = (Volunteer) mariaDbJdbcTemplate.queryForObject(selectVolunteerById, params, new VolunteerMapper());
		}
		catch (EmptyResultDataAccessException e)
		{
			System.out.println(e.getMessage());
		}
		volunteer.setUserName(username);
		volunteer.setPassword(u.getPassword());
		volunteer.setFirstName(u.getFirstName());
		volunteer.setLastName(u.getLastName());
		volunteer.setAddress(u.getAddress());
		volunteer.setCity(u.getCity());
		volunteer.setState(u.getState());
		volunteer.setCountry(u.getCountry());
		volunteer.setIsVolunteer(u.getIsVolunteer());
		return volunteer;
	}
	
	@Override
	public Boolean updateVolunteer(Volunteer v) throws SQLException, ClassNotFoundException, IOException{
		Integer result;
		Map<String, Object> params = new HashMap<>();
		params.put("volunteer_id", v.getVolunteerId());
		String skillNum = "";
		String skillAdd = "";
		String skillParams = "";
		List<String> skills = v.getSkills();
		for (int i = 0; i<skills.size(); i++) {
			skillNum = String.format("skill%d", i+1);
			params.put(skillNum, skills.get(i));
			if (i == 0) {
				skillParams = skillParams + skillNum + "= :" + skillNum;
			}
			else {
				skillParams = skillParams+ ", " + skillNum + "= :" + skillNum;
			}
			
		}
//		params.put("skill1", v.getSkills().get(0));
//		params.put("skill2", v.getSkills().get(1));
//		params.put("skill3", v.getSkills().get(2));
//		params.put("skill4", v.getSkills().get(3));
//		params.put("skill5", v.getSkills().get(4));
//		String updateSql = "update volunteers set skill1 = :skill1, skill2 = :skill2, skill3 = :skill3, skill4 = :skill4, skill5 = :skill5 where volunteer_id = :volunteer_id";
		String updateSql = "update volunteers set "+skillParams+" where volunteer_id = :volunteer_id";
		result = mariaDbJdbcTemplate.update(updateSql, params);		
		if (result > 0) {
			return true;
		}
		return false;	
	}
	
	@Override
	public Boolean removeVolunteer(int volunteerId) throws IOException, SQLException{
		Integer result;
		String deleteSql = "delete from volunteers where volunteer_id = :volunteer_id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("volunteer_id", volunteerId);
		result = mariaDbJdbcTemplate.update(deleteSql, params);
		if (result > 0) {
			return true;
		}
		return false;
	}
	
	private final class VolunteerMapper implements RowMapper<Volunteer> {
		@Override
		public Volunteer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Volunteer v = new Volunteer();
			v.setVolunteerId(rs.getInt(1));
			v.setUserId(rs.getInt(2));
			List<String> skills = new ArrayList<String>();
			int sqlIndex = 3;
			while (sqlIndex < 8) {
				skills.add(rs.getString(sqlIndex));
				sqlIndex++;
			}
			v.setSkills(skills);
			return v;
		}
	}
	
	private final class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User u = new User();
			u.setUserId(rs.getInt(1));
			u.setUserName(rs.getString(2));
			u.setPassword(rs.getString(3));
			u.setFirstName(rs.getString(4));
			u.setLastName(rs.getString(5));
			u.setAddress(rs.getString(6));
			u.setCity(rs.getString(7));
			u.setState(rs.getString(8));
			u.setCountry(rs.getString(9));
			u.setIsVolunteer(rs.getBoolean(10));
			return u;
		}
	}

}
