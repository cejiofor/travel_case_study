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

import com.perscholas.travelcorps.models.OrganizationUser;
import com.perscholas.travelcorps.models.User;
import com.perscholas.travelcorps.models.Volunteer;
import com.perscholas.travelcorps.repositories.SignUpRepository;
import com.perscholas.travelcorps.repositories.UserRepository;

@Repository("mariaDbSignUpRepository")
public class MariaDbSignUpRepository implements SignUpRepository {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private NamedParameterJdbcTemplate mariaDbJdbcTemplate;
	
	@Override
	public Boolean signUpForProject(Integer volunteerId, Integer projectId)
			throws SQLException, ClassNotFoundException, IOException {
		Integer result;
		Map<String, Object> params = new HashMap<>();
		params.put("signups_volunteer_id", volunteerId);
		params.put("signups_project_id",  projectId);
		String signUpSql = "insert into project_signups (signups_volunteer_id, signups_project_id) VALUES (:signups_volunteer_id, :signups_project_id)";		
		result = mariaDbJdbcTemplate.update(signUpSql, params);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean cancelProjectSignup(Integer volunteerId, Integer projectId) throws IOException, SQLException {
		Integer result;
		Map<String, Object> params = new HashMap<>();
		params.put("signups_volunteer_id", volunteerId);
		params.put("signups_project_id",  projectId);
		String deleteSql = "delete from project_signups where signups_volunteer_id = :signups_volunteer_id and signups_project_id = :signups_project_id";		
		result = mariaDbJdbcTemplate.update(deleteSql, params);
		if (result > 0) {
			return true;
		}
		return false;
	}
	
//	@Override
//	public Boolean updateOrgUsers(OrganizationUser orgUser) throws SQLException, ClassNotFoundException, IOException {
//		Integer result;
//		Map<String, Object> params = new HashMap<>();
//		params.put("orgUserId", orgUser.getOrgUserId());
//		params.put("userId", orgUser.getUserId());
//		params.put("orgId",  orgUser.getOrgId());
//		params.put("isPrimeContact", orgUser.getIsPrimeContact());
//		String updateSql = "update org_users set prime_contact = :isPrimeContact where org_user_id = :orgUserId";		
//		result = mariaDbJdbcTemplate.update(updateSql, params);
//		if (result > 0) {
//			return true;
//		}
//		return false;
//	}
	
	private final class OrgUserMapper implements RowMapper<OrganizationUser> {
		@Override
		public OrganizationUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrganizationUser ou = new OrganizationUser();
			ou.setOrgUserId(rs.getInt(1));
			ou.setUserId(rs.getInt(2));
			ou.setOrgId(rs.getInt(3));
			ou.setIsPrimeContact(rs.getBoolean(4));
			return ou;
		}
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
	
	private final class SignUpMapper implements RowMapper<User> {
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
