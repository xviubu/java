package foo;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Test extends HttpServlet
{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
		foo.Person p = new foo.Employee();
		p.setName("Evan");

		request.setAttribute("person",p);

		RequestDispatcher view = request.getRequestDispatcher("/result.jsp");
		view.forward(request,response);
	}
}
