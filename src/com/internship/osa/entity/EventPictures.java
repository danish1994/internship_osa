package com.internship.osa.entity;


import com.googlecode.objectify.annotation.*;

@Entity
public class EventPictures {
	@Id
	String id;
	String picID;
	@Index
	String eventID;
	public EventPictures() {
		super();
	}
	public EventPictures(String id, String picID, String eventID) {
		super();
		this.id = id;
		this.picID = picID;
		this.eventID = eventID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPicID() {
		return picID;
	}
	public void setPicID(String picID) {
		this.picID = picID;
	}
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
}
