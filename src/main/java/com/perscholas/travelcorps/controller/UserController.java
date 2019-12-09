package com.perscholas.travelcorps.controller;

import java.io.IOException;
import java.sql.SQLException;

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

import com.perscholas.travelcorps.models.OrganizationUser;
import com.perscholas.travelcorps.models.User;
import com.perscholas.travelcorps.repositories.OrgRepository;
import com.perscholas.travelcorps.repositories.OrgUserRepository;
import com.perscholas.travelcorps.repositories.UserRepository;

@Controller
@SessionAttributes("orgUser")
public class UserController {
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
		user = userRepository.getUserByName(testUserName);
		
		if (orgUser != null) {
			if (orgUser.getPassword().equals(password)) {
				model.addAttribute("orgUser", orgUser);
				return "redirect:/showWelcome";
			} 
			else {
				model.addAttribute("errorMessage", "Invalid Password");
				return "LoginPage";
			}
		}
		else {
			model.addAttribute("errorMessage", "Invalid User");
			return "LoginPage";
		}
	}
	
	@GetMapping("/showWelcome")
	public String showWelcome(Model model) {
		if (!model.containsAttribute("orgUser")) {
			model.addAttribute("orgUser", new OrganizationUser());
		}
		return "WelcomePage";
	}
	
	@GetMapping("/showRegistration")
	public String showRegistration(Model model) {
		if (!model.containsAttribute("orgUser")) {
			model.addAttribute("orgUser", new OrganizationUser());
		}
		return "RegistrationPage";
	}
//	
//	@PostMapping("/registerUser")
//	public String registerUser(@Valid @ModelAttribute("orgUser") User orgUser, BindingResult result, Model model, HttpSession session) throws SQLException, ClassNotFoundException, IOException {
//		String userName = orgUser.getUserName();
//		String password = orgUser.getPassword();
//		String firstName = orgUser.getFirstName();
//		String lastName = orgUser.getLastName();
//		String address = orgUser.getAddress();
//		String city = orgUser.getCity();
//		String state = orgUser.getState();
//		String country = orgUser.getCountry();
//		Boolean isVolunteer = orgUser.getIsVolunteer();
//		List<String> skills = new ArrayList<String>();
//		
//		User u = new User(userName, password, firstName, lastName, address, city, state, country, isVolunteer);
//		Integer userId = userRepository.registerUser(u);
//		u.setUserId(userId);
//		System.out.println(userId);
//		Volunteer v = new Volunteer(userId, userName, password, firstName, lastName, address, city, state, country, isVolunteer, skills);
//		Integer volunteerId = userRepository.registerUser(v);
//		System.out.println(volunteerId);
//		return "redirect:/"; //clears the form, prevent resumbmission of data
//		//also allows us to perform logic on the the data after submission.
//	}
	
	@GetMapping("/logout")
	public String logout(Model model, HttpSession session, SessionStatus status) {
		status.setComplete();
	    session.removeAttribute("orgUser");
		return "redirect:/";
	}
	 
}
