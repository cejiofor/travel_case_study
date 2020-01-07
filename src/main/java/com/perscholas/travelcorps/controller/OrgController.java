package com.perscholas.travelcorps.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.perscholas.travelcorps.models.Organization;
import com.perscholas.travelcorps.models.User;
import com.perscholas.travelcorps.models.Volunteer;
import com.perscholas.travelcorps.repositories.OrgRepository;
import com.perscholas.travelcorps.repositories.OrgUserRepository;
import com.perscholas.travelcorps.repositories.UserRepository;

@Controller
//@RequestMapping
@SessionAttributes//("org")
public class OrgController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrgUserRepository orgUserRepository;
	@Autowired 
	private OrgRepository orgRepository;
	private int test;
	
	@GetMapping("/orgSelection")
	public String orgSelection(Model model, HttpSession session) throws SQLException {
		if (!model.containsAttribute("org")) {
			model.addAttribute("org", new Organization());
		}
		List<Organization> orgList = new ArrayList<Organization>();
		try {
			orgList = orgRepository.getAllOrgs();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		model.addAttribute("orgList", orgList);
//		attributes.addAttribute("orgList", orgList);
//		session.setAttribute("orgList", orgList);
		
		return "OrgSelectionPage";
	}
	
	@GetMapping("/orgRegistration")
	public String orgRegistration(Model model, HttpSession session) throws SQLException {
		if (!model.containsAttribute("org")) {
			model.addAttribute("org", new Organization());
		}
		return "OrgRegistrationPage";
	}
	
	@PostMapping("/registerOrg")
	public String registerOrg(@Valid @ModelAttribute("org") Organization org, BindingResult result, Model model, HttpSession session) throws SQLException, ClassNotFoundException, IOException {
		if (result.hasErrors()) {
			return "OrgRegistrationPage";
		} 
		String orgName = org.getOrgName();
		Boolean orgExists = false;
		try {
			orgRepository.getOrgByName(orgName);
		}
		catch (SQLException e){
			orgExists = true;
		}
		if (orgExists) {
			model.addAttribute("errorMessage", "Partner Org Already Exists");
			return "OrgRegistrationPage";
		}
		String website = org.getWebsite();
		String mission = org.getMission();
		String email = org.getEmail();
		String address = org.getAddress();
//		Integer primeContactId = 0;
		//org.getPrimeContactId();
		
//		Organization o = new Organization(orgName, website, mission, email, address, primeContactId);
//		Integer orgId = orgRepository.registerOrg(o);
		Integer orgId = orgRepository.registerOrg(org);
//		o.setOrgID(orgId);
		org.setOrgID(orgId);
		System.out.println("New Org: "+orgId);
		session.setAttribute("org", org);
		return "redirect:/orgUserRegistration";
	}
	
	@GetMapping("/showOrgProfile")
	public String showOrgProfile(Model model) {
		return "OrgProfilePage";
	}
	
	@GetMapping("/showOrgUpdate")
	public String showOrgUpdate(Model model) {
		return "OrgUpdateProfile";
	}
	
	@PostMapping("/updateOrg")
	public String updateOrg(@Valid @ModelAttribute("org") Organization org, BindingResult result, Model model, HttpSession session) throws SQLException, ClassNotFoundException, IOException {
		if (result.hasErrors()) {
			return "OrgUpdateProfile";
		} 
		
		Integer orgId = org.getOrgID();
		String orgName = org.getOrgName();
		String website = org.getWebsite();
		String mission = org.getMission();
		String email = org.getEmail();
		String address = org.getAddress();
		Integer primeContactId = 0;//org.getPrimeContactId();
		
		Organization o = new Organization(orgName, website, mission, email, address, primeContactId);
		
		Boolean orgUpdated = orgRepository.updateOrg(org);
		System.out.println("Org Updated: "+orgUpdated);
		return "redirect:/showOrgProfile";
	}
}
