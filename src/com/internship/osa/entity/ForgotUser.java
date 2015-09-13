package com.internship.osa.entity;

import java.util.Date;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class ForgotUser {
	@Id
	String uID;
	@Index
	String randomKey;
	Date date;

	public ForgotUser() {
		super();
	}

	public ForgotUser(String uID, String randomKey, Date date) {
		super();
		this.uID = uID;
		this.randomKey = randomKey;
		this.date = date;
	}

	public String getuID() {
		return uID;
	}

	public void setuID(String uID) {
		this.uID = uID;
	}

	public String getRandomKey() {
		return randomKey;
	}

	public void setRandomKey(String randomKey) {
		this.randomKey = randomKey;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
