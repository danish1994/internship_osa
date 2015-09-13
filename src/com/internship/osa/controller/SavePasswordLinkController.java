package com.internship.osa.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.internship.osa.dao.UserDetailsDao.modifyPassword;
import static com.internship.osa.dao.FrogotUserDao.delete;;

@SuppressWarnings("serial")
public class SavePasswordLinkController extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		String uID = req.getParameter("uID");
		String password = req.getParameter("password");
		System.out.println(uID);
		System.out.println(password);
		//Change Password
		modifyPassword(uID,password);
		//Delete from ForgotUser
		delete(uID);
		res.sendRedirect("/registration.html");
	}
}
