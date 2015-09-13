package com.internship.osa.entity;

import com.googlecode.objectify.annotation.*;

@Entity
public class Subscribe {
	@Index
	String uID;
	@Index
	String eventID;
	@Id
	String id;
	public Subscribe() {
		super();
	}
	public Subscribe(String uID, String eventID, String id) {
		super();
		this.uID = uID;
		this.eventID = eventID;
		this.id = id;
	}
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
