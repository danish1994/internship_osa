package com.internship.osa.dao;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {
	// Loading Entity Classes
	static {
		try {
			factory().register(com.internship.osa.entity.UserDetails.class);
			factory().register(com.internship.osa.entity.Comments.class);
			factory().register(com.internship.osa.entity.Subscribe.class);
			factory().register(com.internship.osa.entity.ForgotUser.class);
			factory().register(com.internship.osa.entity.Event.class);
			factory().register(com.internship.osa.entity.EventPictures.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Return Objectify
	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	// Register Factory
	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}
}