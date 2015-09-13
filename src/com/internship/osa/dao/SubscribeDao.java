package com.internship.osa.dao;

import java.util.Iterator;
import java.util.List;

import com.internship.osa.entity.Subscribe;

import static com.internship.osa.dao.OfyService.ofy;;

public class SubscribeDao {
	//Add Like
	public static void addSubscribe(String eventID, String uID, String id) {
		Subscribe l = new Subscribe(eventID,uID,id);
		ofy().save().entity(l).now();
	}
	
	//Delete Like
	public static void removeSubscribe(String eventID, String uID) {
		List<Subscribe> com = ofy().load().type(Subscribe.class).filter("eventID", eventID).filter("uID", uID).list();
		Iterator<Subscribe> c = com.iterator();
		while(c.hasNext())
		{
			Subscribe cd = c.next();
			ofy().delete().entity(cd).now();
		}
	}
}
