package com.perscholas.travelcorps.repositories;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.perscholas.travelcorps.models.OrganizationUser;

public interface OrgUserRepository {
	List<OrganizationUser> getAllOrgUsers() throws SQLException;	
	Integer registerOrgUsers(OrganizationUser orgUser) throws SQLException, ClassNotFoundException, IOException;
	OrganizationUser getOrgUsersById(int orgUserId) throws ClassNotFoundException, IOException, SQLException;
	OrganizationUser getOrgUsersByName(String name) throws ClassNotFoundException, IOException, SQLException;
	Boolean updateOrgUsers(OrganizationUser orgUser) throws SQLException, ClassNotFoundException, IOException;
	Boolean removeOrgUsers(int orgUserId) throws IOException, SQLException;
}
