import java.net.*;
import java.io.*;

public class HighPortScanner
{
	public static void main(String[] args)
	{
		String host = "localhost";
		if(args.length > 0)
		{
			host  = args[0];
		}

		try
		{
			InetAddress theAddress = InetAddress.getByName(host);
			for(int i = 1;i < 65536;i++)
			{
				Socket connection = null;
				try
				{
					connection = new Socket(host,i);
					System.out.println("There is a server on port " + i + " of " + host);
				}
				catch(IOException ex)
				{

				}
				finally
				{
					try
					{
						if(connection != null)
						{
							connection.close();
						}
					}
					catch(IOException ex)
					{

					}
				}
			}
		}
		catch(UnknownHostException ex)
		{
			System.err.println(ex);
		}
	}
}
