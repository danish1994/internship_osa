package com.internship.osa.controller;

import static com.internship.osa.dao.OfyService.ofy;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.internship.osa.entity.Event;
import com.internship.osa.entity.EventPictures;
import com.internship.osa.entity.Subscribe;

@SuppressWarnings("serial")
public class Repair extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) {
		List<Event> ls = ofy().load().type(Event.class).list();
		Iterator<Event> it = ls.iterator();
		while (it.hasNext()) {
			Event pd = it.next();
			String eventID = pd.getEventID();
			int subCount = ofy().load().type(Subscribe.class)
					.filter("eventID", eventID).count();
			pd.setSubCount(subCount);
			ofy().save().entity(pd);
		}
		List<EventPictures> l = ofy().load().type(EventPictures.class).list();
		Iterator<EventPictures> i = l.iterator();
		while (i.hasNext()) {
			EventPictures ep = i.next();
			String eventID = ep.getEventID();
			Event e = ofy().load().type(Event.class).id(eventID).now();
			if (e == null) {
				ofy().delete().entity(ep);
			}
		}
	}
}
