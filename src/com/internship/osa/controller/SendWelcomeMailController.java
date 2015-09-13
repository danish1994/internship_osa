package com.internship.osa.controller;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.internship.osa.entity.UserDetails;

import static com.internship.osa.dao.OfyService.ofy;

@SuppressWarnings("serial")
public class SendWelcomeMailController extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)
	{
		List<UserDetails> ls = ofy().load().type(UserDetails.class).filter("isNew",true).list();
		Iterator<UserDetails> it = ls.iterator();
		while(it.hasNext())
		{
			UserDetails ud = it.next();
			String uID = ud.getuID();
			String name = ud.getName();
			ud.setNew(false);
			ofy().save().entity(ud);
			//Send Welcome Mail
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);
			String msgBody = "Hello "
					+ ud.getName() +  ".\nWelcome to Event Creater";
			Message msg = new MimeMessage(session);
			try {
				msg.setFrom(new InternetAddress("danish8802204230@gmail.com","Event Creater"));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(uID, name));
				msg.setSubject("Welcome");
				msg.setText(msgBody);
				Transport.send(msg);
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}		
	}
}
