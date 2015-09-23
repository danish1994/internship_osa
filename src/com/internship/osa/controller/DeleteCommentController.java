package com.internship.osa.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.internship.osa.entity.Comments;

import static com.internship.osa.dao.OfyService.ofy;
import static com.internship.osa.dao.CommentsDao.delete;

@SuppressWarnings("serial")
public class DeleteCommentController extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String id = req.getParameter("id");
		Comments c = ofy().load().type(Comments.class).id(id).now();
		String uID = null;
		HttpSession session = req.getSession();
		try{
			uID = (String) session.getAttribute("uID");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		if(c.getuID().equals(uID))
			delete(id);
		System.out.println("Comment Deleted");
	}

}
