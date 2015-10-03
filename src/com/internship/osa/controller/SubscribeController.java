package com.internship.osa.controller;

import static com.internship.osa.dao.EventDao.SubscribeCount;
import static com.internship.osa.dao.EventDao.SubscribeMinus;
import static com.internship.osa.dao.OfyService.ofy;
import static com.internship.osa.dao.SubscribeDao.addSubscribe;
import static com.internship.osa.dao.SubscribeDao.removeSubscribe;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.internship.osa.entity.Subscribe;

@SuppressWarnings("serial")
public class SubscribeController extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String SubscribeStatus = "no";
		String eventID = req.getParameter("eventID");
		String uID = null;

		HttpSession sess = req.getSession();
		try {
			uID = (String) sess.getAttribute("uID");
			List<Subscribe> l = ofy().load().type(Subscribe.class)
					.filter("eventID", eventID).filter("uID", uID).list();
			Iterator<Subscribe> lIt = l.iterator();
			int count = ofy().load().type(Subscribe.class)
					.filter("eventID", eventID).count();
			if (lIt.hasNext())
				SubscribeStatus = "yes";
			else
				SubscribeStatus = "no";
			JSONArray jArray = new JSONArray();
			JSONObject json = new JSONObject();
			if (uID.equals(null)) {
				System.out.println("uID is NULL");
				try {
					json.put("SubscribeType", "none");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				if (SubscribeStatus.equals("no")) {
					Date date = new Date();
					addSubscribe(uID, eventID, date.toString() + uID);
					SubscribeCount(eventID, count+1);
					try {
						json.put("SubscribeType", "Unsubscribe");
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else if (SubscribeStatus.equals("yes")) {
					removeSubscribe(eventID, uID);
					SubscribeMinus(eventID, count-1);
					try {
						json.put("SubscribeType", "Subscribe");
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
			jArray.put(json);
			res.getWriter().write(jArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}