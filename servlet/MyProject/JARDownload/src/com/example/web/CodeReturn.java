package com.example.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class CodeReturn extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
		response.setContentType("application/jar");

		ServletContext ctx = getServletContext();
		InputStream in = ctx.getResourceAsStream("/bookCode.jar");

		int read = 0;
		byte[] bytes = new byte[1024];

		OutputStream out = response.getOutputStream();
		while((read = in.read(bytes)) != -1)
		{
			out.write(bytes,0,read);
		}
		out.flush();
		out.close();
	}
}
