import java.net.*;
import java.io.*;
import java.util.Date;

public class DaytimeServer
{
	public static void main(String[] args)
	{
		int port = DEFAULT_PORT;
		if(args.length > 0)
		{
			try
			{
				port =  Integer.parseInt(args[0]);
				if(port < 0 || port >= 65535)
				{
					System.out.println("Port must between 0 and 65535");
					return;
				}
			}
			catch(NumberFormatException ex)
			{

			}
		}
		try
		{
			ServerSocket server = new ServerSocket(port);

			Socket connection = null;
			while(true)
			{
				try
				{
					connection = server.accept();
					Writer out = new OutputStreamWriter(connection.getOutputStream());
					Date now = new Date();
					out.write(now.toString() + "\r\n");
					out.flush();
					connection.close();
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
		}
		catch(IOException ex)
		{
			System.err.println(ex);
		}
	}
	public static final int DEFAULT_PORT = 13;
}
