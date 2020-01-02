package com.perscholas.travelcorps.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.perscholas.travelcorps.models.User;
import com.perscholas.travelcorps.models.Volunteer;
import com.perscholas.travelcorps.repositories.UserRepository;
import com.perscholas.travelcorps.repositories.VolunteerRepository;

@Controller
@SessionAttributes("user")
public class VolunteerController {
	@Autowired
	private UserRepository userRepository;	
	@Autowired
	private VolunteerRepository volunteerRepository;

	
	@GetMapping("/showVolunteerLogin")
	public String showVolunteerLogin(Model model) {
		if (!model.containsAttribute("user")) {
			model.addAttribute("user", new Volunteer());
		}
		return "VolunteerLoginPage";
	}
		
	@PostMapping("/loginVolunteer")
	public String loginVolunteer(@Valid @ModelAttribute("user") Volunteer volunteer, BindingResult result, Model model, HttpSession session) throws ClassNotFoundException, IOException, SQLException{
		if (result.hasErrors()) {
			return "VolunteerLoginPage";
		}
		String testUserName = volunteer.getUserName();
		String password = volunteer.getPassword();
		Boolean userExists = true;
		try {
			volunteerRepository.getVolunteerByName(testUserName);
		}
		catch (NullPointerException e){
			userExists = false;
		}
		if (!userExists) {
			model.addAttribute("errorMessage", "Invalid User");
			return "VolunteerLoginPage";
		}
		volunteer = volunteerRepository.getVolunteerByName(testUserName);
		
		if (volunteer != null) {
			if (volunteer.getPassword().equals(password)) {
				model.addAttribute("user", volunteer);
				return "redirect:/showProjects";
			}
			else {
				model.addAttribute("errorMessage", "Invalid Password");
				return "VolunteerLoginPage";
			}
		}
		else {
			model.addAttribute("errorMessage", "Invalid User");
			return "VolunteerLoginPage";
		}
	}
	
	@GetMapping("/volunteerRegistration")
	public String volunteerRegistration(Model model) {
		if (!model.containsAttribute("user")) {
			model.addAttribute("user", new Volunteer());
		}
		return "VolunteerRegistrationPage";
	}
	
	@PostMapping("/registerVolunteer")
	public String registerVolunteer(@Valid @ModelAttribute("user") Volunteer volunteer, BindingResult result, Model model, HttpSession session) throws SQLException, ClassNotFoundException, IOException {
		if (result.hasErrors()) {
			return "VolunteerRegistrationPage";
		} 
		String userName = volunteer.getUserName();
		Boolean userExists = true;
		try {
			volunteerRepository.getVolunteerByName(userName);
		}
		catch (NullPointerException e){
			userExists = false;
		}
		if (userExists) {
			model.addAttribute("errorMessage", "Username Already Exists");
			return "VolunteerRegistrationPage";
		}
		String password = volunteer.getPassword();
		String firstName = volunteer.getFirstName();
		String lastName = volunteer.getLastName();
		String address = volunteer.getAddress();
		String city = volunteer.getCity();
		String state = volunteer.getState();
		String country = volunteer.getCountry();
		Boolean isVolunteer = volunteer.getIsVolunteer();
		List<String> skills = volunteer.getSkills();
		
		User u = new User(userName, password, firstName, lastName, address, city, state, country, isVolunteer);
		Integer userId = userRepository.registerUser(u);
		u.setUserId(userId);
		System.out.println("New User: "+userId);
		
		Volunteer v = new Volunteer(userId, userName, password, firstName, lastName, address, city, state, country, isVolunteer, skills);
		Integer volunteerId = volunteerRepository.registerVolunteer(v);
		volunteer.setVolunteerId(volunteerId);
		System.out.println("New Volunteer: "+volunteerId);
		
		return "redirect:/showVolunteerLogin";
	}
	
	@GetMapping("/showVolunteerProfile")
	public String showVolunteerProfile(Model model) {
		return "VolunteerProfilePage";
	}
	
	@GetMapping("/showVolunteerUpdate")
	public String showVolunteerUpdate(Model model) {
		return "VolunteerUpdateProfile";
	}
	
	@PostMapping("/updateVolunteer")
	public String updateVolunteer(@Valid @ModelAttribute("user") Volunteer volunteer, BindingResult result, Model model, HttpSession session) throws SQLException, ClassNotFoundException, IOException {
//		Integer volunteerId = volunteer.getVolunteerId();
		Integer userId = volunteer.getUserId();
		String userName = volunteer.getUserName();
		String password = volunteer.getPassword();
		String firstName = volunteer.getFirstName();
		String lastName = volunteer.getLastName();
		String address = volunteer.getAddress();
		String city = volunteer.getCity();
		String state = volunteer.getState();
		String country = volunteer.getCountry();
		Boolean isVolunteer = volunteer.getIsVolunteer();
//		List<String> skills = volunteer.getSkills();
		
		User u = new User(userId, userName, password, firstName, lastName, address, city, state, country, isVolunteer);
		Boolean userUpdated = userRepository.updateUser(u);
		System.out.println("User Update: " + userUpdated);
		
//		Volunteer v = new Volunteer(volunteerId, userId, userName, password, firstName, lastName, address, city, state, country, isVolunteer, skills);
		Boolean volunteerUpdated = volunteerRepository.updateVolunteer(volunteer);
		System.out.println("Volunteer Update: " + volunteerUpdated);
		return "redirect:/showVolunteerProfile"; 
	}
	
	@PostMapping("/removeVolunteer")
	public String removeVolunteer(@Valid @ModelAttribute("user") Volunteer volunteer, BindingResult result, Model model, HttpSession session) throws SQLException, ClassNotFoundException, IOException {
		Integer volunteerId = volunteer.getVolunteerId();
		Integer userId = volunteer.getUserId();
		Boolean userRemoved = userRepository.removeUser(userId);
		System.out.println("User Removed: " + userRemoved);
		Boolean volunteerRemoved = volunteerRepository.removeVolunteer(volunteerId);
		System.out.println("Volunteer Removed: " + volunteerRemoved);
		return "redirect:/"; 
	}
}
