package com.internship.osa.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.internship.osa.entity.Comments;
import static com.internship.osa.dao.OfyService.ofy;

public class CommentsDao {
	//Save Comment
	public static void save(String eventID, String comments, String uID,
			String name,String id,Date date) {
		Comments com = new Comments(eventID, comments, uID, name,id,date);
		ofy().save().entity(com).now();
		ofy().clear();
	}

	//Delete Comment
	public static void delete(String id) {
		Comments c = ofy().load().type(Comments.class).id(id).now();
		c.setValid(false);
		ofy().save().entity(c).now();
	}

	//Modify Name in Comment
	public static void modifyName(String uID, String name) {
		Comments com=null;
		List<Comments> det = ofy().load().type(Comments.class).filter("uID", uID).list();
		Iterator<Comments> details = det.iterator();
		while (details.hasNext()) {
			com = details.next();
			com.setName(name);
			ofy().save().entity(com);
		}
	}
}
