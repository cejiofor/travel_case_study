package com.perscholas.travelcorps.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession; //TOMACATE HTTPSESSION
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.perscholas.travelcorps.models.Organization;
import com.perscholas.travelcorps.models.OrganizationUser;
import com.perscholas.travelcorps.models.User;
import com.perscholas.travelcorps.models.Volunteer;
import com.perscholas.travelcorps.repositories.OrgRepository;
import com.perscholas.travelcorps.repositories.OrgUserRepository;
import com.perscholas.travelcorps.repositories.UserRepository;

@Controller
@SessionAttributes("user")
public class OrgUserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrgUserRepository orgUserRepository;
	@Autowired 
	private OrgRepository orgRepository;
	
	@GetMapping("/showOrgUserLogin")
	public String showOrgUserLogin(Model model) {	
		if (!model.containsAttribute("user")) {
			model.addAttribute("user", new OrganizationUser());
		}
		return "OrgUserLoginPage";
	}
		
	@PostMapping("/loginOrgUser")
	public String loginOrgUser(@Valid @ModelAttribute("user") OrganizationUser orgUser, BindingResult result, Model model, HttpSession session) throws ClassNotFoundException, IOException, SQLException{
		if (result.hasErrors()) {
			return "OrgUserLoginPage";
		}
		String testUserName = orgUser.getUserName();
		String password = orgUser.getPassword();
		Boolean userExists = true;
		try {
			orgUserRepository.getOrgUsersByName(testUserName);
		}
		catch (NullPointerException e){
			userExists = false;
		}
		if (!userExists) {
			model.addAttribute("errorMessage", "Invalid User");
			return "OrgUserLoginPage";
		}
		orgUser = orgUserRepository.getOrgUsersByName(testUserName);
		
		if (orgUser != null) {
			if (orgUser.getPassword().equals(password)) {
				model.addAttribute("user", orgUser);
				return "redirect:/showProjects";
			} 
			else {
				model.addAttribute("errorMessage", "Invalid Password");
				return "OrgUserLoginPage";
			}
		}
		else {
			model.addAttribute("errorMessage", "Invalid User");
			return "OrgUserLoginPage";
		}
	}

	
//	@GetMapping("/orgUserRegistration")
//	public String orgUserRegistration(Model model, @RequestParam String orgName, HttpSession session) {
//		Organization org = null;
//		try {
//			org = orgRepository.getOrgByName(orgName);
//		}
//		catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println(org.getOrgName());
//		model.addAttribute("org", org);
//		session.setAttribute("org", org);
//		
//		if (!model.containsAttribute("orgUser")) {
//			model.addAttribute("orgUser", new OrganizationUser());
//		}
//		return "OrgUserRegistrationPage";
//	}
	
	@GetMapping("/orgUserRegistration")
	public String orgUserRegistration(Model model, HttpSession session) {
		Organization org = (Organization) session.getAttribute("org");
		System.out.println(org.getOrgName());
		model.addAttribute("org", org);
		session.setAttribute("org", org);
		
		if (!model.containsAttribute("user")) {
			model.addAttribute("user", new OrganizationUser());
		}
		return "OrgUserRegistrationPage";
	}
	
	@PostMapping("/registerOrgUser")
	public String registerOrgUser(@Valid @ModelAttribute("user") OrganizationUser orgUser, BindingResult result, Model model, HttpSession session) throws SQLException, ClassNotFoundException, IOException {
		if (result.hasErrors()) {
			return "OrgUserRegistrationPage";
		} 
		String userName = orgUser.getUserName();
		Boolean userExists = true;
		try {
			orgUserRepository.getOrgUsersByName(userName);
		}
		catch (NullPointerException e){
			userExists = false;
		}
		if (userExists) {
			model.addAttribute("errorMessage", "Username Already Exists");
			return "OrgUserRegistrationPage";
		}
		String password = orgUser.getPassword();
		String firstName = orgUser.getFirstName();
		String lastName = orgUser.getLastName();
		String address = orgUser.getAddress();
		String city = orgUser.getCity();
		String state = orgUser.getState();
		String country = orgUser.getCountry();
		Boolean isVolunteer = orgUser.getIsVolunteer();
		Boolean isPrimeContact = orgUser.getIsPrimeContact();
		Organization org = (Organization) session.getAttribute("org");
		
		User u = new User(userName, password, firstName, lastName, address, city, state, country, isVolunteer);
		Integer userId = userRepository.registerUser(u);
		u.setUserId(userId);
		System.out.println("New User: "+userId);
		
		OrganizationUser ou = new OrganizationUser(userId, userName, password, firstName, lastName, address, city, state, country, isVolunteer, org.getOrgID(), isPrimeContact);
		Integer orgUserId = orgUserRepository.registerOrgUsers(ou);
		System.out.println("New OrgUser: "+orgUserId);
		orgUser.setOrgUserId(orgUserId);
		
		if(isPrimeContact) {
			orgRepository.updatePrimeContact(org.getOrgID(), orgUserId);
		}
		return "redirect:/showOrgUserLogin";
	}
	
	@GetMapping("/showOrgUserProfile")
	public String showOrgUserProfile(Model model) {
		return "OrgUserProfilePage";
	}
	
	@GetMapping("/showOrgUserUpdate")
	public String showOrgUserUpdate(Model model) {
		return "OrgUserUpdateProfile";
	}
	
	@PostMapping("/updateOrgUser")
	public String updateOrgUser(@Valid @ModelAttribute("user") OrganizationUser orgUser, BindingResult result, Model model, HttpSession session) throws SQLException, ClassNotFoundException, IOException {
		Integer userId = orgUser.getUserId();
		String userName = orgUser.getUserName();
		String password = orgUser.getPassword();
		String firstName = orgUser.getFirstName();
		String lastName = orgUser.getLastName();
		String address = orgUser.getAddress();
		String city = orgUser.getCity();
		String state = orgUser.getState();
		String country = orgUser.getCountry();
		Boolean isVolunteer = orgUser.getIsVolunteer();
//		Boolean isPrimeContact = orgUser.getIsPrimeContact();
//		Integer orgID = orgUser.getOrgId();
		
		User u = new User(userId, userName, password, firstName, lastName, address, city, state, country, isVolunteer);
		Boolean userUpdated = userRepository.updateUser(u);
		System.out.println("User Update: " + userUpdated);
		
//		OrganizationUser ou = new OrganizationUser(userId, userName, password, firstName, lastName, address, city, state, country, isVolunteer, orgID, isPrimeContact);
		Boolean orgUserUpdated = orgUserRepository.updateOrgUsers(orgUser);
		System.out.println("Org User Update: " + orgUserUpdated);
		return "redirect:/showOrgUserProfile"; 
	}
	
	@PostMapping("/removeOrgUser")
	public String removeOrgUser(@Valid @ModelAttribute("user") OrganizationUser orgUser, BindingResult result, Model model, HttpSession session) throws SQLException, ClassNotFoundException, IOException {
		Integer orgUserId = orgUser.getOrgUserId();
		Integer userId = orgUser.getUserId();
		Boolean userRemoved = userRepository.removeUser(userId);
		System.out.println("User Removed: " + userRemoved);
		Boolean orgUserRemoved = orgUserRepository.removeOrgUsers(orgUserId);
		System.out.println("Org User Removed: " + orgUserRemoved);
		return "redirect:/"; 
	}
	 
}
