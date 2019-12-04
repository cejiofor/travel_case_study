//package com.perscholas.travelcorps.servlets;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.perscholas.travelcorps.dao.EventDAO;
//import com.perscholas.travelcorps.dao.MemberDAO;
//import com.perscholas.travelcorps.dao.SignUpDAO;
//import com.perscholas.travelcorps.models.Event;
//import com.perscholas.travelcorps.models.Member;
//
///**
// * Servlet implementation class HomeServlet
// * 
// * SESSISON -> will invalidate the session/clear it, send user to login page to staet the processs all over again
// */
//@WebServlet({"/HomeServlet", "/HomeServlet/*"})
//public class HomeServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public HomeServlet() {
//        super();
//    }
//
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String servletPath = request.getServletPath();
//		String pathInfo = request.getPathInfo();
//		String action = null;
//		
//		
//		/* If there are no characters after the servlet path (pathInfo will be 
//		 * null) or if the servlet path is followed by a single "/" then the action 
//		 * string will be assigned only the servlet path. If there is a slash 
//		 * followed by any characters, then the action string will be assigned the 
//		 * servlet path plus the additional path information. */
//		if (pathInfo == null || pathInfo.equals("/")){
//			action = servletPath;
//		} else {
//			action = servletPath + pathInfo;
//		}
//
//
//		try {
//			// FOr less swtich statment, can make different servlets, have the programs directed to other servlets taht have alternative webservlet annotations
//			switch(action){
//				case "/HomeServlet":
//					System.out.println("test");
//					showLogin(request, response);
//					break;
//				case "/HomeServlet/showLogin":
//					showLogin(request, response);
//					break;
//				case "/HomeServlet/loginMember":
//					loginMember(request, response);
//					break;
//				case "/HomeServlet/showRegistration":
//					showRegistration(request, response);
//					break;
//				case "/HomeServlet/registerMember":
//					registerMember(request, response);
//					break;				
//				case "/HomeServlet/showWelcome":
//					showWelcome(request, response);
//					break;
//				case "/HomeServlet/showProfile":
//					showProfile(request, response);
//					break;
//				case "/HomeServlet/showUpdatePage":
//					showUpdatePage(request, response);
//					break;
//				case "/HomeServlet/updateMember":
//					showUpdatePage(request, response);
//					break;
//				case "/HomeServlet/showEvents":
//					showEvents(request, response);
//					break;				
//				case "/HomeServlet/createEvent":
//					createEvent(request, response);
//					break;
//				case "/HomeServlet/signUpForEvent":
//					signUpForEvent(request, response);
//					break;
//				case "/HomeServlet/cancelSignup":
//					cancelSignUp(request, response);
//					break;
//				case "/HomeServlet/deleteEvent":
//					deleteEvent(request, response);
//					break;
//				case "/HomeServlet/logout":
//					logout(request, response);
//					break;
//				default:
//					showLogin(request, response);
//					System.out.println("default");
//					break;
//				}
//		}
//		catch(Exception e) {
//			System.out.println("Error: "+e.getMessage());
//		}
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
//	
//	private void showLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/LoginPage.jsp");
//		rd.forward(request, response);	
//	}
//	
//	private void loginMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//		String name = request.getParameter("name");
//		String password = request.getParameter("password");		
//		System.out.println(name + ": "+ password);
//		
//		HttpSession session = request.getSession();
//		
//		MemberDAO mdao = new MemberDAO();
//		Member m = mdao.getMember(name);
//		
//		if (m!= null) {
//			if (password.equals(m.getPassword())) {
//				session.setAttribute("currentMember", m);
//				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/WelcomePage.jsp");
//				rd.forward(request, response);
//				
//			} 
//			else {
//				request.setAttribute("errorMessage", "Invalid Login");
//				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/LoginPage.jsp");
//				rd.forward(request, response);	
//			}
//		}
//		else {
//			request.setAttribute("errorMessage", "Invalid Login");
//			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/LoginPage.jsp");
//			rd.forward(request, response);
//		}
//	}
//	
//	private void showRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/RegistrationPage.jsp");
//		rd.forward(request, response);
//	}
//	
//	private void registerMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//		String name = request.getParameter("name");
//		String email = request.getParameter("email");	
//		String password = request.getParameter("password");	
//		String favoriteLanguage = request.getParameter("favoriteLanguage");	
//		MemberDAO mdao = new MemberDAO();
//		Member m = new Member(name, email, password, favoriteLanguage);
//		Integer i = mdao.createMember(m);
//		m.setMemberId(i);
//		System.out.println(i);
//		
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/LoginPage.jsp");
//		rd.forward(request, response);
//	}
//	
//	private void showWelcome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException 	{
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/WelcomePage.jsp");
//		rd.forward(request, response);
//	}
//	
//	private void showProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException 	{
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ProfilePage.jsp");
//		rd.forward(request, response);
//	}
//	
//	private void showUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/UpdatePage.jsp");
//		rd.forward(request, response);
//	}
//	
//	private void updateMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//		Integer memberId = Integer.parseInt(request.getParameter("memberId"));
//		String name = request.getParameter("name");
//		String email = request.getParameter("email");	
//		String password = request.getParameter("password");	
//		String favoriteLanguage = request.getParameter("favoriteLanguage");	
//		
//		MemberDAO mdao = new MemberDAO();
//		Member m = new Member(memberId, name, email, password, favoriteLanguage);
//		
//		Boolean updated = mdao.updateMember(m);
//		System.out.println(updated);
//		
//		RequestDispatcher rd = request.getRequestDispatcher("/HomeServlet/showProfile");
//		rd.forward(request, response);
//	}
//	
//	private void showEvents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//		EventDAO edao = new EventDAO();
//		List<Event> eventList = edao.getEvents();
//		request.setAttribute("eventList", eventList);
//		
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/EventsPage.jsp");
//		rd.forward(request, response);
//	}
//	
//	private void createEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//		HttpSession session = request.getSession();
//		
//		String title = request.getParameter("title");
//		String description = request.getParameter("description");
//		String location = request.getParameter("location");
//		String dateTimeString = request.getParameter("dateTime");
//		LocalDateTime ldt = LocalDateTime.parse(dateTimeString);
//		Member currentMember = (Member) session.getAttribute("currentMember");
//				
//		Event event = new Event(title, description, location, ldt, currentMember.getMemberId());
//		
//		EventDAO edao = new EventDAO();
//		edao.createEvent(event);
//		
//		RequestDispatcher rd = request.getRequestDispatcher("/HomeServlet/showEvents");
//		rd.forward(request, response);
//	}
//	
//	private void cancelSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//		Integer memberId = Integer.parseInt(request.getParameter("memberId"));
//		Integer eventId = Integer.parseInt(request.getParameter("eventId"));
//		System.out.println(memberId + ", " + eventId);
//		
//		SignUpDAO s_dao = new SignUpDAO();
//		s_dao.cancelSignup(memberId, eventId);
//		
//		RequestDispatcher rd = request.getRequestDispatcher("/HomeServlet/showEvents");
//		rd.forward(request, response);
//		
//	}
//	
//	private void signUpForEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
//		HttpSession session = request.getSession();
//		Integer memberId = Integer.parseInt(request.getParameter("memberId"));
//		Integer eventId = Integer.parseInt(request.getParameter("eventId"));
//		
//		SignUpDAO sdao = new SignUpDAO();
//		sdao.signupForEvent(memberId, eventId);
//		
//		RequestDispatcher rd = request.getRequestDispatcher("/HomeServlet/showEvents");
//		rd.forward(request, response);
//	}
//	
//	private void deleteEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//		Integer eventId = Integer.parseInt(request.getParameter("eventId"));
//		EventDAO e_dao = new EventDAO();
//		e_dao.removeEvent(eventId);
//		RequestDispatcher rd = request.getRequestDispatcher("/HomeServlet/showEvents");
//		rd.forward(request, response);
//	}
//	
//	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		session.invalidate();
//		RequestDispatcher rd = request.getRequestDispatcher("/HomeServlet");
//		rd.forward(request, response);
//	}
//	
////}private void someMETHOD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////	HttpSession session = request.getSession();
////}
//	
//	
//}
