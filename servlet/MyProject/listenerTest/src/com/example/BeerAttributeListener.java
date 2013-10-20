package com.example;

import javax.servlet.http.*;

public class BeerAttributeListener implements HttpSessionAttributeListener
{
	public void attributeAdded(HttpSessionBindingEvent event)
	{
		String name = event.getName();
		Object value = event.getValue();
		System.out.println("Attribute Added : " + name + ":" + value);
	}

	public void attributeRemoved(HttpSessionBindingEvent event)
	{
		String name = event.getName();
		Object value = event.getValue();
		System.out.println("Attribute Removed : "+ name + ":" + value);
	}

	public void attributeReplaced(HttpSessionBindingEvent event)
	{
		String name = event.getName();
		Object value = event.getValue();
		System.out.println("Attribute Replaced : " +  name + ":" + value);
	}



}
