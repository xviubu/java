package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AttributeTester extends HttpServlet implements ServletRequestAttributeListener
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
		request.setAttribute("a","b");
		request.setAttribute("a","c");
		request.removeAttribute("a");
	}

	public void attributeAdded(ServletRequestAttributeEvent event)
	{
		System.out.println(" A:" + event.getName() + "->" + event.getValue());
	}

	public void attributeRemoved(ServletRequestAttributeEvent event)
	{
		System.out.println(" M:" + event.getName() + "->" + event.getValue());
	}

	public void attributeReplaced(ServletRequestAttributeEvent event)
	{
		System.out.println(" P:" + event.getName() + "->" + event.getValue());
	}
}
