package com.perscholas.travelcorps.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.perscholas.travelcorps.dao.EventDAO;
import com.perscholas.travelcorps.models.Event;
import com.perscholas.travelcorps.models.Member;

@Controller
@RequestMapping("/events") //class level map
public class EventController {

	@GetMapping("/showEvents")
	public String showEvents(Model model) throws SQLException {
		EventDAO edao = new EventDAO();
		List<Event> eventList = edao.getEvents();
		model.addAttribute("eventList", eventList);
		return "EventsPage";
	}
	
	@PostMapping("/createEvent")
	public String createEvent(@RequestParam String title, 
			@RequestParam String description, 
			@RequestParam String location, 
			@RequestParam String dateTime, 
			HttpSession session,
			Model model) throws SQLException {
		
		LocalDateTime ldt = LocalDateTime.parse(dateTime);
		Member currentMember = (Member) session.getAttribute("currentMember");
				
		Event event = new Event(title, description, location, ldt, currentMember.getMemberId());
		
		EventDAO edao = new EventDAO();
		edao.createEvent(event);
		return "redirect:/events/showEvents";
		
	}
	

}
