package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ListenerTester extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("test context attributes set by listener<br>");
		out.println("<br>");

		Dog dog = (Dog) getServletContext().getAttribute("dog");

		out.println("Dog's breed is: " + dog.getBreed());
		out.println("test context attributes<br>");
/*
		synchronized(getServletContext())
		{

			getServletContext().setAttribute("foo","22");
			getServletContext().setAttribute("bar","42");

			out.println(getServletContext().getAttribute("foo"));
			out.println(getServletContext().getAttribute("bar"));
		}
*/
		HttpSession session = request.getSession();
		session.setAttribute("A","B");
		session.setAttribute("A","C");
		session.invalidate();
	}
}
