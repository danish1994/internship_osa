package com.internship.osa.dao;

import java.util.Date;

import com.internship.osa.entity.ForgotUser;
import static com.internship.osa.dao.OfyService.ofy;

public class FrogotUserDao {
	public static void save(String uID, String randomKey, Date date) {
		ForgotUser fu = new ForgotUser(uID, randomKey, date);
		ofy().save().entity(fu).now();
		System.out.println("Saved in Forgot User");
	}
	public static void delete(String uID) {
		ForgotUser fu = ofy().load().type(ForgotUser.class).id(uID).now();
		ofy().delete().entity(fu);
		System.out.println("Delete in Forgot User");
	}
}
