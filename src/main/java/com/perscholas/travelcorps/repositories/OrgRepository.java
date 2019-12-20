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

import com.perscholas.travelcorps.models.Organization;

public interface OrgRepository {
	List<Organization> getAllOrgs() throws SQLException;	
	Integer registerOrg (Organization org) throws SQLException, ClassNotFoundException, IOException;
	Boolean updatePrimeContact(Integer orgID, Integer orgUserId) throws SQLException, ClassNotFoundException, IOException;
	Organization getOrgById(int orgId) throws ClassNotFoundException, IOException, SQLException;
	Organization getOrgByName(String name) throws ClassNotFoundException, IOException, SQLException;
	Boolean updateOrg(Organization org) throws SQLException, ClassNotFoundException, IOException;
	Boolean removeOrg(int orgId) throws IOException, SQLException;
}
