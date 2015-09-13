package com.internship.osa.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.internship.osa.entity.ForgotUser;

import static com.internship.osa.dao.OfyService.ofy;

@SuppressWarnings("serial")
public class DeleteForgotUserController extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) {
		List<ForgotUser> list = ofy().load().type(ForgotUser.class).list();
		Iterator<ForgotUser> user = list.iterator();
		// Deleting All Links Generated Before 30 Mins.
		while (user.hasNext()) {
			ForgotUser fu = user.next();
			Date current = new Date();
			Date saved = fu.getDate();
			long diff = current.getTime() - saved.getTime();
			long timeDifference = diff / (60 * 1000) % 60;
			System.out.println(timeDifference);
			if (timeDifference >= 30)
				ofy().delete().entity(fu);
		}
	}
}
