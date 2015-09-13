package com.internship.osa.entity;

import java.util.Date;

import com.googlecode.objectify.annotation.*;

@Entity
public class Comments {
	@Index
	String eventID;
	@Index
	String comments;
	@Index
	String uID;
	@Index
	String name;
	@Id
	String id;
	@Index
	Date date;
	@Index
	boolean valid;
	public Comments() {
		super();
	}
	public Comments(String eventID, String comments, String uID, String name,
			String id, Date date) {
		super();
		this.eventID = eventID;
		this.comments = comments;
		this.uID = uID;
		this.name = name;
		this.id = id;
		this.date = date;
		valid = true;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String geteventID() {
		return eventID;
	}
	public void seteventID(String eventID) {
		this.eventID = eventID;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
