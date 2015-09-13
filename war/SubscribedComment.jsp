<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.internship.osa.entity.Event"%>
<%@ page import="com.internship.osa.entity.Subscribe"%>
<%@ page import="com.internship.osa.entity.Comments"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="static com.internship.osa.dao.OfyService.ofy"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Subscribe Comments</title>
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
			<h3><%= e.getTag() %></h3>
			<%
				List <Comments> lst = ofy().load().type(Comments.class).filter("eventID",e.getEventID()).list();
				Iterator <Comments> itt = lst.iterator();
				while(itt.hasNext())
				{
					Comments c = itt.next();
			%>
			<div class="media">
				<div class="media-body">
					<div class="well">
						<div class="media-heading">
							<strong><%= c.getName() %></strong>
						</div>
						<p><%= c.getComments() %></p>
					</div>
				</div>
			</div>
			<%
				}
			%>
		</div>
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