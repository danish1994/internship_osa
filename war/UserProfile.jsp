<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>User</title>
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
<%
	try {

		String name = (String) session.getAttribute("Name");
%>
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
					<h1>
						Welcome
						<%=name%></h1>
				</div>
			</div>
		</div>
	</section>
	<!--/#title-->
	<%
		String type = (String) session.getAttribute("Type");
	%>
	<section id="middle" class="container">
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav navbar-left">
				<li><a href="index.html">Home</a></li>
				<li><a href="VerifyPassword.jsp">Settings</a></li>
				<%
					if (type.equals("faculty")) {
				%>

				<li><a href="ManageEvent.jsp" class="dropdown-toggle"
					data-toggle="dropdown">Manage Event <i class="icon-angle-down"></i></a>
					<ul class="dropdown-menu">
						<li><a href="AddEvent.jsp">Add</a></li>
						<li><a href="ManageEvent.jsp">Upadate</a></li>
					</ul></li>
				<%
					}
					} catch (Exception e) {
						response.sendRedirect("/loginCheck");
					}
				%>
				<li><a href="/logout">Logout</a></li>
			</ul>
		</div>
		<table>
			<tr>
				<td>
					<h4>Subscribed Events</h4> <iframe src="SubscribedEvents.jsp"></iframe>
				</td>
				<td>
					<h4>Comments on Subscribed Events</h4> <iframe
						src="SubscribedComment.jsp"></iframe>
				</td>
			<tr>
		</table>
	</section>

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