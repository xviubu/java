import java.net.*;
import java.io.*;
import java.util.*;

public class Redirector implements Runnable
{

	public static void main(String[] args)
	{
		int thePort;
		String theSite;

		try
		{
			theSite = args[0];
			if(theSite.endsWith("/"))
			{
				theSite = theSite.substring(0,theSite.length()-1);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Usage: java Redirector http://www.newsite.com/ port");
			return;
		}

		try
		{
			thePort = Integer.parseInt(args[1]);
		}
		catch(Exception ex)
		{
			thePort = 80;
		}

		Runnable r = new Redirector(theSite,thePort);
		Thread t = new Thread(r);
		t.start();
	}
	public Redirector(String site,int port)
	{
		this.newSite = site;
		this.port = port;
	}

	public void run()
	{
		try
		{
			ServerSocket server = new ServerSocket(this.port);
			System.out.println("Redirecting connections on port " + server.getLocalPort() + " to " + newSite);

			while(true)
			{
				try 
				{
					Socket connection = server.accept();
					Thread t = new RedirectorThread(connection);
					t.start();
				}
				catch(IOException ex)
				{

				}
			}
		}
		catch(BindException ex)
		{
			System.err.println("Cound not start server. Port Occupied");
		}
		catch(IOException ex)
		{
			System.err.println(ex);
		}
	}
class RedirectorThread extends Thread
{
	public RedirectorThread(Socket connection)
	{
		this.connection = connection;
	}

	public void run()
	{
		try
		{
		Writer out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(),"ASCII"));
		Reader in = new InputStreamReader(new BufferedInputStream(connection.getInputStream()));

		StringBuffer request = new StringBuffer(80);
		while(true)
		{
			int c = in.read();
			if(c == '\r' || c == '\n' || c == -1)
			{
				break;
			}

			request.append((char) c);
		}

		String get = request.toString();
		int firstSpace = get.indexOf(' ');
		int secondSpace = get.indexOf(' ',firstSpace + 1);
		String theFile = get.substring(firstSpace + 1,secondSpace);
		if(get.indexOf("HTTP") != -1)
		{
			out.write("HTTP/1.0 302 FOUND\r\n");
			Date now = new Date();
			out.write("Date: " + now + "\r\n");
			out.write("Server: Redirector 1.0 \r\n");
			out.write("Location: " + newSite + theFile+ "\r\n");
			out.write("Content-type: text/html\r\n\r\n");
			out.flush();
		}
		out.write("<HTML><HEAD><TITLE>Document moved</TITLE></HEAD>\r\n");
		out.write("<BODY><H1>Document moved</H1>\r\n");
		out.write("The document " + theFile + " has moved to \r\n <A HREF=\""+ newSite + theFile + "\">"
					+ newSite + theFile + "</A>.\r\n Please update your bookmarks<P>");
		out.write("</BODY></HTML>\r\n");
		out.flush();
		}
		catch(IOException ex)
		{

		}
		finally
		{
			try
			{
				if(connection != null)
					connection.close();
			}
			catch(IOException ex)
			{

			}
		}
					
	}
	private Socket connection;
}
	private int port;
	private String newSite;
}


