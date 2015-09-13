package com.internship.osa.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.internship.osa.entity.Comments;
import com.internship.osa.entity.Event;
import com.internship.osa.entity.Subscribe;

import static com.internship.osa.dao.OfyService.ofy;

@SuppressWarnings("serial")
public class LoadCommentController extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		String eventID = req.getParameter("eventID");
		JSONArray jArray = new JSONArray();
		Event pc = ofy().load().type(Event.class).id(eventID).now();
		JSONObject count = new JSONObject();
		int subCount = ofy().load().type(Subscribe.class).filter("eventID", eventID).count();
		pc.setSubCount(subCount);
		ofy().save().entity(pc).now();
		try {
			count.put("subCount",subCount);
			count.put("comment", ofy().load().type(Comments.class).filter("eventID",eventID).filter("valid", true).count());
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		jArray.put(count);
		List<Comments> ls = ofy().load().type(Comments.class).filter("eventID",eventID).filter("valid",true).order("-date").list();
		Iterator<Comments> it = ls.iterator();
		while (it.hasNext()) {
			Comments c = it.next();
			JSONObject comment = new JSONObject();
			try {
				comment.put("comment", c.getComments());
				comment.put("name", c.getName());
				comment.put("uID", c.getuID());
				comment.put("id",c.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			jArray.put(comment);
		}
		res.setContentType("application/JSON");
		try {
			//jArray.write(res.getWriter());
			res.getWriter().write(jArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
