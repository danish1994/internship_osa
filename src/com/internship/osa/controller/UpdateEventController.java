package com.internship.osa.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.internship.osa.entity.Event;

import static com.internship.osa.dao.EventDao.update;
import static com.internship.osa.dao.OfyService.ofy;

@SuppressWarnings("serial")
public class UpdateEventController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		HttpSession session = req.getSession(false);
		String tag = req.getParameter("Tag");
		String place = req.getParameter("Place");
		String eventID = req.getParameter("eventID");
		String uID = null;
		String getDate = req.getParameter("eventDate");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date eventDate = null;
		try {
			eventDate = formatter.parse(getDate);
			System.out.println(eventDate);
			System.out.println(formatter.format(eventDate));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		String description = req.getParameter("Description");
		try {
			uID = (String) session.getAttribute("uID");
		} catch (Exception e) {
			res.sendRedirect("login.html");
		}
		Event ev = ofy().load().type(Event.class).id(eventID).now();
		if (ev.getuID().equals(uID)) {
			Date date = new Date();
			update(eventID, place, tag, description, eventDate, uID, 0, date);
			System.out.println("Saved");
			// Save Picture
			res.sendRedirect("/AddImage.jsp?eventID=" + eventID);
		}
	}
}
