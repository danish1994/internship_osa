package com.internship.osa.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import static com.internship.osa.dao.UserDetailsDao.validate;
import static com.internship.osa.dao.UserDetailsDao.getName;
import static com.internship.osa.dao.UserDetailsDao.getType;

@SuppressWarnings("serial")
public class LoginController extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		String uID=req.getParameter("email");
		String pass=req.getParameter("password");
		if(validate(uID,pass))
		{
			System.out.println("Valid");
			HttpSession sess=req.getSession();
			String type=getType();
			sess.setAttribute("Name",getName());
			sess.setAttribute("Type",type);
			sess.setAttribute("uID",uID);
			res.sendRedirect("/loginCheck");
		}
		else
			res.sendRedirect("/login-invalid.html");
	}
}
