package com.internship.osa.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.internship.osa.entity.Comments;
import com.internship.osa.entity.Event;

import static com.internship.osa.dao.OfyService.ofy;

@SuppressWarnings("serial")
public class LoadEventsController extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) {
		try {
			String type = req.getParameter("type");
			JSONArray jArray = new JSONArray();
			DateFormat timeFormat = new SimpleDateFormat("dd/MM/YYYY");
			if (type.equals("popular")) {
				List<Event> det = ofy().load().type(Event.class)
						.order("-subCount").list();
				Iterator<Event> ev = det.iterator();
				while (ev.hasNext()) {
					Event pc = ev.next();
					JSONObject eventDetails = new JSONObject();
					String eventDate = timeFormat.format(pc.getEventDate());
					String desc = pc.getDescription();
					try {
						eventDetails.put("imgSrc", pc.getEventID());
						eventDetails.put("tag", pc.getTag());
						eventDetails.put("date", eventDate);
						eventDetails.put(
								"commentCount",
								ofy().load().type(Comments.class)
										.filter("eventID", pc.getEventID())
										.filter("valid", true).count());
						eventDetails.put("place", pc.getPlace());
						eventDetails.put("name", pc.getuID());
						eventDetails.put("desc", desc);
					} catch (Exception e) {
						e.printStackTrace();
					}
					jArray.put(eventDetails);
				}
				res.setContentType("application/JSON");
				try {
					// jArray.write(res.getWriter());
					res.getWriter().write(jArray.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				List<Event> det = ofy().load().type(Event.class).order("-date")
						.list();
				Iterator<Event> ev = det.iterator();
				while (ev.hasNext()) {
					Event pc = ev.next();
					JSONObject eventDetails = new JSONObject();
					String todayDate = timeFormat.format(new Date());
					String eventDate = null;
					try {
						eventDate = timeFormat.format(pc.getEventDate());
					} catch (Exception e) {
						e.printStackTrace();
					}
					String desc = pc.getDescription();
					if (type.equals("today")) {
						if (todayDate.equals(eventDate)) {
							try {
								eventDetails.put("imgSrc", pc.getEventID());
								eventDetails.put("tag", pc.getTag());
								eventDetails.put("date", eventDate);
								eventDetails.put(
										"commentCount",
										ofy().load()
												.type(Comments.class)
												.filter("eventID",
														pc.getEventID())
												.filter("valid", true).count());
								eventDetails.put("place", pc.getPlace());
								eventDetails.put("name", pc.getuID());
								eventDetails.put("desc", desc);
							} catch (Exception e) {
								e.printStackTrace();
							}
							jArray.put(eventDetails);
						}
					} else {
						try {
							eventDetails.put("imgSrc", pc.getEventID());
							eventDetails.put("tag", pc.getTag());
							eventDetails.put("date", eventDate);
							eventDetails.put(
									"commentCount",
									ofy().load().type(Comments.class)
											.filter("eventID", pc.getEventID())
											.filter("valid", true).count());
							eventDetails.put("place", pc.getPlace());
							eventDetails.put("name", pc.getuID());
							eventDetails.put("desc", desc);
						} catch (Exception e) {
							e.printStackTrace();
						}
						jArray.put(eventDetails);
					}
				}
				res.setContentType("application/JSON");
				try {
					// jArray.write(res.getWriter());
					res.getWriter().write(jArray.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
