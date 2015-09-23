package com.internship.osa.controller;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

import static com.internship.osa.dao.UserDetailsDao.save;

@SuppressWarnings("serial")
public class RegisterController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		String name = req.getParameter("name");
		String uID = req.getParameter("email");
		String pass = req.getParameter("password");
		String type = req.getParameter("type");
		PrintWriter out = res.getWriter();
		boolean valid = true;
		if (type.equals("faculty"))
			valid = false;
		if (save(uID, name, pass, "student", valid, "form")) {
			out.println("<script type=\"text/javascript\">");
			if (type.equals("faculty"))
				out.println("alert('Please wait until the admin accept your details.');");
			else
				out.println("alert('Please Login to Continue');");
			out.println("window.location = '/loginCheck';");
			out.println("</script>");
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Email ID Already Exists. Please Try Again');");
			out.println("window.location = '/loginCheck';");
			out.println("</script>");
		}
	}
}
