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

@Controller
@SessionAttributes
public class UserController {
//	private UserRepository userRepository = new MariaDbUserRepository();
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public String showDefault(Model model) {
		model.addAttribute("user", new User());
		return "LoginPage";
	}
	
	@GetMapping("/showLogin")
	public String showLogin() {
		return "LoginPage";
	}
	
//	@PostMapping("/loginUser")
//	public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session,
//			Model model) throws SQLException, ClassNotFoundException, IOException {
//		System.out.println(username);
//		System.out.println(password);
//		User user = userRepository.getUserByName(username);
//		if (user!= null) {
//			if (password.equals(user.getPassword())) {
//				session.setAttribute("currentUser", user);
//				return "redirect:/showWelcome";
//			} 
//			else {
//				model.addAttribute("errorMessage", "Invalid Password");
//				return "LoginPage";
//			}
//		}
//		else {
//			model.addAttribute("errorMessage", "Invalid User");
//			return "LoginPage";
//		}
//	}
		
	@PostMapping("/loginUser")
	public String loginUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) throws ClassNotFoundException, IOException, SQLException{
		if (result.hasErrors()) {
			return "LoginPage";
		}
		String testUserName = user.getUserName();
		String password = user.getPassword();
		user = userRepository.getUserByName(testUserName);
		
		if (user != null) {
			if (user.getPassword().equals(password)) {
				model.addAttribute("user", user);
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
		model.addAttribute("user", new User());
		return "WelcomePage";
	}
	
	@GetMapping("/showRegistration")
	public String showRegistration(Model model) {
		model.addAttribute("user", new User());
		return "RegistrationPage";
	}
	
	@PostMapping("/registerUser")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) throws SQLException, ClassNotFoundException, IOException {
		String userName = user.getUserName();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String address = user.getAddress();
		String city = user.getCity();
		String state = user.getState();
		String country = user.getCountry();
		Boolean isVolunteer = user.getIsVolunteer();
		List<String> skills = new ArrayList<String>();
		
		User u = new User(userName, password, firstName, lastName, address, city, state, country, isVolunteer);
		Integer userId = userRepository.registerUser(u);
		u.setUserId(userId);
		System.out.println(userId);
		Volunteer v = new Volunteer(userId, userName, password, firstName, lastName, address, city, state, country, isVolunteer, skills);
		Integer volunteerId = userRepository.registerUser(v);
		System.out.println(volunteerId);
		return "redirect:/"; //clears the form, prevent resumbmission of data
		//also allows us to perform logic on the the data after submission.
	}
}
