package com.perscholas.travelcorps.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.perscholas.travelcorps.models.Project;
import com.perscholas.travelcorps.repositories.ProjectRepository;

@Controller
//@RequestMapping("/project") //class level map
@SessionAttributes
public class ProjectController {
	@Autowired
	private ProjectRepository projectRepository;
	
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
	
	@PostMapping("/projectSignUp")
	public String projectSignUp(Model model, @RequestParam Integer volunteerId, @RequestParam Integer projectId){
		Project project = projectRepository.getProjectById(projectId);
		, 
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
