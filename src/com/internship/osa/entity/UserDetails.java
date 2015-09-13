package com.internship.osa.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class UserDetails {
	@Id
	String uID;
	@Index
	String name;
	String pass;
	@Index
	String type;
	@Index
	Boolean status;
	@Index
	String source;
	@Index 
	boolean isNew;
	public UserDetails() {

	}

	public UserDetails(String uID, String name, String pass, String type,
			Boolean status, String source) {
		super();
		this.uID = uID;
		this.name = name;
		this.pass = pass;
		this.type = type;
		this.status = status;
		this.source = source;
		isNew = true;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
}
