package com.example;

import javax.servlet.http.*;

public class BeerSessionCounter implements HttpSessionListener
{
	public static int getActiveSessions()
	{
		return activeSessions;
	}

	public void sessionCreated(HttpSessionEvent event)
	{
		activeSessions++;
	}

	public void sessionDestroyed(HttpSessionEvent event)
	{
		activeSessions--;
	}
	private static int activeSessions;
}
