package com.perscholas.travelcorps.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.perscholas.travelcorps.models.Project;
import com.perscholas.travelcorps.models.Volunteer;
import com.perscholas.travelcorps.repositories.ProjectRepository;
import com.perscholas.travelcorps.repositories.SignUpRepository;
import com.perscholas.travelcorps.repositories.VolunteerRepository;

@Controller
//@RequestMapping("/project") //class level map
@SessionAttributes
public class ProjectController {
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired 
	private VolunteerRepository volunteerRepository;
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
		
		Map<Integer, List<Integer>> volunteerMap = new HashMap<Integer, List<Integer>>();
		for(Project proj: projectList) {
			List<Integer> volunteerIDList = new ArrayList<Integer>();
			try {
				volunteerIDList = signUpRepository.getProjectVolunteers(proj.getProjectID());
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.println("VolIds: "+volunteerIDList.toString());
			volunteerMap.put(proj.getProjectID(), volunteerIDList);
		}
		System.out.println(volunteerMap.toString());
		model.addAttribute("volunteerMap", volunteerMap);
		
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
		Boolean projectExists = null;
		try {
			projectRepository.getProjectByName(projectName);
			projectExists = true;
		}
		catch (NullPointerException e){
			projectExists = false;
		}
//		if (projectExists) {
//			model.addAttribute("errorMessage", "Project Already Exists");
//			return "NewProject";
//		}
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
	
	@GetMapping("/showProject/{projectID}")
	public String showProject(Model model, @PathVariable("projectID") int projectID) throws ClassNotFoundException, SQLException, IOException{
		Project project = projectRepository.getProjectById(projectID);
		model.addAttribute("project", project);
		
		
		List<Integer> volunteerIDList = new ArrayList<Integer>();
		try {
			volunteerIDList = signUpRepository.getProjectVolunteers(projectID);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
//		List<Volunteer> volunteerList = new ArrayList<Volunteer>();
//		try {
//			volunteerList = volunteerRepository.getAllVolunteers();
//		}
//		catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		model.addAttribute("volunteerList", volunteerList);
		List<Volunteer> volunteerList = new ArrayList<Volunteer>();
		for(Integer i: volunteerIDList) {
			Volunteer volunteer = null;
			try {
				volunteer = volunteerRepository.getVolunteerById(i);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			volunteerList.add(volunteer);
		}
		model.addAttribute("volunteerList", volunteerList);
		
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
