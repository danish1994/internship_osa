package com.internship.osa.dao;

import static com.internship.osa.dao.OfyService.ofy;

import java.util.Date;

import com.internship.osa.entity.Event;

public class EventDao {

	public static void save(String eventID, String place, String tag, String description,
			Date eventDate, String uID, int subCount, Date date) {
		Event eve = new Event(eventID,place,tag,description,eventDate,uID,subCount,date);
		ofy().save().entity(eve).now();
		ofy().clear();
	}
	
	public static void update(String eventID, String place, String tag, String description,
			Date eventDate, String uID, int subCount, Date date) {
		Event eve = ofy().load().type(Event.class).id(eventID).now();
		eve.setDate(date);
		eve.setDescription(description);
		eve.setEventDate(eventDate);
		eve.setEventID(eventID);
		eve.setPlace(place);
		eve.setTag(tag);
		ofy().save().entity(eve).now();
		ofy().clear();
	}
	
	public static void SubscribeCount(String eventID) {
		Event pd=ofy().load().type(Event.class).id(eventID).now();
		pd.setSubCount(ofy().load().type(Event.class).filter("event",pd.getEventID()).count());
		ofy().save().entity(pd).now();
		System.out.println("Subscribe Count + 1");
	}

	public static void SubscribeMinus(String eventID) {
		Event pd=ofy().load().type(Event.class).id(eventID).now();
		pd.setSubCount(ofy().load().type(Event.class).filter("event",pd.getEventID()).count());
		ofy().save().entity(pd).now();
				System.out.println("Subscribe Count - 1");
	}
}
