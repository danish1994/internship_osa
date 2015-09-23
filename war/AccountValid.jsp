<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.internship.osa.entity.UserDetails"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="static com.internship.osa.dao.OfyService.ofy"%>
<%
try
{
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Account Validate</title>
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
				<h1>Screening</h1>

			</div>
		</div>
	</div>
	</section>
	<!--/#title-->

	<section id="registration" class="container">
	<h1>Screening</h1>
	<table>
		<tr>
			<td>
				<form action="/logout" method="post">
					<input class="btn-primary" type="submit" value="Logout" />
				</form>
			</td>
			<td>
				<form action="VerifyPassword.jsp" method="post">
					<input class="btn-primary" type="submit" value="Settings" />
				</form>
			</td>
		</tr>
	</table>
	<br />
	<br />
	<table class="table table-condensed">
		<tr>
			<td><b><strong>Email Id</strong></b></td>
			<td><b><strong>Name</strong></b></td>
			<td><b><strong>Accept/Reject</strong></b></td>
		</tr>
		<%
				String type=(String)session.getAttribute("Type");
				if(type.equals("admin"))
				{
				List<UserDetails> det = ofy().load().type(UserDetails.class).filter("status", false).list();
				Iterator<UserDetails> user = det.iterator();
					while (user.hasNext())
					{
						UserDetails ud=user.next();
				%>
		<tr>
			<td class="success"><%= ud.getuID() %></td>
			<td class="success"><%= ud.getName() %></td>
			<td class="success">
				<form action="/userAccept" method="post">
					<input class="btn btn-success" type="submit" value="Accept"
						style="width: 100%"> <input name="uID" type="hidden"
						value="<%= ud.getuID()%>">
				</form>
				<form action="/userReject" method="post">
					<input class="btn btn-danger" type="submit" value="Reject"
						style="width: 100%"> <input name="uID" type="hidden"
						value="<%= ud.getuID()%>">
				</form>
			</td>
		</tr>
		<%
					}
				}
			}catch(Exception e)
			{
				response.sendRedirect("/loginCheck");
			}
		%>

	</table>



	</section>
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