package com.perscholas.travelcorps.controller;

import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes
public class HomeController {		
//	@GetMapping("/")
//	public String showDefault(Model model) {
//		return "SplashPage";
//	}
	
	@GetMapping("/logout")
	public String logout(Model model, HttpSession session, SessionStatus status) {
		status.setComplete();
		Enumeration<String> attributes = session.getAttributeNames();
		while (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    session.removeAttribute(attribute);
		}
		return "redirect:/";
	}
	 
}
