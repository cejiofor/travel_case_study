package com.perscholas.travelcorps.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
	
	@GetMapping("/projectCreation")
	public String projectCreation(Model model) {
		if(!model.containsAttribute("project")) {
			model.addAttribute("project", new Project());
		}
		return "NewProject";
	}
	
	@PostMapping("/createProject")
	public String createProject(@Valid @ModelAttribute("project") Project project, BindingResult result, Model model, HttpSession session) throws SQLException, ClassNotFoundException, IOException {
		if (result.hasErrors()) {
			System.out.println("Not Working!!!");
			return "NewProject";
		}
		String projectName = project.getProjectName();
		Boolean projectExists = true;
		try {
			projectRepository.getProjectByName(projectName);
		}
		catch (NullPointerException e){
			projectExists = false;
		}
		if (projectExists) {
			model.addAttribute("errorMessage", "Project Already Exists");
			return "NewProject";
		}
		String city = project.getCity();
		String country = project.getCountry();
		Date startDate = project.getStartDate();
		Date endDate = project.getEndDate();
		Integer orgID = project.getOrgID();
		List<String> skills = project.getSkills();
		Project p = new Project(projectName, city, country, startDate, endDate, orgID, skills);
		Integer id = projectRepository.registerProjects(p);
		project.setProjectID(id);
		return "redirect:/showProjects"; 
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
	
	@PostMapping("/updateProject")
	public String updateProject(@Valid @ModelAttribute("project") Project project, BindingResult result, Model model, HttpSession session) throws SQLException, ClassNotFoundException, IOException {
		Integer projectID = project.getProjectID();
		String projectName = project.getProjectName();
		String city = project.getCity();
		String country = project.getCountry();
		Date startDate = project.getStartDate();
		Date endDate = project.getEndDate();
		Integer orgID = project.getOrgID();
		List<String> skills = project.getSkills();
		Project p = new Project(projectID, projectName, city, country, startDate, endDate, orgID, skills);
		Boolean projectUpdated = projectRepository.updateProject(p);
		System.out.println("Project Update: " + projectUpdated);
		return "redirect:/showProjects"; 
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
}
