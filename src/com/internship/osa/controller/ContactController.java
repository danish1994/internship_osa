package com.internship.osa.controller;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Properties;

@SuppressWarnings("serial")
public class ContactController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		String name = req.getParameter("Name");
		String uID = req.getParameter("Email");
		String usermsg = req.getParameter("Msg");
		//Contact Email
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		String msgBody = "We Recieved a Message from " + name + " Email ID: "
				+ uID + "\n " + usermsg;
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("danish8802204230@gmail.com",
					"The House of Change"));
			//Default Admins
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"danish8802204230@gmail.com", "admin"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"mayurgpt07@gmail.com", "admin"));
			msg.setSubject("Message on Contact Us");
			msg.setText(msgBody);
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		res.sendRedirect("about.html");
	}
}
