<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.internship.osa.entity.Event"%>
<%@ page import="com.internship.osa.entity.UserDetails"%>
<%@ page import="com.internship.osa.entity.Subscribe"%>
<%@ page import="com.internship.osa.entity.Comments"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="static com.internship.osa.dao.OfyService.ofy"%>
<%
	String eventID = request.getParameter("eventID");
	Event pd = ofy().load().type(Event.class).id(eventID).now();
	String desc = pd.getDescription();
	String tag = pd.getTag();
	Date date = pd.getEventDate();
	int sub = ofy().load().type(Subscribe.class).filter("eventID",eventID).count();
	int comments = ofy().load().type(Comments.class).filter("eventID",eventID).count();
	String likeStatus = "no";
	String likeType = "Subscribe";
	String eventUID = pd.getuID();
	String uID = null;
	String place = pd.getPlace();
	DateFormat calendarFormat = new SimpleDateFormat("yyyyMMdd");
	Date startDate = date;
	Date endDate = date;
	String showStartDate = calendarFormat.format(startDate);
	String showEndDate = calendarFormat.format(endDate);
	DateFormat timeFormat = new SimpleDateFormat("dd/MM/YYYY");
	String showDate = timeFormat.format(date);
	try{
		uID = (String) session.getAttribute("uID");
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	List<Subscribe> l = ofy().load().type(Subscribe.class).filter("eventID", eventID).filter("uID", uID).list();
	Iterator<Subscribe> lIt = l.iterator();
	if(lIt.hasNext())
	{
		likeStatus="yes";
		likeType="UnSubscribe";	
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title><%= tag %></title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/prettyPhoto.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">

<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.prettyPhoto.js"></script>
<script src="js/main.js"></script>
<script src="js/jspdf.js"></script>
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>
<!--/head-->
<body>
	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=144716315690681";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>

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
					<h1><%= tag %></h1>
				</div>
			</div>
		</div>
	</section>
	<!--/#title-->

	<section id="blog" class="container">
		<div class="row">
			<aside class="col-sm-4 col-sm-push-8">
				<div class="widget">
					<h3>Location of Event</h3>
					<iframe width="100%" height="215" frameborder="0" scrolling="no"
						marginheight="0" marginwidth="0"
						src="https://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=<%=place %>&amp;output=embed"></iframe>
				</div>
			</aside>
			<div class="col-sm-8 col-sm-pull-4">
				<div class="blog">
					<div class="blog-item">
						<img class="img-responsive img-blog" src="<%= eventID %>"
							width="100%" alt="" />
						<div class="blog-content">
							<h3><%= tag %></h3>
							<div id="content">
								<div class="entry-meta">
									<span><i class="icon-user"></i> <a href="#"><%= eventUID %></a></span>
									<span><i class="icon-calendar"></i> <%= showDate %></span> <span><i
										class="icon-comment"></i> <a href="blog-item.html#comments"
										id="commentCount"></a></span>
								</div>
								<p class="lead"><%= desc %></p>
							</div>
							<table>
								<tr>
									<td><button class="btn-warning" onClick="showPictures()">Show
											Images</button></td>
									<td><button class="btn-warning" id="subscribe"
											onClick="modifyLike()">Subscribe</button></td>
									<td><button class="btn-warning" id="pdfGen">Download
											as PDF</button></td>
									<td><button class="btn-warning" onClick="addCalendar()">Add
											To Calender</button></td>
								</tr>
							</table>


							<hr>
							<script>
								function showPictures(){
									var eventID = '<%=eventID%>';
									window.location = "/ShowImage.jsp?eventID="+eventID;
								}
								function addCalendar() {
									var tag = '<%=tag%>';
									var place = '<%=place%>';
									var startDate = '<%=showStartDate%>';
									var endDate = '<%=showEndDate%>';
									var place = '<%=place%>';
									var desc = '<%=desc%>';
									var link = 'https://www.google.com/calendar/render?action=TEMPLATE&text='+tag+'&dates='+startDate+'/'+endDate+'&details='+desc+'&location='+place+'&sf=true&output=xml';
									console.log(link);
									window.location = link;
								}
							</script>
							<script>
								var doc = new jsPDF();
								var specialElementHandlers = {
									'#editor' : function(element, renderer) {
										return true;
									}
								};
								$('#pdfGen')
										.click(
												function() {
													doc
															.fromHTML(
																	$('#content')
																			.html(),
																	15,
																	15,
																	{
																		'width' : 170,
																		'elementHandlers' : specialElementHandlers
																	});
													doc.save('Event.pdf');
												});
							</script>
							<div id="editor"></div>
							<p>&nbsp;</p>
							<script>
							var length = 2;
							var l;
							var myArr;
							var id;
							loadComment();
							function increaseLength()
							{
								length+=2;
								load(myArr)
							}
							function loadComment(){
								var xmlhttp = new XMLHttpRequest();
								var eventID = '<%=eventID%>';
								var url = '/loadComment?eventID=' + eventID;
								xmlhttp.onreadystatechange = function() {
									if (xmlhttp.readyState % 5 == 4 && xmlhttp.status == 200) {
										myArr = JSON.parse(xmlhttp.responseText);
										load(myArr);
									}
								}
								xmlhttp.open("GET", url, true);
								xmlhttp.send();
							}
							function load(arr) {
								var out = "";
								var i,l=length;
								if(length>arr.length)
									l=arr.length;
								for (i = 1; i < l; i++) {
									var name = arr[i].name;
									out += '<div class="media-body" ><div class="well"><div class="media-heading">';
									out += '<strong>'+name+'</strong>&nbsp;';
									if(arr[i].uID=='<%=uID%>')
										out += '<button class="pull-right" id="'+arr[i].id+'" onClick="deleteComment(this.id)">Delete</button>';
									out += '</div><p>'+arr[i].comment+'</p></div></div>';
								}
								if(l>0&&l<arr.length)
									out+='<button class="btn btn-default" onClick="increaseLength()">Show More Comments</button>';
								else if(l==arr.length)
									out+='<h4>No More Comments</h4>';
								else
									out+='<h4>No More Comments</h4>';		
								out+='<br />'
								var type = '<%=likeType%>';
								document.getElementById("commentBox").innerHTML = out;
								document.getElementById("subscribe").innerHTML = type;
								document.getElementById("commentCount").innerHTML = arr[0].comment + 'Comments';
							}
							function addComment()
							{
								var comment = document.getElementById("commentBody").value;
								console.log(comment);
								if(comment!="")
								{
									document.getElementById("commentBody").value = "";
									var xmlhttp = new XMLHttpRequest();
									var url = '/comment?eventID='+'<%=eventID%>'+'&comment=' + comment;
									xmlhttp.open("GET", url, true);
									xmlhttp.send();
									xmlhttp.onreadystatechange = function() {
										if (xmlhttp.readyState % 5 == 4 && xmlhttp.status == 200) {
											loadComment();
										}
									}
								}
							}
							function deleteComment(id)
							{
								var xmlhttpDelete = new XMLHttpRequest();
								var url = '/deleteComment?id='+id;
								xmlhttpDelete.onreadystatechange = function() {
									if (xmlhttpDelete.readyState == 4 && xmlhttpDelete.status == 200) {
										loadComment();
									}
								}
								xmlhttpDelete.open("GET", url, true);
								xmlhttpDelete.send();
							}
							function modifyLike()
							{
								var xmlhttpDelete = new XMLHttpRequest();
								var eventID = '<%=eventID%>
								';
									var url = '/subscribe?eventID=' + eventID;
									xmlhttpDelete.onreadystatechange = function() {
										if (xmlhttpDelete.readyState == 4
												&& xmlhttpDelete.status == 200) {
											var arr = JSON
													.parse(xmlhttpDelete.responseText);
											if (arr[0].likeType != 'none')
												document
														.getElementById("subscribe").innerHTML = arr[0].SubscribeType;
										}
									}
									xmlhttpDelete.open("GET", url, true);
									xmlhttpDelete.send();
								}
							</script>

							<div id="comments">
								<div id="comments-list">
									<div class="media" id="commentBox"></div>
									<!--/.media-->
								</div>
								<!--/#comments-list-->
								<%
								if(session.getAttribute("uID")!=null)
								{
								%>
								<div id="comment-form">
									<h3>Leave a comment</h3>
									<div class="col-sm-12">
										<textarea rows="8" class="form-control" placeholder="Comment"
											id="commentBody"></textarea>
									</div>
									<button type="submit" class="btn-danger" onClick="addComment()">Submit
										Comment</button>
								</div>
								<%
								}
								else
								{
								%>
								<div id="comment-form">
									<h3>
										Please <a href="/loginCheck">Login</a> to Comment
									</h3>
								</div>
								<%
								}
								%>
								<!--/#comment-form-->
							</div>
							<!--/#comments-->
						</div>
					</div>
					<!--/.blog-item-->
				</div>
			</div>
			<!--/.col-md-8-->
		</div>
		<!--/.row-->
	</section>
	<!--/#blog-->

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
</body>
</html>