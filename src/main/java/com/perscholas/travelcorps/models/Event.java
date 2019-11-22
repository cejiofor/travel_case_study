package com.perscholas.travelcorps.models;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

public class Event {
	private int eventId;
	private String title;
	private String description;
	private String location;
	private LocalDateTime dateTime;
	private int memberId;
	private List<Member> eventAttendees;
	
	public Event() {
		super();
	}
	
	public Event(String title, String description, String location, LocalDateTime dateTime, int memberId) {
		super();
		this.title = title;
		this.description = description;
		this.location = location;
		this.dateTime = dateTime;
		this.memberId = memberId;
	}

	
	public Event(int eventId, String title, String description, String location, LocalDateTime dateTime, int memberId) {
		super();
		this.eventId = eventId;
		this.title = title;
		this.description = description;
		this.location = location;
		this.dateTime = dateTime;
		this.memberId = memberId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public List<Member> getEventAttendees() {
		return eventAttendees;
	}

	public void setEventAttendees(List<Member> eventAttendees) {
		this.eventAttendees = eventAttendees;
	}
	
	/******Methods to check for ID in eventAttenders******/
	// Loop method
	public Boolean attendersContainsIdLoop(Integer id) {
		for (Member m : this.eventAttendees) {
			if (m.getMemberId() == id) {
				return true;
			}
		}
		return false;
	}
	// Iterator method
	public Boolean attendersContainsIdIterator(Integer id) {
		Iterator<Member> attenders = this.eventAttendees.iterator();
		while(attenders.hasNext()) {
			Member m = attenders.next();
			if (m.getMemberId() == id) {
				return true;
			}
		}
		return false;
	}
	// Stream method
	public Boolean attendersContainsIdStream(Integer id) {
		Member attender = this.eventAttendees.stream().filter(member -> id == 
				member.getMemberId()).findAny().orElse(null);
		if (attender != null) {
			return true;
		}
		return false;
	}
	
//	public static void main(String[] args) {
//		Event ev = new Event();
//		Member a1 = new Member();
//		a1.setMemberId(1);
//		Member a2 = new Member();
//		a2.setMemberId(2);
//		Member a3 = new Member();
//		a3.setMemberId(3);
//		
//		Member currentUser = new Member();
//		currentUser.setMemberId(3);
//		
//		ev.getEventAttenders().add(a1);
//		ev.getEventAttenders().add(a2);
//		ev.getEventAttenders().add(a3);
//		System.out.println(ev.attendersContainsIdLoop(currentUser.getMemberId()));
//	}
	
}
