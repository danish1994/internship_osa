<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.internship.osa.entity.UserDetails"%>
<%@ page import="com.internship.osa.controller.LoginController"%>
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
<title>Settings</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/prettyPhoto.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
<link rel="shortcut icon" href="images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="images/ico/apple-touch-icon-57-precomposed.png">
</head>
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
				<h1>User Settings</h1>
			</div>
		</div>
	</div>
	</section>
	<!--/#title-->
	<%
		String uID = null;
		String verified = null;
		try{
			uID = (String) session.getAttribute("uID");
			verified = (String) session.getAttribute("verified");
			session.removeAttribute("verified");
			if(!verified.equals("yes"))
				response.sendRedirect("/loginCheck");
			UserDetails ud= ofy().load().type(UserDetails.class).id(uID).now();	
			if(ud.getSource().equals("form"))
			{
		%>
	<section id="registration" class="container">
	<form class="center" action="/changeSetting" method="post">
		<h3>Change Settings</h3>
		<fieldset class="registration-form">
			<div class="form-group">
				<input type="text" id="text" name="name" value="<%= ud.getName() %>"
					class="form-control" required>
			</div>
			<div class="form-group">
				<input type="email" id="email" name="email"
					value="<%= ud.getuID() %>" class="form-control" required disabled>
				<input type="hidden" name="uID" value="<%= uID %>">
			</div>
			<div class="form-group">
				<input type="password" id="password" name="pass"
					value="<%=ud.getPass() %>" class="form-control" required>
			</div>
			<div class="form-group">
				<input type="submit" value="Change Details"
					class="btn btn-success btn-md btn-block">
			</div>
		</fieldset>
	</form>
	</section>
	<%
			}
			else
			{
	%>
	<section id="registration" class="container">
	<form class="center" action="/changeSetting" method="post">
		<h3>Change Settings</h3>
		<fieldset class="registration-form">
			<div class="form-group">
				<input type="text" id="text" name="name" value="<%= ud.getName() %>"
					class="form-control" required>
			</div>
				<input type="hidden" name="uID" value="<%= uID %>">
			<div class="form-group">
				<input type="submit" value="Change Details"
					class="btn btn-success btn-md btn-block">
			</div>
		</fieldset>
	</form>
	</section>
	<%
			}
		}catch(Exception e)
		{
			response.sendRedirect("/loginCheck");
		}
					%>
	<!--/#registration-->
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
	<script src="js/main.js"></script>
</body>
</html>