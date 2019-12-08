package com.perscholas.travelcorps.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.perscholas.travelcorps.models.Project;
import com.perscholas.travelcorps.models.Volunteer;
import com.perscholas.travelcorps.repositories.ProjectRepository;
import com.perscholas.travelcorps.repositories.SignUpRepository;

@Controller
//@RequestMapping("/project") //class level map
@SessionAttributes
public class ProjectController {
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired 
	private SignUpRepository signUpRepository;
	
	@GetMapping("/showAllProjects")
	public String showAllProjects(Model model) throws SQLException {
		List<Project> projectList = projectRepository.getAllProjects();
		model.addAttribute("projectList", projectList);
		return "ProjectsPage";
	}
	
	@GetMapping("/showProjects")
	public String showProjects(Model model) throws SQLException {
		List<Project> projectList = new ArrayList<Project>();
		try {
			projectList = projectRepository.getAllProjects();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		model.addAttribute("projectList", projectList);
		return "ProjectsPage";
	}
	
	@GetMapping("/showProject")
	public String showProject(@Valid @ModelAttribute("project") Project project, BindingResult result, Model model, @RequestParam Integer volunteerId, @RequestParam Integer projectId) throws ClassNotFoundException, SQLException, IOException{
		List<Project> projectList = new ArrayList<Project>();
		try {
			projectList = projectRepository.getAllProjects();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		model.addAttribute("projectList", projectList);
		return "ProjectPage";
	}
	
	@GetMapping("/projectSignUp")
	public String projectSignUp(@Valid @ModelAttribute("volunteer") Volunteer volunteer, BindingResult result, Model model, @RequestParam Integer volunteerId, @RequestParam Integer projectId) throws ClassNotFoundException, SQLException, IOException{
		signUpRepository.signUpForProject(volunteerId, projectId);
		return "redirect:/showProjects";
	}
	
	@GetMapping("/cancelSignUp")
	public String cancelSignUp(@Valid @ModelAttribute("volunteer") Volunteer volunteer, BindingResult result, Model model, @RequestParam Integer volunteerId, @RequestParam Integer projectId) throws ClassNotFoundException, SQLException, IOException{
		signUpRepository.cancelProjectSignup(volunteerId, projectId);
		return "redirect:/showProjects";
	}
	
//	@PostMapping("/createProject")
//	public String createProject(@Valid @ModelAttribute("orgUser") OrganizationUser orgUser, HttpSession session, Model model) throws SQLException {
//		
//		LocalDateTime ldt = LocalDateTime.parse(dateTime);
//		Member currentMember = (Member) session.getAttribute("currentMember");
//				
//		Event event = new Event(title, description, location, ldt, currentMember.getMemberId());
//		
//		EventDAO edao = new EventDAO();
//		edao.createEvent(event);
//		return "redirect:/events/showEvents";
//		
//	}
	

}
