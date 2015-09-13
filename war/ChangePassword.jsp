<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.internship.osa.entity.ForgotUser"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="static com.internship.osa.dao.OfyService.ofy"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Change Password</title>
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
				<h1>Change Password</h1>
			</div>
		</div>
	</div>
	</section>
	<!--/#title-->
	<%
	String randomKey = request.getParameter("cp");
	List<ForgotUser> list = ofy().load().type(ForgotUser.class).filter("randomKey",randomKey).list();
	Iterator<ForgotUser> user = list.iterator();
%>

	<section id="registration" class="container"> <%
						if(user.hasNext())
						{
	%>
	<form class="center" action="/savePasswordLink" method="post">
		<fieldset class="registration-form">
			<table>
				<tr>
					<td>
						<div class="form-group">
							<input type="password" name="password" class="form-control"
								size="50" required> <input type="hidden" name="uID"
								value="<%=user.next().getuID() %>">
						</div>
					</td>
					<td>
						<p></p>
					</td>
					<td>
						<div class="form-group">
							<button class="btn btn-success">Submit</button>
						</div>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
	<%
						}
						else
						{
	%>
	<fieldset class="registration-form">
		<h3 align="Left" style="color: #000;">The Link Has Expired.
			Please Try Again.</h3>
		<%
						}
	%>
	</fieldset>
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