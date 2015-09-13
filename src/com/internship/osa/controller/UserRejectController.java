package com.internship.osa.controller;

import java.io.IOException;

import javax.servlet.http.*;

import static com.internship.osa.dao.UserDetailsDao.delete;

@SuppressWarnings("serial")
public class UserRejectController extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		String uID=req.getParameter("uID");
		delete(uID);
		res.sendRedirect("AccountValid.jsp");
	}
}
