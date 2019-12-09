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
import org.springframework.web.bind.annotation.PostMapping;
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
@SessionAttributes("orgUser")
public class OrgUserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrgUserRepository orgUserRepository;
	@Autowired 
	private OrgRepository orgRepository;
		
//	@GetMapping("/")
//	public String showDefault(Model model) {
//		if (!model.containsAttribute("orgUser")) {
//			model.addAttribute("orgUser", new User());
//		}
//		return "LoginPage";
//	}
	
	@GetMapping("/showOrgLogin")
	public String showOrgLogin(Model model) {
		if (!model.containsAttribute("orgUser")) {
			model.addAttribute("orgUser", new OrganizationUser());
		}
		return "OrgLoginPage";
	}
		
	@PostMapping("/loginOrgUser")
	public String loginOrgUser(@Valid @ModelAttribute("orgUser") OrganizationUser orgUser, BindingResult result, Model model, HttpSession session) throws ClassNotFoundException, IOException, SQLException{
		if (result.hasErrors()) {
			return "OrgLoginPage";
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
			return "VolunteerLoginPage";
		}
		orgUser = orgUserRepository.getOrgUsersByName(testUserName);
		
		if (orgUser != null) {
			if (orgUser.getPassword().equals(password)) {
				model.addAttribute("orgUser", orgUser);
				return "redirect:/showWelcome";
			} 
			else {
				model.addAttribute("errorMessage", "Invalid Password");
				return "OrgLoginPage";
			}
		}
		else {
			model.addAttribute("errorMessage", "Invalid User");
			return "OrgLoginPage";
		}
	}
	
	@GetMapping("/showOrgWelcome")
	public String showOrgWelcome(Model model) {
		if (!model.containsAttribute("orgUser")) {
			model.addAttribute("orgUser", new OrganizationUser());
		}
		return "WelcomePage";
	}
	
	@GetMapping("/orgUserRegistration")
	public String orgUserRegistration(Model model) {
		if (!model.containsAttribute("orgUser")) {
			model.addAttribute("orgUser", new OrganizationUser());
		}
		return "OrgUserRegistrationPage";
	}
	@PostMapping("/registerOrgUser")
	public String registerOrgUser(@Valid @ModelAttribute("orgUser") OrganizationUser orgUser, BindingResult result, Model model, HttpSession session) throws SQLException, ClassNotFoundException, IOException {
		if (result.hasErrors()) {
			System.out.println("Not Working!!!");
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
		Organization org = (Organization) session.getAttribute("curretOrg");
		User u = new User(userName, password, firstName, lastName, address, city, state, country, isVolunteer);
		Integer userId = userRepository.registerUser(u);
		u.setUserId(userId);
		System.out.println(userId);
		OrganizationUser ou = new OrganizationUser(userId, userName, password, firstName, lastName, address, city, state, country, isVolunteer, org.getOrgID(), isPrimeContact);
		Integer orgUserId = orgUserRepository.registerOrgUsers(ou);
		System.out.println(orgUserId);
		orgUser.setOrgUserId(orgUserId);
		return "redirect:/showOrgLogin";
	}
	
	@GetMapping("/showOrgUserProfile")
	public String showOrgUserProfile(Model model) {
		return "OrgUserProfilePage";
	}
	
	@GetMapping("/showOrgUserUpdate")
	public String showOrgUserUpdate(Model model) {
		return "UpdateOrgUserProfile";
	}
	
	@PostMapping("/updateOrgUser")
	public String updateOrgUser(@Valid @ModelAttribute("orgUser") OrganizationUser orgUser, BindingResult result, Model model, HttpSession session) throws SQLException, ClassNotFoundException, IOException {
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
	public String removeOrgUser(@Valid @ModelAttribute("orgUser") OrganizationUser orgUser, BindingResult result, Model model, HttpSession session) throws SQLException, ClassNotFoundException, IOException {
		if (result.hasErrors()) {
			System.out.println("Not Working!!!");
			return "OrgUserRegistrationPage";
		}
		
		Integer orgUserId = orgUser.getOrgUserId();
		Integer userId = orgUser.getUserId();
		Boolean userRemoved = userRepository.removeUser(userId);
		System.out.println("User Removed: " + userRemoved);
		Boolean orgUserRemoved = orgUserRepository.removeOrgUsers(orgUserId);
		System.out.println("Org User Removed: " + orgUserRemoved);
		return "redirect:/"; 
	}
	 
}
