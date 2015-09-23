package com.internship.osa.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import com.internship.osa.dao.UserDetailsDao;
import com.internship.osa.dao.CommentsDao;

@SuppressWarnings("serial")
public class ChangeSettingController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		HttpSession session = req.getSession(false);
		String uID = null;
		try{
			uID = (String) session.getAttribute("uID");
		}catch(Exception e)
		{
			res.sendRedirect("/loginCheck");
		}
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		session.setAttribute("Name",name);
		//Change Details
		UserDetailsDao.modifyDetails(uID, name,pass);
		CommentsDao.modifyName(uID,name);
		res.sendRedirect("/loginCheck");
	}
}
