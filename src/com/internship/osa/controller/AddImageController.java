package com.internship.osa.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.internship.osa.entity.Event;

import static com.internship.osa.dao.OfyService.ofy;
import static com.internship.osa.dao.EventPicturesDao.save;;

@SuppressWarnings("serial")
public class AddImageController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		HttpSession session = req.getSession(false);
		String eventID = req.getParameter("eventID");
		String uID = null;
		try {
			uID = (String) session.getAttribute("uID");
		} catch (Exception e) {
			res.sendRedirect("login.html");
		}
		Event e = ofy().load().type(Event.class).id(eventID).now();
		if(e.getuID().equals(uID))
		{
		// Blob Store Start
		BlobstoreService blobstoreService = BlobstoreServiceFactory
				.getBlobstoreService();
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("pic");
		BlobKey blobKey = new BlobKey(blobKeys.get(0).getKeyString());
		System.out.println(blobKey);
		ImagesService imagesService = ImagesServiceFactory.getImagesService();
		@SuppressWarnings("deprecation")
		String picID = imagesService.getServingUrl(blobKey);
		System.out.println(eventID);
		// Blob Store End
		Date date = new Date();
		save(date.toString()+picID,picID,eventID);
		System.out.println("Saved");
		// Save Picture
		res.sendRedirect("/AddImage.jsp?eventID="+eventID);
		}
		else
			res.sendRedirect("/loginCheck");
	}
}
