package com.internship.osa.controller;

import java.io.IOException;

import javax.servlet.http.*;

import static com.internship.osa.dao.UserDetailsDao.save;

@SuppressWarnings("serial")
public class AddAdmin extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		save("admin@admin.com","admin","admin","admin",true,"form");
	}
}
