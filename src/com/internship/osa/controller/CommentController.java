package com.internship.osa.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.internship.osa.dao.CommentsDao.save;

@SuppressWarnings("serial")
public class CommentController extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpSession session = req.getSession();
		String comm = req.getParameter("comment");
		String eventID = req.getParameter("eventID");
		String uID = (String) session.getAttribute("uID");
		String name = (String) session.getAttribute("Name");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// Primary Key
		String id = dateFormat.format(date);
		// Save Comment
		save(eventID, comm, uID, name, id + uID, date);
		System.out.println("Comment Saved");
	}

}
