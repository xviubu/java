package com.xviubu.tip;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class TipServlet extends HttpServlet
{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
		
		PageContent pageContent = new PageContent();
		pageContent.setCurrentTip(tip);
		request.setAttribute("pageContent",pageContent);

		List<String[]> movieList = new ArrayList<String[]>();
		String[] movieList1 = {"变形金刚","禁闭岛","大逃杀"};
		String[] movieList2 = {"致命弯道1","致命弯道2","致命弯道3","致命弯道4"};
		movieList.add(movieList1);
		movieList.add(movieList2);

		String[] commentList = {"This site rocks.","This site is cool.","This site is stupid"};

		request.setAttribute("commentList",commentList);
		request.setAttribute("userType","notmember");
		String userPref = request.getParameter("userPref");

		request.setAttribute("userPref",userPref);
		request.setAttribute("movieList",movieList);

		RequestDispatcher view = request.getRequestDispatcher("show.jsp");
		view.forward(request,response);
	}

//	private static final String tip = "Wash your hair every other day.";
	private static final String tip = "<b></b>tags make things bold";
   
}
