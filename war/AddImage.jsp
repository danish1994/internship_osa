<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.internship.osa.entity.EventPictures"%>
<%@ page import="com.internship.osa.entity.Event"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="static com.internship.osa.dao.OfyService.ofy"%>
<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Add Image</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/prettyPhoto.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>
<%
	String uID = null;
	String type = null;
	try {
		uID = (String) session.getAttribute("uID");
		type = (String) session.getAttribute("Type");
	String eventID = request.getParameter("eventID");	
	Event e = ofy().load().type(Event.class).id(eventID).now();
	if (!e.getuID().equals(uID)) {
		response.sendRedirect("/loginCheck");
	}
%>

<!--/head-->
<body>
	<header class="navbar navbar-inverse navbar-fixed-top wet-asphalt"
		role="banner">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html"><img
					src="images/logo.png" alt="logo"></a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="index.html">Home</a></li>
					<li><a href="about-us.html">About Us</a></li>
					<li><a href="events.html">Events</a></li>
					<li><a href="/loginCheck">Login</a></li>
					<li><a href="contact-us.html">Contact</a></li>
				</ul>
			</div>
		</div>
	</header>
	<!--/header-->

	<section id="title" class="emerald">
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<h1>Add Image</h1>
				</div>
			</div>
		</div>
	</section>
	<!--/#title-->

	<section id="portfolio" class="container">
		<%
		BlobstoreService blobstoreService = BlobstoreServiceFactory
					.getBlobstoreService();
		%>
		<form action="<%=blobstoreService.createUploadUrl("/addImage")%>"
			method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><input type="file" name="pic" accept="image/*"><input type="hidden" name="eventID" value="<%=eventID %>" /></td>
					<td><input type="submit"></td>

				</tr>
			</table>
		</form>
		<!--/#portfolio-filter-->
	</section>

	<ul class="portfolio-items col-3">
		<%
	List<EventPictures> det = ofy().load().type(EventPictures.class).filter("eventID",eventID).list();
	Iterator<EventPictures> et = det.iterator();
		while (et.hasNext())
		{
			EventPictures pd=et.next();
	
		%>
		<li class="portfolio-item apps">
			<div class="item-inner">
				<img src="<%=pd.getPicID()%>">
				<div class="overlay">
					<a class="preview btn btn-danger"
						href="<%=pd.getPicID()%>" rel="prettyPhoto"><i
						class="icon-eye-open"></i></a>
				</div>
			</div>
		</li>
		<!--/.portfolio-item-->
		<%
		}
		} catch (Exception e) {
			response.sendRedirect("/loginCheck");
		}
		
		%>
	</ul>
	</section>
	<!--/#portfolio-->
	<footer id="footer" class="midnight-blue">
		<div class="container">
			<div class="row">
				<div class="col-sm-6">&copy; 2015. All Rights Reserved.</div>
				<div class="col-sm-6">
					<ul class="pull-right">
						<li><a href="#">Home</a></li>
						<li><a href="#">About Us</a></li>
						<li><a href="#">Contact Us</a></li>
						<li><a id="gototop" class="gototop" href="#"><i
								class="icon-chevron-up"></i></a></li>
						<!--#gototop-->
					</ul>
				</div>
			</div>
		</div>
	</footer>
	<!--/#footer-->

	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.prettyPhoto.js"></script>
	<script src="js/jquery.isotope.min.js"></script>
	<script src="js/main.js"></script>
</body>
</html>
