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

import com.perscholas.travelcorps.models.Organization;
import com.perscholas.travelcorps.models.User;
import com.perscholas.travelcorps.repositories.OrgRepository;

@Repository("mariaDbOrgRepository")
public class MariaDbOrgRepository implements OrgRepository {

	@Autowired
	private NamedParameterJdbcTemplate mariaDbJdbcTemplate;
	
	@Override
	public List<Organization> getAllOrgs() throws SQLException {
		String selectOrgs = "SELECT * FROM orgs";
		List<Organization> result = mariaDbJdbcTemplate.query(selectOrgs, new OrgMapper());
		return result;
	}
	
	@Override
	public Integer registerOrg(Organization org) throws SQLException, ClassNotFoundException, IOException{
		Integer id = -1;
		MapSqlParameterSource params = new MapSqlParameterSource();
//		params.addValue("orgID", org.getOrgID());
		params.addValue("orgName", org.getOrgName());
		params.addValue("website", org.getWebsite());
		params.addValue("mission", org.getMission());
		params.addValue("email", org.getEmail());
		params.addValue("address", org.getAddress());
		String createUserSql = "insert into orgs (org_name, website, mission_id, email, address) values (:orgName, :website, :mission, :email, :address)";		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		Integer createResult = mariaDbJdbcTemplate.update(createUserSql, params, keyHolder);
		if (createResult > 0) {
			id = keyHolder.getKey().intValue();
		}
		return id;
	}
	
	@Override
	public Organization getOrgById(int orgId) throws ClassNotFoundException, IOException, SQLException{
		String selectOrgById = "select * from orgs where org_id = :orgId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		Organization org = null;
		try
		{
			org = (Organization)mariaDbJdbcTemplate.queryForObject(selectOrgById, params, new OrgMapper());
		}
		catch (EmptyResultDataAccessException e)
		{
			System.out.println(e.getMessage());
		}
		return org;
	}
	
	
	@Override
	public Organization getOrgByName(String orgName) throws ClassNotFoundException, IOException, SQLException{
		String selectOrgByName = "select * from orgs where org_name = :orgName";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgName", orgName);
		Organization org= null;
		try
		{
			org = (Organization)mariaDbJdbcTemplate.queryForObject(selectOrgByName, params, new OrgMapper());
		}
		catch (EmptyResultDataAccessException e)
		{
			System.out.println(e.getMessage());
		}
		return org;
	}
	
	@Override
	public Boolean updateOrg(Organization org) throws SQLException, ClassNotFoundException, IOException{
		Integer result;
		Map<String, Object> params = new HashMap<>();
		params.put("orgID", org.getOrgID());
		params.put("orgName", org.getOrgName());
		params.put("website", org.getWebsite());
		params.put("mission", org.getMission());
		params.put("email", org.getEmail());
		params.put("address", org.getAddress());
		params.put("primeContactId", org.getPrimeContactId());
		String updateSql = "update orgs set org_name = :orgName, website = :website, mission = :mission, email = :email, address = :address, prime_contact_id = :primeContactId where org_id = :orgID";		
		result = mariaDbJdbcTemplate.update(updateSql, params);
		if (result > 0) {
			return true;
		}
		return false;	
	}
	
	@Override
	public Boolean updatePrimeContact(Integer orgID, Integer orgUserId) throws SQLException, ClassNotFoundException, IOException{
		Integer result;
		Map<String, Object> params = new HashMap<>();
		params.put("orgID", orgID);
		params.put("orgUserId", orgUserId);
		String updateSql = "update orgs set prime_contact_id = :orgUserId where org_id = :orgID";		
		result = mariaDbJdbcTemplate.update(updateSql, params);
		if (result > 0) {
			return true;
		}
		return false;	
	}
	
	@Override
	public Boolean removeOrg(int orgId) throws IOException, SQLException{
		Integer result;
		String deleteSql = "delete from orgs where org_id = :orgId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		result = mariaDbJdbcTemplate.update(deleteSql, params);
		if (result > 0) {
			return true;
		}
		return false;
	}
	
	private final class OrgMapper implements RowMapper<Organization> {
		@Override
		public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
			Organization org = new Organization();
			org.setOrgID(rs.getInt(1));
			org.setOrgName(rs.getString(2));
			org.setWebsite(rs.getString(3));
			org.setMission(rs.getString(4));
			org.setEmail(rs.getString(5));
			org.setAddress(rs.getString(6));
			org.setPrimeContactId(rs.getInt(7));
//			List<Integer> projects = new ArrayList<Integer>();
//			int sqlIndex = 8;
//			while (sqlIndex < 13) {
//				projects.add(rs.getInt(sqlIndex));
//				sqlIndex++;
//			}
//			org.setProjects(projects);
			return org;
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
