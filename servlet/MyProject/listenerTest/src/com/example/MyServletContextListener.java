package com.example;
import javax.servlet.*;

public class MyServletContextListener implements ServletContextListener
{
	public void contextInitialized(ServletContextEvent event)
	{
		/*由事件得到上下文*/
		ServletContext sc = event.getServletContext();
		/*由上下文得到初始化参数breed*/
		String dogBreed = sc.getInitParameter("breed");
		
		Dog d = new Dog(dogBreed);
		/*由上下文设置dog属性的职位d*/
		sc.setAttribute("dog",d);
	}

	public void contextDestroyed(ServletContextEvent event)
	{

	}
}
