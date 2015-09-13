package com.internship.osa.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.internship.osa.dao.UserDetailsDao.validate;

@SuppressWarnings("serial")
public class CheckPasswordController extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		String pass = req.getParameter("password");
		String uID = req.getParameter("uID");
		HttpSession session = req.getSession();
		if(validate(uID,pass))
		{
			session.setAttribute("verified", "yes");
			res.sendRedirect("Settings.jsp");
		}
		else
			res.sendRedirect("UserProfile.jsp");
	}
}
