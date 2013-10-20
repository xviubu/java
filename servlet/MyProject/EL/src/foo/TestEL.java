package foo;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class TestEL extends HttpServlet
{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
		foo.Person p = new foo.Person();
		p.setName("Evan");

		foo.Dog dog = new foo.Dog();
		dog.setName("Spike");
		
		p.setDog(dog);

		String[] favoriteMusic = {"Zero 7","Tahiti 80","BT","Frou Frou"};
		ArrayList<String> favoriteFood = new ArrayList<String>();
		favoriteFood.add("chai ice cream");
		favoriteFood.add("fajitas");
		favoriteFood.add("thai pizza");
		favoriteFood.add("anything in dark chocolate");
		request.setAttribute("food",favoriteFood);

		Map musicMap = new HashMap();
		musicMap.put("Ambient","Zero 7");
		musicMap.put("Surf","Tahiti 80");
		musicMap.put("Dj","BT");
		musicMap.put("Indie","Travis");
		request.setAttribute("musicMap",musicMap);
		request.setAttribute("Genre","Ambient");

		String[] musicTypes = {"Ambient","Surf","DJ","Indie"};
		request.setAttribute("musicType",musicTypes);

		request.setAttribute("musicList",favoriteMusic);
		request.setAttribute("person",p);

		RequestDispatcher view = request.getRequestDispatcher("result.jsp");
		view.forward(request,response);
		
	}
}
