package com.internship.osa.controller;

import static com.internship.osa.dao.UserDetailsDao.save;
import static com.internship.osa.dao.UserDetailsDao.check;
import static com.internship.osa.dao.UserDetailsDao.getSavedName;
import static com.internship.osa.dao.UserDetailsDao.returnType;
import static com.internship.osa.dao.UserDetailsDao.returnValid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GoogleCallBackController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GoogleCallBackController() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("entering doGet");
			try {
				// get code
				String code = request.getParameter("code");
				System.out.println(code);
				// format parameters to post
				String urlParameters = "code="
						+ code
						+ "&client_id=710386576595-m43vqev3u5rq69lhlt4hdl00hntu8gcn.apps.googleusercontent.com"
						+ "&client_secret=m-NoPPzsgVKLYzc0Agg6pdNM"
						+ "&redirect_uri=http://internshiposa.appspot.com/googlecallback"
						+ "&grant_type=authorization_code";

				// post parameters
				URL url = new URL("https://accounts.google.com/o/oauth2/token");
				URLConnection urlConn = url.openConnection();
				urlConn.setDoOutput(true);
				OutputStreamWriter writer = new OutputStreamWriter(
						urlConn.getOutputStream());
				writer.write(urlParameters);
				writer.flush();

				// get output in outputString
				String line, outputString = "";
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(urlConn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					outputString += line;
				}
				System.out.println(outputString);

				// get Access Token
				JsonObject json = (JsonObject) new JsonParser()
						.parse(outputString);
				String access_token = json.get("access_token").getAsString();
				System.out.println(access_token);

				// get User Info
				url = new URL(
						"https://www.googleapis.com/oauth2/v1/userinfo?access_token="
								+ access_token);
				urlConn = url.openConnection();
				outputString = "";
				reader = new BufferedReader(new InputStreamReader(
						urlConn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					outputString += line;
				}
				System.out.println(outputString);

				// Convert JSON response into Pojo class
				GooglePojo data = new Gson().fromJson(outputString,
						GooglePojo.class);
				// Register
				HttpSession sess = request.getSession();
				// Check ID
				if (check(data.email)) {
					DateFormat dateFormat = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					String id = dateFormat.format(date);
					// Save Data and Login
					save(data.email, data.name, id + "id:" + data.id,
							"student", true, "google");
					sess.setAttribute("Name", data.name);
					sess.setAttribute("Type", "student");
					sess.setAttribute("loginSource", "google");
					System.out.println("Valid");
					sess.setAttribute("uID", data.email);
					response.sendRedirect("SelectRole.jsp");
				} else {
					// Login
					String name = getSavedName(data.email);
					String type = returnType((String) data.email);
					boolean valid = returnValid((String) data.email);
					if (valid == false)
						type = "student";
					sess.setAttribute("Type", type);
					sess.setAttribute("Name", name);
					System.out.println("Valid");
					sess.setAttribute("uID", data.email);
					response.sendRedirect("UserProfile.jsp");
				}
				writer.close();
				reader.close();

			} catch (MalformedURLException e) {
				System.out.println(e);
			} catch (ProtocolException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
			System.out.println("leaving doGet");
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Oops. Something went wrong!');");
			out.println("window.location = '/loginCheck';");
			out.println("</script>");
		}
	}
}