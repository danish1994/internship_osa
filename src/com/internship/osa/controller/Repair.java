package com.internship.osa.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.internship.osa.entity.Subscribe;
import com.internship.osa.entity.Event;

import static com.internship.osa.dao.OfyService.ofy;

@SuppressWarnings("serial")
public class Repair extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) {
		List<Event> ls = ofy().load().type(Event.class).list();
		Iterator <Event> it = ls.iterator();
		while(it.hasNext())
		{
			Event pd = it.next();
			String eventID = pd.getEventID();
			int subCount = ofy().load().type(Subscribe.class).filter("eventID",eventID).count();
			pd.setSubCount(subCount);
			ofy().save().entity(pd);
		}
	}
}
