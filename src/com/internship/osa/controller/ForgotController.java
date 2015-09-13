package com.internship.osa.controller;

import static com.internship.osa.dao.FrogotUserDao.save;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.*;

@SuppressWarnings("serial")
public class ForgotController extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		String uID=req.getParameter("email");
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Date date = new Date();
		//Generating Random Password
		String randomKey = UUID.randomUUID().toString();
		//Save Random Password
		save(uID,randomKey,date);
		//Mail Body
		String msgBody = "Please Reset Your Password.\nPlease Visit the link to Change the Password:\nhttp://internshiposa.appspot.com/ChangePassword.jsp?cp="+randomKey+"\nThe Link Expires in 30 minutes.";
		Message msg = new MimeMessage(session);
		//Forgot Email
	    try {
			msg.setFrom(new InternetAddress("danish8802204230@gmail.com", "Event Creater"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(uID, "User"));
		    msg.setSubject("Password Reset");
		    msg.setText(msgBody);
		    Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		res.sendRedirect("forgot-confirm.html");
	}
}
