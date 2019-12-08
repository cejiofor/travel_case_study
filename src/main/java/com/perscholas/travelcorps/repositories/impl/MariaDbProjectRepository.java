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

import com.perscholas.travelcorps.models.Project;
import com.perscholas.travelcorps.models.User;
import com.perscholas.travelcorps.repositories.OrgRepository;
import com.perscholas.travelcorps.repositories.ProjectRepository;

@Repository("mariaDbsqlIndex = Repository")
public class MariaDbProjectRepository implements ProjectRepository {
	@Autowired
	private NamedParameterJdbcTemplate mariaDbJdbcTemplate;
	
	@Autowired
	private OrgRepository orgRepository;
	
	@Override
	public List<Project> getAllProjects() throws SQLException {
		String selectProjects = "SELECT * FROM projects";
		List<Project> result = mariaDbJdbcTemplate.query(selectProjects, new ProjectMapper());
		return result;
	}

	@Override
	public List<Project> getProjectsbyOrg(int orgId) throws SQLException {
		List<Integer> projectIdList = new ArrayList<Integer>();
		try {
			projectIdList = orgRepository.getOrgById(orgId).getProjects();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
		}
		List<Project> projectList = new ArrayList<Project>();
		Project project = null;
		for (int i = 0; i<projectList.size(); i++) {
			try {
				project = this.getProjectById(projectIdList.get(i));
			} catch (ClassNotFoundException | IOException e) {
				System.out.println(e.getMessage());
			}
			projectList.add(project);
		}
		return projectList;
	}

	@Override
	public Integer registerProjects(Project p) throws SQLException, ClassNotFoundException, IOException {
		Integer id = -1;
		MapSqlParameterSource params = new MapSqlParameterSource();
		String skillNum = "";
		String skillAdd = "";
		String skillParams = "";
		params.addValue("projectName", p.getProjectName());
		params.addValue("city", p.getCity());
		params.addValue("country", p.getCountry());
		params.addValue("startDate", Project.getSqlDate(p.getStartDate()));
		params.addValue("endDate",  Project.getSqlDate(p.getEndDate()));
		params.addValue("orgId", p.getOrgID());
		List<String> skills = p.getSkills();
		for (int i = 0; i<skills.size(); i++) {
			skillNum = String.format("skill%d", i+1);
			skillAdd = skillAdd + ", "+skillNum;
			skillParams = skillParams + ", :" + skillNum;
			params.addValue(skillNum, skills.get(i));
		}
		String createUserSql = "insert into projects (project_name, city, country, startDate, endDate, ord_id"+skillAdd+") values (:project_name, :city, :country, :startDate, :endDate, :ord_id"+skillParams+")";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		Integer createResult = mariaDbJdbcTemplate.update(createUserSql, params, keyHolder);
		if (createResult > 0) {
			id = keyHolder.getKey().intValue();
		}
		return id;
	}

	@Override
	public Project getProjectById(int projectId) throws ClassNotFoundException, IOException, SQLException {
		String selectProjectById = "select * from projects where project_id = :projectId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectId", projectId);
		Project project = null;
		try
		{
			project = (Project)mariaDbJdbcTemplate.queryForObject(selectProjectById, params, new ProjectMapper());
		}
		catch (EmptyResultDataAccessException e)
		{
			System.out.println(e.getMessage());
		}
		return project;
	}

	@Override
	public Project getProjectByName(String name) throws ClassNotFoundException, IOException, SQLException {
		String selectProjectByName = "select * from projects where project_name = :name";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		Project project = null;
		try
		{
			project = (Project)mariaDbJdbcTemplate.queryForObject(selectProjectByName, params, new ProjectMapper());
		}
		catch (EmptyResultDataAccessException e)
		{
			System.out.println(e.getMessage());
		}
		return project;
	}

	@Override
	public Boolean updateProject(Project p) throws SQLException, ClassNotFoundException, IOException {
		Integer result;
		Map<String, Object> params = new HashMap<>();
		params.put("projectId", p.getProjectID());
		String skillNum = "";
		String skillParams = "";
		params.put("projectName", p.getProjectName());
		params.put("city", p.getCity());
		params.put("country", p.getCountry());
		params.put("startDate", Project.getSqlDate(p.getStartDate()));
		params.put("endDate",  Project.getSqlDate(p.getEndDate()));
		params.put("orgId", p.getOrgID());
		List<String> skills = p.getSkills();
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
		String updateSql = "update projects set project_name = :projectName, city = :city, country = :country, startDate = :startDate, endDate = :endDate, org_id = :orgId "+skillParams+" where project_id = :projectId";
		result = mariaDbJdbcTemplate.update(updateSql, params);		
		if (result > 0) {
			return true;
		}
		return false;	
	}

	@Override
	public Boolean removeProject(int projectId) throws IOException, SQLException {
		Integer result;
		String deleteSql = "delete from projects where project_id = :project_id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("project_id", projectId);
		result = mariaDbJdbcTemplate.update(deleteSql, params);
		if (result > 0) {
			return true;
		}
		return false;
	}
	
	private final class ProjectMapper implements RowMapper<Project> {
		@Override
		public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
			Project p = new Project();
			p.setProjectID(rs.getInt(1));
			p.setProjectName(rs.getString(2));
			p.setCity(rs.getString(3));
			p.setCountry(rs.getString(4));
			p.setStartDate(Project.getUtilDate(rs.getDate(5)));
			p.setEndDate(Project.getUtilDate(rs.getDate(6)));
			p.setOrgID(rs.getInt(7));
			List<String> skills = new ArrayList<String>();
			int sqlIndex = 8;
			while (sqlIndex < 13) {
				skills.add(rs.getString(sqlIndex));
				sqlIndex++;
			}
			p.setSkills(skills);
			return p;
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
