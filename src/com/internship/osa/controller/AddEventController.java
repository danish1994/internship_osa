package com.internship.osa.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

import static com.internship.osa.dao.EventDao.save;

@SuppressWarnings("serial")
public class AddEventController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		HttpSession session = req.getSession(false);
		String tag = req.getParameter("Tag");
		String place = req.getParameter("Place");
		String uID = null;
		String getDate = req.getParameter("eventDate");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date eventDate = null;
		try {
			eventDate = formatter.parse(getDate);
			System.out.println(eventDate);
			System.out.println(formatter.format(eventDate));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		String description = req.getParameter("Description");
		try {
			uID = (String) session.getAttribute("uID");
		} catch (Exception e) {
			res.sendRedirect("login.html");
		}
		// String time = timeFormat.format(today);
		// Blob Store Start
		BlobstoreService blobstoreService = BlobstoreServiceFactory
				.getBlobstoreService();
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("pic");
		BlobKey blobKey = new BlobKey(blobKeys.get(0).getKeyString());
		System.out.println(blobKey);
		ImagesService imagesService = ImagesServiceFactory.getImagesService();
		@SuppressWarnings("deprecation")
		String eventID = imagesService.getServingUrl(blobKey);
		System.out.println(eventID);
		// Blob Store End
		Date date = new Date();
		save(eventID,place,tag,description,eventDate,uID,0,date);
		System.out.println("Saved");
		// Save Picture
		res.sendRedirect("/AddImage.jsp?eventID="+eventID);
	}
}
