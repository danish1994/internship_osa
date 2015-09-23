package com.internship.osa.controller;

import java.io.IOException;

import javax.servlet.http.*;

import static com.internship.osa.dao.UserDetailsDao.modify;

@SuppressWarnings("serial")
public class UserAcceptController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpSession session = req.getSession(false);
		String type = null;
		String uID = req.getParameter("uID");
		try {
			type = (String) session.getAttribute("Type");
		} catch (Exception e) {
			res.sendRedirect("/loginCheck");
		}
		if (type.equals("admin"))
			modify(uID, true);
		else
			res.sendRedirect("/loginCheck");
		res.sendRedirect("AccountValid.jsp");
	}
}
