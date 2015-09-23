package com.internship.osa.dao;

import java.util.Iterator;
import java.util.List;

import com.internship.osa.entity.UserDetails;

import static com.internship.osa.dao.OfyService.ofy;

public class UserDetailsDao {
	static UserDetails ud = null;

	// Register
	// Add a User
	public static boolean save(String uID, String name, String pass,
			String type, Boolean status, String source) {
		if (check(uID)) {
			UserDetails user = new UserDetails(uID, name, pass, type, status,
					source);
			ofy().save().entity(user);
			ofy().clear();
			System.out.println("Data Saved");
			return true;
		} else
			return false;
	}

	// Check Existing User
	public static boolean check(String uID) {
		List<UserDetails> det = ofy().load().type(UserDetails.class)
				.filter("uID", uID).list();
		Iterator<UserDetails> details = det.iterator();
		if (details.hasNext())
			return false;
		else
			return true;
	}

	// Login
	// Validate Details
	public static boolean validate(String uID, String pass) {
		boolean res = true;
		List<UserDetails> det = ofy().load().type(UserDetails.class).list();
		Iterator<UserDetails> details = det.iterator();
		res = details.hasNext();
		while (details.hasNext()) {
			ud = details.next();
			if (ud.getuID().equals(uID)
					&& ud.getPass().equals(pass)
					&& (ud.getStatus().equals(true) || ud.getType().equals(
							"student"))) {
				res = true;
				break;
			} else
				res = false;
		}
		return res;
	}

	// Modify Name
	public static void modifyDetails(String uID, String name, String pass) {
		UserDetails ud = ofy().load().type(UserDetails.class).id(uID).now();
		ud.setName(name);
		ud.setPass(pass);
		ofy().save().entity(ud).now();
		ofy().clear();
		System.out.println("Details Changed");
	}

	// Modify Password
	public static void modifyPassword(String uID, String pass) {
		UserDetails ud = ofy().load().type(UserDetails.class).id(uID).now();
		ud.setPass(pass);
		ud.setSource("form");
		ofy().save().entity(ud).now();
		ofy().clear();
		System.out.println("Password Changed");
	}

	// Modify Type
	public static void modifyType(String uID, String type, boolean valid) {
		UserDetails ud = ofy().load().type(UserDetails.class).id(uID).now();
		ud.setType(type);
		ud.setStatus(valid);
		ofy().save().entity(ud).now();
		ofy().clear();
		System.out.println("Type Changed");
	}

	// Return Name
	public static String getName() {
		return ud.getName();
	}

	// Return User Type
	public static String getType() {
		return ud.getType();
	}

	// Return Claim Name
	public static String getSavedName(String uID) {
		UserDetails ud = ofy().load().type(UserDetails.class).id(uID).now();
		return ud.getName();
	}

	// Change Status
	public static void modify(String uID, boolean valid) {
		UserDetails ud = ofy().load().type(UserDetails.class).id(uID).now();
		ud.setStatus(valid);
		ud.setType("faculty");
		ofy().save().entity(ud).now();
	}

	// Delete
	public static void delete(String uID) {
		UserDetails ud = ofy().load().type(UserDetails.class).id(uID).now();
		ofy().delete().entity(ud).now();
	}

	// Return Type
	public static String returnType(String uID) {
		UserDetails ud = ofy().load().type(UserDetails.class).id(uID).now();
		return ud.getType();
	}

	// Return Status
	public static boolean returnValid(String uID) {
		UserDetails ud = ofy().load().type(UserDetails.class).id(uID).now();
		return ud.getStatus();
	}
}