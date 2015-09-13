package com.internship.osa.controller;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

@SuppressWarnings("serial")
public class LoginCheckController extends HttpServlet {
	private HttpSession session;

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		session = req.getSession(true);
		if (!session.isNew()) {
			if (session.getAttribute("Type")==(null))
				res.sendRedirect("registration.html");
			else {
				String type = (String) session.getAttribute("Type");
				if (type.equals("student")||type.equals("faculty"))
					res.sendRedirect("UserProfile.jsp");
				else if(type.equals("admin"))
					res.sendRedirect("AccountValid.jsp");
			}
		} else {
			res.sendRedirect("registration.html");
		}
	}
}
