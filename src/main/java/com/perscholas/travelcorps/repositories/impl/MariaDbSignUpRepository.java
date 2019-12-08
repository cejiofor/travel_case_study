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
import com.perscholas.travelcorps.repositories.OrgUserRepository;
import com.perscholas.travelcorps.repositories.UserRepository;

//@Repository("mariaDbsqlIndex = Repository")
@Repository("mariaDbOrgUserRepository")
public class MariaDbSignUpRepository implements OrgUserRepository {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private NamedParameterJdbcTemplate mariaDbJdbcTemplate;
	
	

	@Override
	public List<OrganizationUser> getAllOrgUsers() throws SQLException {
		String selectOrgUsers = "SELECT * FROM org_users";
		List<OrganizationUser> result = mariaDbJdbcTemplate.query(selectOrgUsers, new OrgUserMapper());
		return result;
	}

	@Override
	public Integer registerOrgUsers(OrganizationUser orgUser) throws SQLException, ClassNotFoundException, IOException {
		Integer id = -1;
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("orgUserId", orgUser.getOrgUserId());
		params.addValue("userId", orgUser.getUserId());
		params.addValue("orgId",  orgUser.getOrgId());
		params.addValue("isPrimeContact", orgUser.getIsPrimeContact());
		String createUserSql = "insert into org_users (org_user_id, user_id, org_id, prime_contact) values (:orgUserId, :userId, :orgId, :isPrimeContact)";		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		Integer createResult = mariaDbJdbcTemplate.update(createUserSql, params, keyHolder);
		if (createResult > 0) {
			id = keyHolder.getKey().intValue();
		}
		return id;
	}

	@Override
	public OrganizationUser getOrgUsersById(int orgUserId) throws ClassNotFoundException, IOException, SQLException {
		String selectOrgUserById = "select * from org_users where org_user_id = :orgUserId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgUserId", orgUserId);
		OrganizationUser orgUser = null;
		try
		{
			orgUser = (OrganizationUser)mariaDbJdbcTemplate.queryForObject(selectOrgUserById, params, new OrgUserMapper());
		}
		catch (EmptyResultDataAccessException e)
		{
			System.out.println(e.getMessage());
		}
		return orgUser;
	}

	@Override
	public OrganizationUser getOrgUsersByName(String name) throws ClassNotFoundException, IOException, SQLException {
		User u = userRepository.getUserByName(name);
		Integer id = u.getUserId();
		String selectOrgUserById = "select * from org_users where user_id = :user_id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", id);
		OrganizationUser orgUser = null;
		try
		{
			orgUser = (OrganizationUser)mariaDbJdbcTemplate.queryForObject(selectOrgUserById, params, new OrgUserMapper());
			
		}
		catch (EmptyResultDataAccessException e)
		{
			System.out.println(e.getMessage());
		}
		orgUser.setUserName(name);
		orgUser.setPassword(u.getPassword());
		orgUser.setFirstName(u.getFirstName());
		orgUser.setLastName(u.getLastName());
		orgUser.setAddress(u.getAddress());
		orgUser.setCity(u.getCity());
		orgUser.setState(u.getState());
		orgUser.setCountry(u.getCountry());
		orgUser.setIsVolunteer(u.getIsVolunteer());
		return orgUser;
	}

	@Override
	public Boolean updateOrgUsers(OrganizationUser orgUser) throws SQLException, ClassNotFoundException, IOException {
		Integer result;
		Map<String, Object> params = new HashMap<>();
		params.put("orgUserId", orgUser.getOrgUserId());
		params.put("userId", orgUser.getUserId());
		params.put("orgId",  orgUser.getOrgId());
		params.put("isPrimeContact", orgUser.getIsPrimeContact());
		String updateSql = "update org_users set prime_contact = :isPrimeContact where org_user_id = :orgUserId";		
		result = mariaDbJdbcTemplate.update(updateSql, params);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean removeOrgUsers(int orgUserId) throws IOException, SQLException {
		Integer result;
		String deleteSql = "delete from org_users where org_user_id = :orgUserId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgUserId", orgUserId);
		result = mariaDbJdbcTemplate.update(deleteSql, params);
		if (result > 0) {
			return true;
		}
		return false;
	}
	
	
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
