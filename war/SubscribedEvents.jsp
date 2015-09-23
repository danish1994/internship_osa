<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.internship.osa.entity.Event"%>
<%@ page import="com.internship.osa.entity.Subscribe"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="static com.internship.osa.dao.OfyService.ofy"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Subscribe Events</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/prettyPhoto.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>
<!--/head-->
<body>
	<section id="blog" class="container">
	<div class="blog">
		<%
		String uID = null;
		try{
			uID = (String) session.getAttribute("uID");
		
		List <Subscribe> ls = ofy().load().type(Subscribe.class).filter("uID",uID).list();
		Iterator <Subscribe> it = ls.iterator();
		while(it.hasNext())
		{
			Subscribe s = it.next();
			Event e = ofy().load().type(Event.class).id(s.getEventID()).now();
	%>
		<div class="blog-item">
			<img class="img-responsive img-blog" src="<%= e.getEventID() %>"
				width="100%" alt="" />
			<div class="blog-content">
				<h3><a href="Single.jsp?eventID=<%=e.getEventID()%>"><%= e.getTag() %></a></h3>
				<div class="entry-meta">
					<span><i class="icon-user"></i> <a href="#"><%= e.getTag() %></a></span>
					<span><i class="icon-calendar"></i> <%= e.getEventDate() %></span>
				</div>
			</div>
		</div>
		<!--/.blog-item-->
		<%
		}
		}catch(Exception e)
		{
			response.sendRedirect("/loginCheck");
		}
		%>
		<h3>No More Events</h3>
	</div>
	</section>
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.prettyPhoto.js"></script>
	<script src="js/jquery.isotope.min.js"></script>
	<script src="js/main.js"></script>
</body>
</html>