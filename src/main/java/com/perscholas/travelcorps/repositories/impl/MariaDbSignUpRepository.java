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
	public List<Integer> getVolunteerSignUps(Integer volunteerId) throws SQLException, ClassNotFoundException, IOException {
			Map<String, Object> params = new HashMap<>();
			params.put("volunteer_id",  volunteerId);
			String selectVolunteerProjects = "select signups_project_id FROM project_signups where signups_volunteer_id = :volunteer_id";		
			List<Integer> result = mariaDbJdbcTemplate.queryForList(selectVolunteerProjects, params, Integer.class);
			return result;
	}
	
	@Override
	public List<Integer> getProjectVolunteers(Integer projectId) throws SQLException, ClassNotFoundException, IOException {
			Map<String, Object> params = new HashMap<>();
			params.put("project_id",  projectId);
			String selectProjectVolunteers = "select signups_volunteer_id FROM project_signups where signups_project_id = :project_id";		
			List<Integer> result = mariaDbJdbcTemplate.queryForList(selectProjectVolunteers, params, Integer.class);
			return result;
	}
	
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
	
	private final class IntegerMapper implements RowMapper<Integer> {
		@Override
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getInt(1);
		}
	}

}
