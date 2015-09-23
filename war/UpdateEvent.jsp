<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="static com.internship.osa.dao.OfyService.ofy"%>
<%@ page import="com.internship.osa.entity.Event"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Modify Event</title>
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
	<script type="text/javascript">
		if ($('#date').prop('type') != 'date') {
			$('#date').datepicker();
		}
	</script>
	<!--/header-->
	<section id="title" class="emerald">
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<h1>Update Events</h1>
				</div>
			</div>
		</div>
	</section>
	<!--/#title-->
	<section>
		<div class="row row-centered">
			<div class="col-md-2" style="width: 100%">
				<%
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date eventDate = e.getEventDate();
				%>
				<form action="/updateEvent" method="post">
					<fieldset class="registration-form">
						<table class="about-top">
							<tr>
								<td colspan=3><br /> <br />
									<h3>Event Details:</h3> <br /></td>
							</tr>
							<tr>
								<td><h3>Tag</h3></td>
								<td><h3>Place</h3></td>
								<td><h3>Date</h3></td>
							</tr>
							<tr>

								<td>
									<div class="form-group">
										<input type="text" name="Tag" value="<%=e.getTag()%>" required>
									</div>
								</td>
								<td>
									<div class="form-group">
										<input type="text" name="Place" value="<%=e.getPlace()%>"
											required>
									</div>
								</td>
								<td>
									<div class="form-group">
										<input type="date" name="eventDate"
											value="<%=dateFormat.format(eventDate)%>" required id="date"><input
											type="hidden" name="eventID" value="<%=eventID%>">
									</div>
								</td>
							</tr>
							<tr>
								<td colspan=3>
									<h3>Description</h3>
								</td>
							</tr>
							<tr>
								<td colspan=3>
									<div class="form-group">
										<textarea class="form-control" rows="10" cols="60"
											id="comment" name="Description" required><%= e.getDescription() %></textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan=3><input type="submit" name="submit"
									class="btn btn-warning btn-lg" value="Update Event"
									style="width: 100%;" /></td>
							</tr>
						</table>
						<%
			} catch (Exception e) {
				response.sendRedirect("/loginCheck");
			}
			%>
					</fieldset>
				</form>
			</div>
		</div>
	</section>
	<!--/#registration-->
	<!--/#bottom-->
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