package com.example;
import javax.servlet.http.*;
import java.io.*;

public class Dog implements HttpSessionBindingListener,HttpSessionActivationListener,Serializable
{
	public Dog(String breed)
	{
		this.breed = breed;
	}

	public String getBreed()
	{
		return breed;
	}

	public void valueBound(HttpSessionBindingEvent event)
	{
		System.out.println("Dog is bounded");
	}
	public void valueUnbound(HttpSessionBindingEvent event)
	{
		System.out.println("Dog is unbound");
	}

	public void sessionWillPassivate(HttpSessionEvent event)
	{
		System.out.println("session will passivate");
	}
	public void sessionDidActivate(HttpSessionEvent event)
	{
		System.out.println("session did activate ");
	}
	private String breed;
}
