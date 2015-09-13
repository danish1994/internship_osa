package com.internship.osa.dao;

import com.internship.osa.entity.EventPictures;
import static com.internship.osa.dao.OfyService.ofy;

public class EventPicturesDao {
	
	public static void save(String id, String picID, String eventID) {
		EventPictures ep = new EventPictures(id,picID,eventID);
		ofy().save().entity(ep).now();
	}	
}
