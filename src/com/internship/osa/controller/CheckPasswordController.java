package com.internship.osa.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.internship.osa.dao.UserDetailsDao.validate;

@SuppressWarnings("serial")
public class CheckPasswordController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		try {
			String pass = req.getParameter("password");
			String uID = null;
			HttpSession session = req.getSession();
			try {
				uID = (String) session.getAttribute("uID");
			} catch (Exception e) {
				res.sendRedirect("/loginCheck");
			}
			if (validate(uID, pass)) {
				session.setAttribute("verified", "yes");
				res.sendRedirect("Settings.jsp");
			} else
				res.sendRedirect("UserProfile.jsp");
		} catch (Exception e) {
			PrintWriter out = res.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Oops. Something went wrong!');");
			out.println("window.location = '/loginCheck';");
			out.println("</script>");
		}
	}
}
