package com.perscholas.travelcorps.controller;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.perscholas.travelcorps.dao.MemberDAO;
import com.perscholas.travelcorps.models.Member;

@Controller
@SessionAttributes
public class MemberController {
	
	@GetMapping("/")
	public String showDefault() {
		return "LoginPage";
	}
	
	@GetMapping("/showLogin")
	public String showLogin() {
		return "LoginPage";
	}
	
	@PostMapping("/loginMember")
	public String loginMember(@RequestParam String name, 
			@RequestParam String password, 
			HttpSession session,
			Model model) throws SQLException {
		MemberDAO mdao = new MemberDAO();
		Member m = mdao.getMember(name);
		
		if (m!= null) {
			if (password.equals(m.getPassword())) {
				session.setAttribute("currentMember", m);
				return "WelcomePage";
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
	public String showWelcome() {
		return "WelcomePage";
	}
	
	@GetMapping("/showRegistration")
	public String showRegistration() {
		return "RegistrationPage";
	}
	
	@PostMapping("/registerMember")
	public String registerMember(@RequestParam String name, 
			@RequestParam String email, 
			@RequestParam String password, 
			@RequestParam String favoriteLanguage, 
			HttpSession session,
			Model model) throws SQLException {
		MemberDAO mdao = new MemberDAO();
		Member m = new Member(name, email, password, favoriteLanguage);
		Integer i = mdao.createMember(m);
		m.setMemberId(i);
		System.out.println(i);
		return "redirect:/"; //clears the form, prevent resumbmission of data
		//also allows us to perform logic on the the data after submission.
	}
}
