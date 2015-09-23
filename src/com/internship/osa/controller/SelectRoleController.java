package com.internship.osa.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.internship.osa.dao.UserDetailsDao.modifyType;

@SuppressWarnings("serial")
public class SelectRoleController extends HttpServlet {
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		try{
			HttpSession sess = req.getSession();
			String uID = (String) sess.getAttribute("uID");
			String type = req.getParameter("type");
			if(type.equals("faculty")){
				modifyType(uID,type,false);
				PrintWriter out = res.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Please wait until the admin accept your details.');");
				out.println("window.location = '/loginCheck';");
				out.println("</script>");
			}
			else
			{
				res.sendRedirect("/loginCheck");
			}
		}catch(Exception e){
			res.sendRedirect("/loginCheck");
		}
	}
}
