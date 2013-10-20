package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class SessionTester extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		session.setAttribute("foo","42");
		session.setAttribute("bar","420");
//		session.invalidate();

		session.setMaxInactiveInterval(1);
		String foo = (String)session.getAttribute("foo");
			
		if(session.isNew())
		{
			out.println("This is a new session");
		}
		else
		{
			out.println("Welcome back!");
		}
		
		out.println("Foo: " + foo);

	}
}
