package com.internship.osa.controller;

import java.io.IOException;

import javax.servlet.http.*;

import static com.internship.osa.dao.UserDetailsDao.modify;

@SuppressWarnings("serial")
public class UserAcceptController extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		String uID=req.getParameter("uID");
		modify(uID,true);
		res.sendRedirect("AccountValid.jsp");
	}
}
