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
		String type =req.getParameter("type");
		boolean valid=true;
		if(type.equals("faculty"))
			valid=false;
		if (save(uID, name, pass, type, valid, "form")) {
			res.sendRedirect("register-complete.html");
		} else
			res.sendRedirect("register-invalid.html");
	}
}
