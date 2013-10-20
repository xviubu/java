package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class CheckCookie extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Cookie[] cookies = request.getCookies();

		if(cookies != null)
		{
			for(int i = 0;i < cookies.length;i++)
			{
				if(cookies[i].getName().equals("username"))
				{
					String userName = cookies[i].getValue();
					out.println("Hello " + userName);
					break;
				}
			}
		}

	}
}
