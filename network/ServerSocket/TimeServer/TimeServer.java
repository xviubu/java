import java.io.*;
import java.net.*;
import java.util.Date;

public class TimeServer
{
	public static void main(String[] args)
	{
		int port = DEFAULT_PORT;
		if(args.length > 0)
		{
			try
			{
				port = Integer.parseInt(args[0]);
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

		long differenceBetweenEpochs = 2208988800L;
		try
		{
			ServerSocket server = new ServerSocket(port);
			while(true)
			{
				Socket connection = null;
				try
				{
					connection = server.accept();	
					OutputStream out = connection.getOutputStream();
					Date now = new Date();
					long msSince1970 = now.getTime();
					long secondsSince1970 = msSince1970/1000;
					long secondsSince1900 = secondsSince1970 + differenceBetweenEpochs;
					byte[] time = new byte[4];
					time[0] = (byte) ((secondsSince1900 & 0x00000000FF000000L) >> 24);
					time[1] = (byte) ((secondsSince1900 & 0x0000000000FF0000L) >> 16);
					time[2] = (byte) ((secondsSince1900 & 0x000000000000FF00L) >> 8);
					time[3] = (byte) (secondsSince1900 & 0x00000000000000FFL);

					out.write(time);
					out.flush();
				}
				catch(IOException ex)
				{

				}
				finally
				{
					if(connection != null)
						connection.close();
				}
			}
		}
		catch(IOException ex)
		{
			System.err.println(ex);
		}
	}
	public static final int DEFAULT_PORT = 37;
}
