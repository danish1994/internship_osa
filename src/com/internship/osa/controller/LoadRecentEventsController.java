package com.internship.osa.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.internship.osa.entity.Event;

import static com.internship.osa.dao.OfyService.ofy;

@SuppressWarnings("serial")
public class LoadRecentEventsController extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) {
		try {
			JSONArray jArray = new JSONArray();
			List<Event> det = ofy().load().type(Event.class).order("-date")
					.list();
			Iterator<Event> ev = det.iterator();
			int i = 0;
			while (ev.hasNext()&&i<5) {
				Event pc = ev.next();
				JSONObject eventDetails = new JSONObject();
				try {
					eventDetails.put("imgSrc", pc.getEventID());
					eventDetails.put("tag", pc.getTag());
				} catch (Exception e) {
					e.printStackTrace();
				}
				jArray.put(eventDetails);
				i++;
			}
			res.setContentType("application/JSON");
			try {
				res.getWriter().write(jArray.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
