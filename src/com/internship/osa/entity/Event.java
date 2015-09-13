package com.internship.osa.entity;

import java.util.Date;

import com.googlecode.objectify.annotation.*;

@Entity
public class Event {
	@Id
	String eventID;
	String place;
	String tag;
	String description;
	@Index
	Date eventDate;
	@Index
	String uID;
	@Index
	int subCount;
	@Index
	Date date;

	public Event() {
		super();
	}

	public Event(String eventID, String place, String tag, String description,
			Date eventDate, String uID, int subCount, Date date) {
		super();
		this.eventID = eventID;
		this.place = place;
		this.tag = tag;
		this.description = description;
		this.eventDate = eventDate;
		this.uID = uID;
		this.subCount = subCount;
		this.date = date;
	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getuID() {
		return uID;
	}

	public void setuID(String uID) {
		this.uID = uID;
	}

	public int getSubCount() {
		return subCount;
	}

	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

}
