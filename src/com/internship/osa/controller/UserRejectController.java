package com.internship.osa.controller;

import java.io.IOException;

import javax.servlet.http.*;

import static com.internship.osa.dao.UserDetailsDao.delete;

@SuppressWarnings("serial")
public class UserRejectController extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		HttpSession session = req.getSession(false);
		String type = null;
		String uID=req.getParameter("uID");
		try{
			type = (String) session.getAttribute("Type");
		}catch(Exception e)
		{
			res.sendRedirect("/loginCheck");
		}
		if(type.equals("admin"))
			delete(uID);
		else
			res.sendRedirect("/loginCheck");
		res.sendRedirect("AccountValid.jsp");
	}
}
