package com.perscholas.travelcorps.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.perscholas.travelcorps.models.OrganizationUser;
import com.perscholas.travelcorps.models.User;
import com.perscholas.travelcorps.repositories.ProjectRepository;

@Controller
@RequestMapping("/project") //class level map
@SessionAttributes
public class ProjectController {
	private ProjectRepository projectRepository;
	
	@GetMapping("/showAllProjects")
	public String showAllProjects(Model model) throws SQLException {
		List<Project> projectList = projectRepository.getAllProjects();
		model.addAttribute("eventList", eventList);
		return "EventsPage";
	}
	
	@PostMapping("/createProject")
	public String createProject(@Valid @ModelAttribute("orgUser") OrganizationUser orgUser, HttpSession session, Model model) throws SQLException {
		
		LocalDateTime ldt = LocalDateTime.parse(dateTime);
		Member currentMember = (Member) session.getAttribute("currentMember");
				
		Event event = new Event(title, description, location, ldt, currentMember.getMemberId());
		
		EventDAO edao = new EventDAO();
		edao.createEvent(event);
		return "redirect:/events/showEvents";
		
	}
	

}
